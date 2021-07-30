package com.wfh.sp21.lms.model.module;

import com.wfh.sp21.lms.model.User;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;

@Entity
@Table(name = "wfh_question")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;
    private String questionName;
    private String questionText;
    private float defaultMark;
    private Date timeCreated;
    private Date timeModified;
    private boolean hidden;
    private int questionType;

    @ManyToOne
    @JoinColumn(name = "create_by")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User createdBy;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "wfh_quiz_question",
            joinColumns = @JoinColumn(name = "question_id"),
    inverseJoinColumns = @JoinColumn(name = "quiz_id"))
    private Collection<Quiz> quizCollection;




    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Collection<QuestionAnswers> answers;

    @OneToOne
    @JoinColumn(name = "q_multichoice_id")
    private QuestionMultichoice questionMultichoice;

    @OneToOne
    @JoinColumn(name = "q_truefalse_id")
    private QuestionTrueFalse questionTrueFalse;

    @OneToOne
    @JoinColumn(name = "q_essay")
    private QuestionEssay questionEssay;
}
