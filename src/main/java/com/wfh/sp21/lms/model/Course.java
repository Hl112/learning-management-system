package com.wfh.sp21.lms.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "wfh_course")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String fullName;
    private String shortName;
    private Date startDate;
    private Date endDate;
    private String password;
    private String description;
    @Lob
    private String image;
    @ColumnDefault("1")
    private boolean active;
    @ColumnDefault("0")
    private boolean visible;

    @ManyToOne
    @JoinColumn(name = "course_category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private CourseCategory courseCategory;

    @ManyToOne
    @JoinColumn(name = "username")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @OneToMany(mappedBy = "course")
    private Set<UserEnrolments> userEnrolments;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<CourseSections> courseSections;

}
