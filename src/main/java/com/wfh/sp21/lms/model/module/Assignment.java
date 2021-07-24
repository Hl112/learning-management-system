package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wfh_assignment")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Assignment{
    @Id
    private Long assignmentId;
    @Lob
    private String file;
    private Date startDate;
    private Date dueDate;
    private boolean fileSubmission;
    private boolean textSubmission;
    private float maximumGrade;

}
