package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.Question;
import com.wfh.sp21.lms.model.module.QuizQuestion;
import com.wfh.sp21.lms.repository.QuizQuestionRepository;
import com.wfh.sp21.lms.services.QuizQuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuizQuestionServicesImpl implements QuizQuestionServices {

    @Autowired
    private QuizQuestionRepository questionRepository;

    @Override
    public List<Question> getAllQuestionsByQuizId(Long quizId) {
        List<QuizQuestion> list = questionRepository.findAllByQuestion_StatusAndQuiz_QuizId(true,quizId);
        return list.stream().map(QuizQuestion::getQuestion).collect(Collectors.toList());
    }

    @Override
    public List<QuizQuestion> getAllByQuizId(Long quizId) {
        return questionRepository.findAllByQuestion_StatusAndQuiz_QuizId(true,quizId);
    }
}
