package com.forteach.education.course.repository;

import com.forteach.education.course.domain.CourseShareUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:37
 * @Version: 1.0
 * @Description: 协作成员列表
 */
public interface CourseShareUsersRepository extends JpaRepository<CourseShareUsers, String> {

    /**
     * 删除分型用户
     *
     * @param shareId
     * @return
     */
    @Modifying(clearAutomatically = true)
    @Transactional(rollbackOn = Exception.class)
    public int deleteByShareId(String shareId);

    /**
     * 查询分享的协作成员信息
     *
     * @param shareId
     * @return
     */
    @org.springframework.transaction.annotation.Transactional(readOnly = true)
    public List<CourseShareUsers> findByShareId(String shareId);


}
