package com.wfh.sp21.lms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEnrolmentsKey implements Serializable {
    @Column(name = "username")
    private String username;
    @Column(name = "course_id")
    private Long courseId;
}
