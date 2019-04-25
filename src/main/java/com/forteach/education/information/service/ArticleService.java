package com.forteach.education.information.service;

import java.util.List;
import java.util.Optional;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.classes.domain.Classes;
import com.forteach.education.classes.service.ClassesService;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.images.course.service.ArtIcleImagesService;
import com.forteach.education.information.domain.Article;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.dto.IArticle;
import com.forteach.education.information.repository.ArticleDao;
import com.forteach.education.information.web.req.article.SaveArticleRequest;
import com.forteach.education.information.web.res.article.ArticleStuListResponse;
import com.forteach.education.util.RegexUtils;
import com.forteach.education.util.UpdateUtil;
import com.forteach.education.web.vo.DataDatumVo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ArticleService {

    //资讯信息在首页只显示6条数据
    @Value("${com.yunfeng.pageSize:6}")
    private String articleHomePageSize;

    /**
     * 学生信息前缀
     */
    public static final String STUDENT_ADO = "studentsData$";

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private ArtIcleImagesService artIcleImagesService;

    @Autowired
    private ClassesService classesService;

    @Autowired
    private MyArticleService myArticleService;

    @Resource
    private HashOperations<String, String, String> hashOperations;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Transactional
    public Article save(Article article,List<DataDatumVo> dataList) {

        //保存资讯内容图片列表信息
        if(dataList!=null&&dataList.size()>0){
            //设置列表头图片
            article.setImgUrl(dataList.get(0).getFileUrl());
            boolean saveImg=artIcleImagesService.saveImages(article.getArticleId(),dataList);
            MyAssert.isFalse(saveImg, DefineCode.ERR0013,"保存资料图片失败");
        }

        // 保存资讯信息
        Article art = articleDao.save(article);
        // 返回对象为空，保存失败
        MyAssert.isNull(art, DefineCode.ERR0013,"保存资料内容失败");

        return art;
    }

    /**
     * 根据用户id 从redis 取出名字信息
     *
     * @param id
     * @return
     */
    private String findStudentsName(final String id) {

        String key=STUDENT_ADO.concat(id);
        return hashOperations.get(key, "name");
    }

    /**
     * 从redis 取出头像信息
     *
     * @param id
     * @return
     */
    private String findStudentsPortrait(final String id) {
        return hashOperations.get(STUDENT_ADO.concat(id), "portrait");
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
            String createTime=art.getCreateTime();
            UpdateUtil.copyProperties(request, art);
            art.setCreateTime(createTime);

        }else {
            art = new Article();
            UpdateUtil.copyNullProperties(request, art);
            art.setArticleId(IdUtil.fastSimpleUUID());
            art.setCreateUser(request.getUserId());
            art.setIsNice("false");

            //初始化点收藏、点赞、回复数量数据
            String sckey="SHOUCANG".concat(art.getArticleId());
            String gdkey="GOOD".concat(art.getArticleId());
            String replykey="ARTCOMMENTREPLY".concat(art.getArticleId());
            stringRedisTemplate.opsForValue().set(sckey,"0");
            stringRedisTemplate.opsForValue().set(gdkey,"0");
            stringRedisTemplate.opsForValue().set(replykey,"0");

            //获得班级信息
            Classes cla=classesService.findById(request.getClassId());
            MyAssert.isNull(cla, DefineCode.ERR0013,"班级信息不存在");
            //设置班级名称
            art.setClassName(cla.getClassName());

            //学生名称
            art.setUserName(findStudentsName(request.getUserId()));
            //学生头像
            art.setUserTortrait(findStudentsPortrait(request.getUserId()));


            //记录我的发布信息
            MyArticle myArticle= myArticleService.setMyArticle("",art.getUserId(),art.getArticleId(),myArticleService.FABU);
            myArticleService.save(myArticle);
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
        Optional<Article> obj=articleDao.findById(id);
        MyAssert.isNull(obj,DefineCode.ERR0013,"该信息不存在");
        return obj.get();
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

    /**
     * 设置学生列表是否收藏、发布、点赞
     * @param ar
     * @param articleId
     * @param userId
     */
    public void setStuTagType(ArticleStuListResponse ar,String articleId,String userId){

        //设置是否点赞
        ar.setIsClickGood(String.valueOf(myArticleService.exixtsMyArticle(articleId,userId,myArticleService.GOOD)));
        //设置是否收藏
        ar.setIsCollect(String.valueOf(myArticleService.exixtsMyArticle(articleId,userId,myArticleService.SHOUCANG)));
        //设置是否发布
        ar.setIsMy(String.valueOf(myArticleService.exixtsMyArticle(articleId,userId,myArticleService.FABU)));
    }

    /**
     * 根据学生ID，获得资讯
     *
     * @param studentId  发布资讯的学生
     * @param pageable
     * @return
     */
    public List<IArticle> findByStudentId(String studentId, Pageable pageable) {
        return articleDao.findByUserIdOrderByCreateTimeDesc(studentId, pageable).getContent();
    }

    /**
     *
     * @param studentId   学生Id
     * @param courseId    课程Id
     * @param pageable
     * @return
     */
    public List<IArticle> findByUserIdAndCourseIdByCreateTimeDesc(String studentId,String courseId, Pageable pageable) {
        return articleDao.findByUserIdAndCourseIdOrderByCreateTimeDesc(studentId,courseId, pageable).getContent();
    }

    /**
     * 点赞
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int addClickGood(String articleId,String userId) {

        boolean result=myArticleService.exixtsMyArticle(articleId,userId,myArticleService.GOOD);
        if(!result){
            //记录点赞日志记录
            MyArticle myArticle= myArticleService.setMyArticle("",userId,articleId,myArticleService.GOOD);
            myArticleService.save(myArticle);
        }

        //资讯点赞次数+1
        articleDao.addClickGood(articleId);

        String key="GOOD".concat(articleId);
        //if(stringRedisTemplate.hasKey(key)){
        String count=stringRedisTemplate.opsForValue().get(key);
        int newcount=Integer.valueOf(count).intValue()+1;
        stringRedisTemplate.opsForValue().set(key,String.valueOf(newcount));
        return newcount;
    }

    /**
     * 收藏
     *
     * @param articleId
     * @return
     */
    @Transactional
    public int addCollectCount(String articleId,String userId) {
        boolean result=myArticleService.exixtsMyArticle(articleId,userId,myArticleService.SHOUCANG);
        if(!result){
            //记录收藏日志记录
            MyArticle myArticle= myArticleService.setMyArticle("",userId,articleId,myArticleService.SHOUCANG);
            myArticleService.save(myArticle);
        }

        //资讯点赞次数+1
        articleDao.addCollectCount(articleId);
        String key="SHOUCANG".concat(articleId);
        //if(stringRedisTemplate.hasKey(key)){
            String count=stringRedisTemplate.opsForValue().get(key);
            int newcount=Integer.valueOf(count).intValue()+1;
            stringRedisTemplate.opsForValue().set(key,String.valueOf(newcount));
            return newcount;
       // }

    }

    @Transactional
    public String delCollect(String articleId,String userId) {
        return  myArticleService.deleteMyArticle(articleId,userId,myArticleService.SHOUCANG);
    }

    @Transactional
    public String delGood(String articleId,String userId) {
        return myArticleService.deleteMyArticle(articleId,userId,myArticleService.GOOD);
    }

    @Transactional
    public int delMoreByArticleIds(List<String> articleIds) {
        return articleDao.delMoreByArticleIds(articleIds);
    }

    @Transactional
    public int deleteArticleById(String articleId) {
        return articleDao.deleteArticleById(articleId);
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
