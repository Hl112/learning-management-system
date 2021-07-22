package com.wfh.sp21.lms.model;

import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import lombok.*;

import javax.persistence.*;
import java.io.File;
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

    private String name;
    private String description;
    private String typeName;


    @ManyToMany(mappedBy = "courseModules")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseSections> courseSections;

    @OneToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @OneToOne
    @JoinColumn(name = "file_id")
    private FileModule file;

}
