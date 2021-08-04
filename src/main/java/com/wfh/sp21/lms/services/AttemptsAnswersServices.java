package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.AttemptsAnswers;

import java.util.List;

public interface AttemptsAnswersServices {
    AttemptsAnswers getById(Long attemptsAnswersId);
    List<AttemptsAnswers> getByQAttemptAndQuestionId(Long quizAttemptId, Long questionId);
    boolean addUpdateAnswer(AttemptsAnswers attemptsAnswers);
}
