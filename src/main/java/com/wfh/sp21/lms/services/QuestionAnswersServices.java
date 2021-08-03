package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.QuestionAnswers;

import java.util.List;

public interface QuestionAnswersServices {
    QuestionAnswers getAnswerById(Long questionAnswerId);
    List<QuestionAnswers> getAllAnswerByQuestionId(Long questionId);
    boolean addAnswer(QuestionAnswers questionAnswers);
}
