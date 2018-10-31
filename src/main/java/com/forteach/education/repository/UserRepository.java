package com.forteach.education.repository;

import com.forteach.education.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @author: liu zhenming
 * @version: V1.0
 * @date: 2018/10/31 9:57
 */
public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByIsValidatedEquals(String isValidated, Pageable pageable);

}