package com.wfh.sp21.lms.model.module;

import com.wfh.sp21.lms.model.CourseModules;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wfh_quiz")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz implements Serializable {
    @Id
    private Long quizId;
    private Date timeOpen;
    private Date timeClose;
    private Date timeLimit;
    private float gradeToPass;
    private int attempt;
    private int gradeMethod;
//    private int questionPerPage;
    private boolean review;
    private boolean shuffleQuestions;
    private String password;
}
