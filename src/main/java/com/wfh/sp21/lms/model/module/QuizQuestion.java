package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
@Entity
@Table(name = "wfh_quiz_question")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class QuizQuestion {
    @EmbeddedId
    private QuizQuestionKey quizQuestionKey;

    @ManyToOne
    @MapsId(value = "quiz_id")
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne
    @MapsId(value = "question_id")
    @JoinColumn(name = "question_id")
    private Question question;
}
