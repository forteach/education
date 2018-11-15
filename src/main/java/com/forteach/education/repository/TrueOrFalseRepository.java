package com.forteach.education.repository;

import com.forteach.education.domain.TrueOrFalse;
import org.apache.xmlbeans.impl.jam.JPackage;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:47
 * @Version: 1.0
 * @Description:　判断题
 */
public interface TrueOrFalseRepository extends JpaRepository<TrueOrFalse, String> {

}
