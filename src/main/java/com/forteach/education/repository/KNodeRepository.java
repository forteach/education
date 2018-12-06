package com.forteach.education.repository;

import com.forteach.education.domain.KNode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/12/5 22:03
 * @Version: 1.0
 * @Description:
 */
public interface KNodeRepository extends JpaRepository<KNode, String> {

    Page<KNode> findAll(Specification<KNode> specification, Pageable pageable);

}
