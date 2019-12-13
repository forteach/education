package com.forteach.education.information.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.common.config.MyAssert;
import com.forteach.education.common.keyword.DefineCode;
import com.forteach.education.information.domain.MyArticle;
import com.forteach.education.information.repository.MyArticleDao;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
public class MyArticleService {

	/** MyArticle TagType 本人发布、收藏、点赞 */
	@Resource
	private MyArticleDao myArticleDao;

	/**
	 *
	 * @param id
	 * @param userId
	 * @param articleId
	 * @param tagType  本人发布 点赞  收藏
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public MyArticle setMyArticle(String id,String userId,String articleId,int tagType) {
	    MyArticle myArticle = new MyArticle();
		if (StrUtil.isNotBlank(id)){
			Optional<MyArticle> optional = myArticleDao.findById(id);
			MyAssert.isFalse(optional.isPresent(), DefineCode.ERR0010, "不存在对应信息");
		}else {
			myArticle = new MyArticle(IdUtil.simpleUUID());
			myArticle.setCreateUser(userId);
		}
		// 获得页面设置的资讯值
		myArticle.setUpdateUser(userId);
		myArticle.setUserId(userId);
		myArticle.setArticleId(articleId);
		myArticle.setTagType(tagType);

		return myArticleDao.saveAndFlush(myArticle);
	}

	/**
	 * 判断我的资讯是否存在
	 * @param articleId
	 * @param userId
	 * @param tagType  我的文章标签类型 0：我发布的 1：我收藏的
	 * @return
	 */
   public boolean exixtsMyArticle(String articleId,String userId,int tagType){
		return myArticleDao.existsByArticleIdAndUserIdAndTagType(articleId,userId,tagType);
   }

	public List<String> findByUserIdTagType(String userId, int tagType, Pageable pageable) {
		List<MyArticle> list = myArticleDao.findAllByUserIdAndTagTypeOrderByCreateTimeDesc(userId, tagType, pageable).getContent();
		return list.stream().filter(Objects::nonNull).map(MyArticle::getArticleId).distinct().collect(toList());
	}


	@Transactional
	public String deleteMyArticle(String articleId,String userId,int tagType) {
		 myArticleDao.deleteByUserIdAndArticleIdAndTagType(userId,articleId,tagType);
		return "Y";
	}
}