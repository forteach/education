package com.forteach.education.course.domain.ziliao;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "courseware", indexes = {@Index(columnList = "chapter_id")})
@EqualsAndHashCode(callSuper = true)
public class Courseware extends AbsFile implements Serializable {


}
