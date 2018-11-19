package com.forteach.education.repository;

import com.forteach.education.domain.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-19 10:29
 * @Version: 1.0
 * @Description: 小组成员
 */
public interface GroupMemberRepository extends JpaRepository<GroupMember, String> {
}
