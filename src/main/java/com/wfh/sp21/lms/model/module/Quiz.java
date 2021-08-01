package com.wfh.sp21.lms.model.module;

import com.wfh.sp21.lms.model.CourseModules;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

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
    private boolean start = false;
//    private int gradeMethod;
//    private int questionPerPage;
    private boolean review;
    private boolean shuffleQuestions;
    private String password;

    @OneToMany(mappedBy = "quiz")
    @ToString.Exclude
    private Set<QuizQuestion> quizQuestion;

}
