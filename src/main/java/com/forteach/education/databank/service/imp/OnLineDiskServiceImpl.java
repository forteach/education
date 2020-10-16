package com.forteach.education.databank.service.imp;

import com.forteach.education.common.keyword.Dic;
import com.forteach.education.databank.domain.OnLineDisk;
import com.forteach.education.databank.repository.OnLineDiskRepository;
import com.forteach.education.databank.service.OnLineDiskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * @Author: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 2020/10/15 10:08
 * @Version: v1.0
 * @Modified：在线网盘
 * @Description:
 */
@Slf4j
@Service
public class OnLineDiskServiceImpl implements OnLineDiskService {

    private final OnLineDiskRepository onLineDiskRepository;


    public OnLineDiskServiceImpl(OnLineDiskRepository onLineDiskRepository) {
        this.onLineDiskRepository = onLineDiskRepository;
    }

    @Override
    public List<OnLineDisk> list(long pId, String userId) {
        return onLineDiskRepository.findAllByIsValidatedAndPIdAndUserId(Dic.TAKE_EFFECT_OPEN, pId, userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<Long> set) {
        Set<Long> longSet = findList(set);
        onLineDiskRepository.deleteAllByIdIn(longSet);
    }

    private Set<Long> findList(Set<Long> set) {
        List<OnLineDisk> lineDisks = onLineDiskRepository.findAllById(set);
        Set<Long> longSet = lineDisks.stream()
                .map(OnLineDisk::getPId)
                .filter(pId -> 0 != pId)
                .collect(toSet());
        set.addAll(longSet);
        findList(longSet);
        return set;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(OnLineDisk onLineDisk) {
        onLineDiskRepository.save(onLineDisk);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void move(Long id, Long pId) {
        onLineDiskRepository.findById(id).ifPresent(o -> {
            o.setPId(pId);
            onLineDiskRepository.save(o);
        });
    }
}