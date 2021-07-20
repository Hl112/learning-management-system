package com.wfh.sp21.lms.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wfh_course_sections")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CourseSections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseSectionId;
    private String courseSectionName;
    private String courseSummary;
    private boolean visible;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Course course;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "wfh_section_modules",
        joinColumns = @JoinColumn(name = "course_section_id"),
            inverseJoinColumns = @JoinColumn(name = "course_modules_id")
    )
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseModules> courseModules;



}
