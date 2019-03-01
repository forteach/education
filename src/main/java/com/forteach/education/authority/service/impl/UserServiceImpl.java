package com.forteach.education.authority.service.impl;

import com.forteach.education.authority.domain.SysUsers;
import com.forteach.education.authority.repository.UserRepository;
import com.forteach.education.authority.repository.UserRoleRepository;
import com.forteach.education.authority.service.TokenService;
import com.forteach.education.authority.service.UserService;
import com.forteach.education.authority.web.req.RegisterUserReq;
import com.forteach.education.authority.web.req.UserLoginReq;
import com.forteach.education.classes.domain.Teacher;
import com.forteach.education.classes.repository.TeacherRepository;
import com.forteach.education.common.keyword.WebResult;
import com.forteach.education.util.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-2 17:44
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    /**
     * HMacMD5加密的盐
     */
    @Value("${token.salt}")
    private String salt;

    /**
     * 初始化的用户密码
     */
    @Value("${initialization.password}")
    private String initPassWord;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private TokenService tokenService;

    @Resource
    private TeacherRepository teacherRepository;

    @Override
    public WebResult login(UserLoginReq userLoginReq) {
        SysUsers user = userRepository.findByUserName(userLoginReq.getUserName());
        if (user == null) {
            return WebResult.failException("用户不存在");
        } else if (!user.getPassWord().equals(Md5Util.macMD5(userLoginReq.getPassWord()))) {
            return WebResult.failException("密码错误");
        }
        String token = tokenService.createToken(user.getTeacherId());
        Map<String, Object> map = new HashMap<String, Object>(2);
        map.put("token", token);
        map.put("userId", user.getId());
        return WebResult.okResult(map);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public WebResult registerUser(RegisterUserReq registerUserReq) {
        Optional<Teacher> teacher = teacherRepository.findById(registerUserReq.getTeacherCode());
        if (!teacher.isPresent()) {
            return WebResult.failException("不存在您的信息，请联系管理员");
        }
        //验证是否注册
        SysUsers users = userRepository.findByTeacherId(registerUserReq.getTeacherCode());
        if (users != null) {
            return WebResult.failException("您已经注册过了");
        }
        SysUsers user = new SysUsers();
        user.setPassWord(Md5Util.macMD5(registerUserReq.getPassWord()));
        user.setTeacherId(registerUserReq.getTeacherCode());
        user.setUserName(registerUserReq.getUserName());
        SysUsers sysUsers = userRepository.save(user);
        //分配角色
//        userRoleRepository.save(UserRole.builder().userId(sysUsers.getId()).roleId("").build());
        return WebResult.okResult();
    }

//    public void addTeacher(String teacherId) {
//        teacherRepository.findByIsValidatedEqualsAndUpdateTime(TAKE_EFFECT_OPEN, "")
//                .parallelStream()
//                .filter(t -> StrUtil.isNotBlank(t.getTeacherId()))
//                .map(this::saveAllBuildStudent);
//    }
//
//    private void saveAllBuildStudent(final Teacher teacher) {
//        List<SysUsers> list = new ArrayList<>();
//        list.add(SysUsers.builder()
//                .userName(teacher.getTeacherName())
//                .teacherId(teacher.getTeacherId())
//                .passWord(Md5Util.macMD5(salt.concat(initPassWord)))
//                .build());
//        userRepository.saveAll(list);
//    }
}
