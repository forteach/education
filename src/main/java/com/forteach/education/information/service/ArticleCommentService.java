package com.forteach.education.information.service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.domain.ArticleComment;
import com.forteach.education.information.repository.ArticleCommentDao;
import com.forteach.education.information.web.req.artComment.SaveArtCommentRequest;
import com.forteach.education.util.UpdateUtil;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.forteach.education.information.service.ArticleKey.STUDENT;
import static com.forteach.education.information.service.ArticleKey.STUDENT_ADO;


@Service
public class ArticleCommentService {


    @Resource
    private ArticleCommentDao articleCommentDao;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 设置资料对象数据
     *
     * @param request
     * @return
     */
    @Transactional
    public ArticleComment save(SaveArtCommentRequest request) {
        // 获得页面设置的资讯值
        String commentId = request.getCommentId();
        ArticleComment artcomment;

        // 是否获取已存在的用户信息
        if (StrUtil.isNotBlank(commentId)) {
            artcomment = findById(commentId);
            String createTime = artcomment.getCreateTime();
            UpdateUtil.copyProperties(request, artcomment);
            artcomment.setCreateTime(createTime);

        } else {
            artcomment = new ArticleComment();
            UpdateUtil.copyNullProperties(request, artcomment);
            artcomment.setCommentId(IdUtil.fastSimpleUUID());
            artcomment.setCreateUser(request.getUserId());

            //初始化点击量数据
            String artcommentkey = ArticleKey.ARTCOMMENTGOOD.concat(artcomment.getCommentId());
            stringRedisTemplate.opsForValue().set(artcommentkey, "0");

            //增加资讯回复数量
            String replykey = ArticleKey.ARTCOMMENTREPLY.concat(request.getArticleId());
            String count = stringRedisTemplate.opsForValue().get(replykey);
            int newCount = StrUtil.isNotBlank(count) ? Integer.valueOf(count).intValue() + 1 : 1;
            stringRedisTemplate.opsForValue().set(replykey, String.valueOf(newCount));

            stringRedisTemplate.expire(artcommentkey, 365, TimeUnit.DAYS);
            stringRedisTemplate.expire(replykey, 356, TimeUnit.DAYS);
            if (STUDENT.equals(request.getUserType())) {
                //学生名称
                String key = STUDENT_ADO.concat(request.getUserId());
                artcomment.setUserName(findStudentsName(key));
                //学生头像
                artcomment.setUserTortrait(findStudentsPortrait(key));
            } else {
                artcomment.setUserName("教师");
                //TODO 添加教师头像路径？
            }
        }
        return articleCommentDao.save(artcomment);
    }


    /**
     * 根据用户id 从redis 取出名字信息
     *
     * @param key
     * @return
     */
    private String findStudentsName(String key) {
        if (hashOperations.hasKey(key, "name")) {
            return hashOperations.get(key, "name");
        }
        return "";
    }

    /**
     * 从redis 取出头像信息
     *
     * @param key
     * @return
     */
    private String findStudentsPortrait(String key) {
        if (hashOperations.hasKey(key, "portrait")) {
            return hashOperations.get(key, "portrait");
        }
        return "";
    }


    /**
     * 根据ID获取资料信息
     *
     * @param id
     * @return
     */
    public ArticleComment findById(String id) {
        ArticleComment obj = articleCommentDao.findByCommentId(id);
        MyAssert.isNull(obj, DefineCode.ERR0013, "该信息不存在");
        return obj;
    }

    /**
     * 评论点赞
     *
     * @param commentId 评论编号
     * @return
     */
    @Transactional
    public int addClickGood(String commentId) {

        //资讯点赞次数+1
        articleCommentDao.addGoodCount(commentId);

        String key = ArticleKey.ARTCOMMENTGOOD.concat(commentId);

        String count = stringRedisTemplate.opsForValue().get(key);
        int newcount = Integer.valueOf(count).intValue() + 1;
        stringRedisTemplate.opsForValue().set(key, String.valueOf(newcount));
        stringRedisTemplate.expire(key, 365, TimeUnit.DAYS);
        return newcount;
    }

    /**
     * 评论回复
     *
     * @param reply         回复内容
     * @param commentId     评论ID
     * @param replyUserName 回复人名称
     * @return
     */
    @Transactional
    public String saveReply(String reply, String commentId, String replyUserName) {
        articleCommentDao.saveReply(reply, commentId, DateUtil.now(), replyUserName);
        return "Y";
    }

    /**
     * 根据资讯D，获得资讯评论
     *
     * @param articleId
     * @param pageable
     * @return
     */
    public List<ArticleComment> findByArticleId(String articleId, Pageable pageable) {
        return articleCommentDao.findByArticleIdOrderByCreateTimeDesc(articleId, pageable).getContent();
    }
}
