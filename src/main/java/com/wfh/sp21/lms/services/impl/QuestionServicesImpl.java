package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.Question;
import com.wfh.sp21.lms.repository.QuestionRepository;
import com.wfh.sp21.lms.services.QuestionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class QuestionServicesImpl implements QuestionServices {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizQuestionServicesImpl quizQuestionServicesImpl;

    @Autowired
    private QuizServicesImpl quizServicesImpl;

    @Override
    public Question getQuestionById(Long questionId) {
        return questionRepository.findByQuestionId(questionId);
    }

    @Override
    public List<Question> getQuestionsByQuizId(Long quizId) {
        return quizQuestionServicesImpl.getAllQuestionsByQuizId(quizId);
    }

    @Override
    public boolean addUpdateQuestion(Question question, Long quizId) {
        return false;
    }

    @Override
    public boolean deleteQuestion(Long questionId) {
        Question question = questionRepository.getById(questionId);
        question.setStatus(false);
        questionRepository.save(question);
        return true;
    }
}
