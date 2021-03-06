package com.forteach.education.information.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.forteach.education.information.domain.Notice;
import com.forteach.education.information.repository.NoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 简单公告S
 */
@Service
public class NoticeService {


    @Autowired
    private NoticeDao noticeDao;

    @Transactional
    public Notice save(String noticeId, String content, String area) {

        Notice nc = null;
        if (StrUtil.isNotBlank(noticeId)) {
            nc = findById(noticeId);
        } else {
            nc = new Notice();
            nc.setNoticeId(IdUtil.fastSimpleUUID());
        }

        nc.setArea(area);
        nc.setContent(content);
        return noticeDao.save(nc);
    }

    public Notice findById(String id) {
        return noticeDao.findByNoticeId(id);
    }


    public List<Notice> findByIsValidatedDesc(String isVal, Pageable pageable) {
        return noticeDao.findByIsValidatedOrderByCreateTimeDesc(isVal, pageable).getContent();
    }

    @Transactional
    public String deleteByNoticeId(String noticeId) {
        noticeDao.deleteByNoticeId(noticeId);
        return "Y";
    }

}
