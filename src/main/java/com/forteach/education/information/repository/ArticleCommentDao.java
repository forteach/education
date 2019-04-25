package com.forteach.education.information.repository;

import com.forteach.education.information.domain.ArticleComment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface ArticleCommentDao extends JpaRepository<ArticleComment, String>, JpaSpecificationExecutor<ArticleComment> {

    public ArticleComment findByCommentId(String CommentId);

    /**
     *评论回复
     * @param reContent   回复内容
     * @param commentId   评论Id
     * @param reContenTime  回复时间
     * @param replyUserName  回复人名称
     * @return
     */

    @Modifying
    @Query( "update ArticleComment set reContent=?1 ,reContenTime=?2 ,replyUserName=?3 where commentId=?4")
    public int saveReply( String reContent,String reContenTime,String replyUserName,String commentId);

    /**
     * 评论点赞数量
     * @return
     */
    @Modifying
    @Query("update  ArticleComment  set goodCount=goodCount+1 where commentId =?1")
    public int addGoodCount(String commentId);

    /**
     * 获得资讯评论分页信息
     * @param articleId
     * @param pageable
     * @return
     */
    public Page<ArticleComment> findByArticleIdOrderByCreateTimeDesc(String articleId, Pageable pageable);

}
