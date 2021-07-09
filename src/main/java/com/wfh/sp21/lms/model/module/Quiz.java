package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "wfh_quiz")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;
    private String quizName;
    private String description;
    private Date timeOpen;
    private Date timeClose;
    private Date timeLimit;
    private float gradeToPass;
    private int attempt;
    private int gradeMethod;
    private int questionPerPage;
    private boolean review;
    private boolean shuffleQuestions;
    private String password;
}
