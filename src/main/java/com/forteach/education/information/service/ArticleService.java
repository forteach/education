package com.forteach.education.information.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.domain.Classes;
import com.forteach.education.classes.service.ClassesService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.course.domain.Course;
import com.forteach.education.course.service.CourseService;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.dto.IArticle;
import com.forteach.education.information.repository.ArticleDao;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import com.forteach.education.information.web.res.article.IArtTag;
import com.forteach.education.util.UpdateUtil;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleService {

    @Resource
    private ArticleDao articleDao;

//    @Resource
//    private ArtIcleImagesService artIcleImagesService;

    @Resource
    private ClassesService classesService;

    @Resource
    private MyArticleService myArticleService;

    @Resource
    private CourseService courseService;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Transactional
    public Article save(Article article) {
//    public Article save(Article article, List<DataDatumVo> dataList) {

        //保存资讯内容图片列表信息
//        if (dataList != null && dataList.size() > 0) {
//            MyAssert.isNull(dataList.get(0), DefineCode.ERR0013, "保存资料的图片信息不能为空");
        //设置列表头图片
//            article.setImgUrl(dataList.get(0).getFileUrl());
//            boolean saveImg = artIcleImagesService.saveImages(article.getArticleId(), dataList);
//            MyAssert.isFalse(saveImg, DefineCode.ERR0013, "保存资料图片失败");
//        }

        // 保存资讯信息
        Article art = articleDao.save(article);
        // 返回对象为空，保存失败
        MyAssert.isNull(art, DefineCode.ERR0013, "保存资料内容失败");
        return art;
    }

    /**
     * 根据用户id 从redis 取出名字信息
     *
     * @param id
     * @return
     */
    private String findStudentsName(final String id) {
        String key = ArticleKey.STUDENT_ADO.concat(id);
        return hashOperations.get(key, "name");
    }

    /**
     * 从redis 取出头像信息
     *
     * @param id
     * @return
     */
    private String findStudentsPortrait(final String id) {
        return hashOperations.get(ArticleKey.STUDENT_ADO.concat(id), "portrait");
    }

    /**
     * 设置资料对象数据
     *
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Article setDoMain(SaveArticleRequest request) {
        // 获得页面设置的资讯值
        String artId = request.getArticleId();
        Article art;

        // 是否获取已存在的用户信息
        if (StrUtil.isNotBlank(artId)) {
            art = findById(artId);
            MyAssert.isNull(art, DefineCode.ERR0010, "资料信息不存在");
            String createTime = art.getCreateTime();
            UpdateUtil.copyProperties(request, art);
            art.setCreateTime(createTime);

        } else {
            art = new Article();
            UpdateUtil.copyNullProperties(request, art);
            art.setArticleId(IdUtil.fastSimpleUUID());
            art.setCreateUser(request.getUserId());
            art.setIsNice("false");

            //初始化点收藏、点赞、回复数量数据
            String sckey = ArticleKey.SHOUCANG.concat(art.getArticleId());
            String gdkey = ArticleKey.GOOD.concat(art.getArticleId());
            String replykey = ArticleKey.ARTCOMMENTREPLY.concat(art.getArticleId());
            stringRedisTemplate.opsForValue().set(sckey, "0");
            stringRedisTemplate.opsForValue().set(gdkey, "0");
            stringRedisTemplate.opsForValue().set(replykey, "0");

            //获得班级信息
            Classes cla = classesService.findById(request.getClassId());
            MyAssert.isNull(cla, DefineCode.ERR0013, "班级信息不存在");
            //设置班级名称
            art.setClassName(cla.getClassName());

            //获得课程名称
            Course course = courseService.findByCourseId(request.getCourseId());
            MyAssert.isNull(course, DefineCode.ERR0013, "课程信息不存在");
            art.setCourseName(course.getCourseName());

            //学生名称
            art.setUserName(findStudentsName(request.getUserId()));
            //学生头像
            art.setUserTortrait(findStudentsPortrait(request.getUserId()));

            //记录我的发布信息
            myArticleService.setMyArticle("", art.getUserId(), art.getArticleId(), ArticleKey.FABUVALUE);
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
        Article obj = articleDao.findByArticleId(id);
        MyAssert.isNull(obj, DefineCode.ERR0013, "该信息不存在");
        return obj;
    }

    /**
     * 根据课程ID，获得资讯
     *
     * @param courseId
     * @param pageable
     * @return
     */
    public List<IArticle> findByCourseId(String courseId, Pageable pageable) {

        //TODO 增加筛选已收藏的学生
        return articleDao.findByCourseIdOrderByCreateTimeDesc(courseId, pageable).getContent();
    }

    /**
     * 获得倒序的信息列表
     *
     * @param pageable
     * @return
     */
    public List<IArticle> findAllDesc(Pageable pageable) {
        return articleDao.findAllByOrderByCreateTimeDesc(pageable).getContent();
    }

    public List<IArticle> findAllAndTitleDesc(Pageable pageable, String title) {
        return articleDao.findAllByTitleContainingOrderByCreateTimeDesc(title, pageable).getContent();
    }

    /**
     * 设置学生列表是否收藏、发布、点赞
     *
     * @param ar
     * @param articleId
     * @param userId
     */
    public void setStuTagType(IArtTag ar, String articleId, String userId) {

        //设置是否点赞
        ar.setIsClickGood(String.valueOf(myArticleService.exixtsMyArticle(articleId, userId, ArticleKey.GOODVALUE)));
        //设置是否收藏
        ar.setIsCollect(String.valueOf(myArticleService.exixtsMyArticle(articleId, userId, ArticleKey.SHOUCANGVALUE)));
        //设置是否发布
        ar.setIsMy(String.valueOf(myArticleService.exixtsMyArticle(articleId, userId, ArticleKey.FABUVALUE)));
    }

    /**
     * 根据学生ID，获得资讯
     *
     * @param studentId 发布资讯的学生
     * @param pageable
     * @return
     */
    public List<IArticle> findByStudentId(String studentId, Pageable pageable) {
        return articleDao.findByUserIdOrderByCreateTimeDesc(studentId, pageable).getContent();
    }

    /**
     * @param studentId 学生Id
     * @param courseId  课程Id
     * @param pageable
     * @return
     */
    public List<IArticle> findByUserIdAndCourseIdByCreateTimeDesc(String studentId, String courseId, Pageable pageable) {
        return articleDao.findByUserIdAndCourseIdOrderByCreateTimeDesc(studentId, courseId, pageable).getContent();
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addClickGood(String articleId, String userId) {

//        boolean result=myArticleService.exixtsMyArticle(articleId,userId,myArticleService.GOOD);
//        if(!result){
//            //记录点赞日志记录
//            MyArticle myArticle= myArticleService.setMyArticle("",userId,articleId,myArticleService.GOOD);
//            myArticleService.save(myArticle);
//        }
//
        // 资讯点赞次数 +1
        articleDao.addClickGood(articleId);

        String key = ArticleKey.GOOD.concat(articleId);
        String count = stringRedisTemplate.opsForValue().get(key);
        String countStr = StrUtil.isBlank(count) ? "0" : count;
        int newCount = Integer.parseInt(countStr) + 1;
        stringRedisTemplate.opsForValue().set(key, String.valueOf(newCount));
        return newCount;
    }

    /**
     * 收藏
     *
     * @param articleId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public int addCollectCount(String articleId, String userId) {
        boolean result = myArticleService.exixtsMyArticle(articleId, userId, ArticleKey.SHOUCANGVALUE);
        if (!result) {
            //记录收藏日志记录
            myArticleService.setMyArticle("", userId, articleId, ArticleKey.SHOUCANGVALUE);
        }
        //资讯点赞次数+1
        articleDao.addCollectCount(articleId);
        String key = ArticleKey.SHOUCANG.concat(articleId);
        //if(stringRedisTemplate.hasKey(key)){
        String count = stringRedisTemplate.opsForValue().get(key);
        String countStr = StrUtil.isBlank(count) ? "0" : count;
        int newCount = Integer.parseInt(countStr) + 1;
        stringRedisTemplate.opsForValue().set(key, String.valueOf(newCount));
        return newCount;
        // }

    }

    @Transactional(rollbackFor = Exception.class)
    public String addNice(String articleId, String value) {
        articleDao.addNice(articleId, value);
        return value;
    }

    @Transactional(rollbackFor = Exception.class)
    public String delCollect(String articleId, String userId) {
        return myArticleService.deleteMyArticle(articleId, userId, ArticleKey.SHOUCANGVALUE);
    }

    @Transactional(rollbackFor = Exception.class)
    public String delGood(String articleId, String userId) {
        return myArticleService.deleteMyArticle(articleId, userId, ArticleKey.GOODVALUE);
    }

    @Transactional(rollbackFor = Exception.class)
    public int delMoreByArticleIds(List<String> articleIds) {
        return articleDao.delMoreByArticleIds(articleIds);
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteArticleById(String articleId) {
        return articleDao.deleteArticleById(articleId);
    }

    public List<IArticle> findMyArticle(PageRequest page, String userId, int tagType, String title) {
        List<String> list = myArticleService.findByUserIdTagType(userId, tagType, page);
        if (!list.isEmpty()) {
            if (StrUtil.isNotBlank(title)) {
                // 是搜索查询，先查出全部对应的类型再查对应数据
                return articleDao.findAllByTitleLikeOrderByCreateTimeDesc(userId, tagType, "%" + title + "%", page).getContent();
            }
            return articleDao.findAllByArticleIdInOrderByCreateTimeDesc(list);
        }
        return new ArrayList<>();
    }

//
//    private String replaceImgWidth(String content) {
//        // 宽、高过滤的正则表达式
//        String reg1 = "<img (.*?)height=\"(.*?)\\\"";
//        String reg2 = "<img (.*?)width=\"(.*?)\\\"";
//
//        content = RegexUtils.replaceAll(content, reg1, "<img $1height=\"100%\\\"").toString();
//        content = RegexUtils.replaceAll(content, reg2, "<img $1width=\"100%\\\"").toString();
//
//        return content;
//    }


}
