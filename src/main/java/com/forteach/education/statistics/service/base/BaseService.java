package com.forteach.education.statistics.service.base;

import cn.hutool.core.util.StrUtil;
import com.forteach.education.statistics.repository.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class BaseService<T> {

    public Page<T> findAllPage(Map<String, Object> map, int page, int size, int sort, BaseRepository baseRepository) {
        Sort.Direction anEnum;
        if (0 == sort) {
            anEnum = Sort.Direction.ASC;
        } else {
            anEnum = Sort.Direction.DESC;
        }
        return baseRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>(16);
            map.keySet().forEach(k -> {
                if (StrUtil.isNotBlank(String.valueOf(map.get(k)))) {
//                    predicatesList.add(criteriaBuilder.equal(root.get(k), map.get(k)));
                    predicatesList.add(criteriaBuilder.like(root.get(k), "%" + map.get(k) + "%"));
                }
            });
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        }, PageRequest.of(page, size, anEnum, "createTime"));
    }


//    public List<ChartColumnarVo> findChatColumnarVo(BaseRepository baseRepository){
//         baseRepository.findAllByIsValidatedEquals(Dic.TAKE_EFFECT_OPEN)
//                .stream()
//                .collect(Collectors.groupingBy(t -> t.getClass()));
//    }
}