package com.forteach.education.config;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-5 13:27
 * @Version: 1.0
 * @Description: 获取当前登录的用户信息用户ID
 */
//@Configuration
//public class UserIdAuditorBean implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Optional<String> empty = Optional.empty();
//        if (authentication == null){
//            return empty;
//        }
//        Object principal = authentication.getPrincipal();
//        if (principal.getClass().isAssignableFrom(SysUsers.class)){
//            return Optional.ofNullable(((SysUsers)principal).getId());
//        }
//        return empty;
//    }
//}