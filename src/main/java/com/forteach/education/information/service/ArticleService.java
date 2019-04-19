package com.forteach.education.information.service;

import java.util.List;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.dto.IArticle;
import com.forteach.education.information.repository.ArticleDao;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import com.forteach.education.information.web.res.article.ArticleResponse;
import com.forteach.education.util.RegexUtils;
import com.forteach.education.util.UpdateUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static java.util.stream.Collectors.toList;

@Service
public class ArticleService {

    //资讯信息在首页只显示6条数据
    @Value("${com.yunfeng.pageSize:6}")
    private String articleHomePageSize;


    @Autowired
    private ArticleDao articleDao;

    @Transactional
    public Article save(Article article) {

        // 保存资讯信息
        Article art = articleDao.save(article);
        // 返回对象为空，保存失败
        MyAssert.isNull(art, DefineCode.ERR0013);

        return art;
    }

    /**
     * 设置资料对象数据
     *
     * @param request
     * @return
     */
    public Article setDoMain(SaveArticleRequest request) {
        // 获得页面设置的资讯值
        String artId = request.getArticleId();
        Article art = null;

        // 是否获取已存在的用户信息
        if (StrUtil.isNotBlank(artId)) {
            art = findById(artId);
        }
        // 新建用户
        if (art == null) {
            art = new Article();
            UpdateUtil.copyNullProperties(request, art);
            art.setArticleId(IdUtil.fastSimpleUUID());
        }
        return art;
    }

    /**
     * 根据ID获取资料信息
     *
     * @param id
     * @return
     */
    public Article findById(String id) {
        return articleDao.findById(id).get();
    }

    /**
     * 根据课程ID，获得资讯
     *
     * @param courseId
     * @param pageable
     * @return
     */
    public List<ArticleResponse> findByCourseId(String courseId, Pageable pageable) {
        return articleDao.findByCourseIdOrderByCreateTimeDesc(courseId, pageable).getContent()
                .stream()
                .map(item -> {
                    ArticleResponse ar = new ArticleResponse();
                    UpdateUtil.copyNullProperties(item, ar);
                    return ar;
                })
                .collect(toList());
    }

    /**
     * 获得倒序的信息列表
     *
     * @param pageable
     * @return
     */
    public List<ArticleResponse> findAllDesc(Pageable pageable) {
        return articleDao.findAllByOrderByCreateTimeDesc(pageable).getContent()
                .stream()
                .map(item -> {
                    ArticleResponse ar = new ArticleResponse();
                    UpdateUtil.copyNullProperties(item, ar);
                    return ar;
                })
                .collect(toList());
    }

    /**
     * 收藏
     *
     * @param id
     * @return
     */
    public int addCollectCount(String id) {
        return articleDao.addCollectCount(id);
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    public int addClickGood(String articleId) {
        return articleDao.addCollectCount(articleId);
    }

    @Transactional
    public int delMoreByArticleIds(List<String> articleIds) {
        return articleDao.delMoreByArticleIds(articleIds);
    }

    @Transactional
    public int deleteArticleById(String articleId) {
        return articleDao.deleteArticleById(articleId);
    }


    private String replaceImgWidth(String content) {
        // 宽、高过滤的正则表达式
        String reg1 = "<img (.*?)height=\"(.*?)\\\"";
        String reg2 = "<img (.*?)width=\"(.*?)\\\"";

        content = RegexUtils.replaceAll(content, reg1, "<img $1height=\"100%\\\"").toString();
        content = RegexUtils.replaceAll(content, reg2, "<img $1width=\"100%\\\"").toString();

        return content;
    }


}
