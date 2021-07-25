package com.wfh.sp21.lms.model.module;

import com.wfh.sp21.lms.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wfh_assignment_submission")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignmentSubmission {
    @Id
    private Long assignmentSubmissionId;
    private Date timeCreated = new Date();
    private Date timeModified;


    @ManyToOne
    @JoinColumn(name = "assignment_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "username")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
