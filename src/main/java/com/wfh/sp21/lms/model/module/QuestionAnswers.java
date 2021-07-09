package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "wfh_question_answers")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuestionAnswers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionAnswersId;
    private String answer;
    private String feedback;
    private boolean correct;
    private int position;
    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

}
