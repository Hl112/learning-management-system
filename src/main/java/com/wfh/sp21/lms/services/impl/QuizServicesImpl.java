package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.repository.QuizRepository;
import com.wfh.sp21.lms.services.QuizServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuizServicesImpl implements QuizServices {

    @Autowired
    private QuizRepository quizRepository;
    @Override
    public Quiz getQuizById(Long quizId) {
        return quizRepository.findByQuizId(quizId);
    }

    @Override
    public boolean makeStarted(Long quizId) {
        Quiz quiz = quizRepository.findByQuizId(quizId);
        quiz.setStart(true);
        quizRepository.save(quiz);
        return true;
    }
}
