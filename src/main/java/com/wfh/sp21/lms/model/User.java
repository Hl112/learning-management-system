package com.wfh.sp21.lms.model;

import com.sun.istack.NotNull;
import com.wfh.sp21.lms.model.module.AssignmentSubmission;
import com.wfh.sp21.lms.model.module.Question;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "wfh_user")

@AllArgsConstructor
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Builder
public class User {
    @Id
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String email;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String phone;
    private String address;
    private String description;
    private byte[] image;
    @Column(columnDefinition = "boolean default true")
    @NotNull
    private boolean status = true;
    private Date joinedDate = new Date();

    @ManyToOne
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Role role;


    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<UserEnrolments> userEnrolments;

    @OneToMany(mappedBy = "createdBy")
    @ToString.Exclude
    private Set<Question> listQuestions;

    @OneToMany(mappedBy = "user")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Set<Course> listCourses;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        User user = (User) o;

        return Objects.equals(username, user.username);
    }

    @Override
    public int hashCode() {
        return 562048007;
    }

    //Submission
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Collection<AssignmentSubmission> assignmentSubmissions;
}
