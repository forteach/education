package com.forteach.education.information.repository;

import com.forteach.education.information.domain.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface NoticeDao extends JpaRepository<Notice, String>, JpaSpecificationExecutor<Notice> {

    @Transactional(readOnly = true)
    public Notice findByNoticeId(String noticeId);

    @Transactional(readOnly = true)
    public Page<Notice> findByIsValidatedOrderByCreateTimeDesc(String isVal, Pageable pageable);

    @Modifying(clearAutomatically = true)
    public int deleteByNoticeId(String Id);
}
