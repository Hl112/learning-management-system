package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Question;

import java.util.List;

public interface QuestionServices {
    Question getQuestionById(Long questionId);
    List<Question> getQuestionsByQuizId(Long quizId);
    boolean addUpdateQuestion(Question question, Long quizId);
    boolean deleteQuestion(Long questionId);
}
