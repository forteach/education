package com.forteach.education.databank.repository;

import com.forteach.education.databank.domain.ExerciseBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Auther: zhangyy
 * @Email: zhang10092009@hotmail.com
 * @Date: 18-11-15 15:49
 * @Version: 1.0
 * @Description:　练习册
 */
public interface ExerciseBookRepository extends JpaRepository<ExerciseBook, String> {

}
