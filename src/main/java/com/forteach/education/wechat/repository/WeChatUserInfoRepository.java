package com.forteach.education.wechat.repository;


import com.forteach.education.wechat.domain.WeChatUserInfo;
import com.forteach.education.wechat.repository.dto.IWeChatUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 19-1-10 17:10
 * @Version: 1.0
 * @Description:
 */
public interface WeChatUserInfoRepository extends JpaRepository<WeChatUserInfo, String> {
    /**
     * 根据微信账号查询绑定学生信息
     *
     * @param openId
     * @return
     */
    @Transactional(readOnly = true)
    List<WeChatUserInfo> findByOpenId(String openId);

    /**
     * 根据学生id查询对应微信登录信息
     *
     * @param studentId
     * @return
     */
    @Transactional(readOnly = true)
    List<WeChatUserInfo> findByStudentId(String studentId);

    /**
     * 查询用户信息
     *
     * @param openId
     * @return
     */
    @Query(value = " select " +
            " w.studentId as studentId, " +
            " s.userName as studentName, " +
            " w.classId as classId, " +
            " c.className as className, " +
            " s.portrait as portrait " +
            " from WeChatUserInfo as w " +
            " left join Classes as c on c.classId = w.classId " +
            " left join StudentEntitys as s on w.studentId = s.id " +
            " where w.isValidated = '0' and s.isValidated = '0' and w.openId = ?1 ")
    @Transactional(readOnly = true)
    IWeChatUserInfo findByIsValidatedEqualsAndOpenId(String openId);
}
