package com.wfh.sp21.lms.model;

import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "wfh_course_modules")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseModules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseModuleId;

    private String name;
    private String description;
    private String typeName;
    private boolean showDescription;
    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "course_section_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseSections courseSections;


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
