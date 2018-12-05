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
//        SecurityContext ctx = SecurityContextHolder.getContext();
//        Optional<String> empty = Optional.empty();
//        if (ctx == null || ctx.getAuthentication() == null || ctx.getAuthentication().getPrincipal() == null){
//            return empty;
//        }
//        Object principal = ctx.getAuthentication().getPrincipal();
//        if (principal.getClass().isAssignableFrom(String.class)){
//            return Optional.of(Optional.ofNullable(String.valueOf(principal))
//                    .orElseThrow(IllegalArgumentException::new));
//        }
//        return empty;
//    }
//}
