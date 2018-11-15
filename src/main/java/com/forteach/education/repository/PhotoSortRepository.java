package com.forteach.education.repository;

import com.forteach.education.domain.PhotoSort;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 14:58
 * @Version: 1.0
 * @Description: 资料图片册
 * 展示方式 0->仅主人可见,1->输入密码即可查看,2->仅组成员能查看,3->回答问题即可查看''
 */
public interface PhotoSortRepository extends JpaRepository<PhotoSort, String> {

}
