package com.forteach.education.information.repository;

import com.forteach.education.information.domain.Article;
import com.forteach.education.information.dto.IArticle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {

    @Transactional(readOnly = true)
    public Article findByArticleId(String articleId);

    /**
     * 分页查看资讯信息
     */
    @Transactional(readOnly = true)
    public Page<IArticle> findByUserIdOrderByCreateTimeDesc(String userId, Pageable pageable);

    /**
     * 分页查看资讯信息
     */
    @Transactional(readOnly = true)
    public Page<IArticle> findByCourseIdOrderByCreateTimeDesc(String courseId, Pageable pageable);

    @Transactional(readOnly = true)
    List<IArticle> findAllByArticleIdInOrderByCreateTimeDesc(List<String> articleIds);

    @Query(value = " select " +
            " a.articleId as articleId, " +
            " a.courseId as courseId, " +
            " a.courseName as courseName, " +
            " a.title as title, " +
            " a.description as description, " +
//            " a.imgUrl as imgUrl, " +
            " a.clickCount as clickCount, " +
            " a.collectCount as collectCount, " +
            " a.clickGood as clickGood, " +
            " a.articleType as articleType, " +
            " a.userId as userId, " +
            " a.userName as userName, " +
            " a.userTortrait as userTortrait, " +
            " a.classId as classId, " +
            " a.className as className, " +
            " a.isNice as isNice, " +
            " a.createTime as createTime " +
            " from Article as a where a.isValidated ='0' and a.articleId in " +
            " (select distinct m.articleId from MyArticle as m where m.isValidated = '0' and m.userId = ?1 and m.tagType =?2 ) " +
            " and a.title like ?3 order by a.createTime desc ")
    @Transactional(readOnly = true)
    Page<IArticle> findAllByTitleLikeOrderByCreateTimeDesc(String userId, Integer tagType, String title, Pageable pageable);

    /**
     * 分页查看资讯信息
     */
    @Transactional(readOnly = true)
    public Page<IArticle> findByUserIdAndCourseIdOrderByCreateTimeDesc(String userId, String courseId, Pageable pageable);

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Page<IArticle> findAllByOrderByCreateTimeDesc(Pageable pageable);

    @Transactional(rollbackFor = Exception.class, readOnly = true)
    public Page<IArticle> findAllByTitleContainingOrderByCreateTimeDesc(String title, Pageable pageable);

    @Modifying(clearAutomatically = true)
    @Query("update  Article  set isValidated='1' where articleId in(?1)")
    public int delMoreByArticleIds(List<String> articleIds);

    @Modifying(clearAutomatically = true)
    @Query("update  Article  set isValidated='1' where articleId =?1")
    public int deleteArticleById(String articleId);


    /**
     * 精华
     *
     * @param articleIds
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update  Article  set isNice=?2 where articleId =?1")
    public void addNice(String articleIds, String value);


    /**
     * 收藏数量
     *
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update  Article  set collectCount=collectCount+1 where articleId =?1")
    public int addCollectCount(String articleId);

    /**
     * 点赞数量
     *
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update  Article  set clickGood=clickGood+1 where articleId =?1")
    public int addClickGood(String articleId);

    /**
     * 点击数量
     *
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Query("update  Article  set clickCount=clickCount+1 where articleId =?1")
    public int addClickCount(String articleId);

}
