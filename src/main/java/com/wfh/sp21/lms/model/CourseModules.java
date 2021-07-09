package com.wfh.sp21.lms.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wfh_course_modules")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseModules {
    @Id
    @GeneratedValue
    private Long courseModuleId;

    @ManyToMany(mappedBy = "courseModules")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseSections> courseSections;

}
