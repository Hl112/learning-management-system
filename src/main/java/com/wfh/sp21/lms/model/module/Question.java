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
    @JoinColumn(name = "question_category")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private QuestionCategory questionCategory;

    @ManyToOne
    @JoinColumn(name = "create_by")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User createdBy;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private Collection<QuestionAnswers> answers;
}
