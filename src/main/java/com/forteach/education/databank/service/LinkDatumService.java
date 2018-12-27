package com.forteach.education.databank.service;

import com.forteach.education.databank.domain.LinkDatum;
import com.forteach.education.common.web.vo.SortVo;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2018/11/20 20:50
 * @Version: 1.0
 * @Description: 链接仓库
 */
public interface LinkDatumService {

    LinkDatum save(LinkDatum linkDatum);

    LinkDatum edit(LinkDatum linkDatum);

    void delete(LinkDatum linkDatum);

    void deleteById(String linkId);

    void deleteIsValidById(String linkId);

    Page<LinkDatum> findAll(SortVo sortVo);

    LinkDatum getLinkDatumById(String linkId);

    List<LinkDatum> findByChapterId(String chapterId);
}
