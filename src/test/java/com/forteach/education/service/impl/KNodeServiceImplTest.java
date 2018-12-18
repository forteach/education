package com.forteach.education.service.impl;

import com.forteach.education.course.domain.KNode;
import com.forteach.education.course.repository.KNodeRepository;
import com.forteach.education.databank.repository.RepositoryImpl;
import com.forteach.education.util.SortUtil;
import com.forteach.education.web.vo.SortVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static com.forteach.education.common.keyword.Dic.TAKE_EFFECT_OPEN;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-12-6 13:45
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class KNodeServiceImplTest {

    @Resource
    private KNodeRepository kNodeRepository;
    @Resource
    private RepositoryImpl repository;
    @Test
    public void save() {
        kNodeRepository.save(KNode.builder()
                .courseId("ff808181677d238701677d2646440000")
                .chapterId("2c91809267462308016746293a2a0002")
                .dataId("ff808181674ef03301674ef0809d0000")
                .kNodeType("1")
                .nodeName("电子商务定义")
                .nodeDesc("电子商务定义")
                .build());
    }

    @Test
    public void edit() {
//        kNodeRepository.ed
    }

    @Test
    public void selectById() {
        SortVo sortVo = new SortVo();
        sortVo.setIsValidated(TAKE_EFFECT_OPEN);
        sortVo.setPage(0);
        sortVo.setSize(10);
        sortVo.setSort(1);
        sortVo.setSorting("cTime");
        Page<KNode> page = repository.findKNodePage(TAKE_EFFECT_OPEN, "ff808181673e5e6c01673e5f792b0001", "", "","1",
                PageRequest.of(sortVo.getPage(), sortVo.getSize(), SortUtil.getSort(sortVo)));
        page.forEach(kNode -> {

        log.info("kNode : {}", kNode.toString());
        });
    }
}