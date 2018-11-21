package com.forteach.education.util;

import com.forteach.education.web.vo.SortVo;
import org.springframework.data.domain.Sort;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-21 13:44
 * @Version: 1.0
 * @Description: 默认倒叙
 */
public class SortUtil {

    public static Sort getSort(SortVo sortVo){
        Sort sort = null;
        if (0 == sortVo.getSort()){
            sort = new Sort(Sort.Direction.ASC, sortVo.getSorting());
        }else {
            sort = new Sort(Sort.Direction.DESC, sortVo.getSorting());
        }
        return sort;
    }
}
