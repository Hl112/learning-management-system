package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.AttemptsAnswers;
import com.wfh.sp21.lms.model.module.QuestionAnswers;
import com.wfh.sp21.lms.repository.AttemptsAnswersRepository;
import com.wfh.sp21.lms.services.AttemptsAnswersServices;
import com.wfh.sp21.lms.services.QuestionAnswersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttemptsAnswersServicesImpl implements AttemptsAnswersServices{

    @Autowired
    private AttemptsAnswersRepository attemptsAnswersRepository;

    @Autowired
    private QuestionAnswersServices questionAnswersServices;

    @Override
    public AttemptsAnswers getById(Long attemptsAnswersId) {
        return attemptsAnswersRepository.findByAttemptAnswerId(attemptsAnswersId);
    }

    @Override
    public List<AttemptsAnswers> getByQAttemptAndQuestionId(Long quizAttemptId, Long questionId) {
        return attemptsAnswersRepository.findAllByQuizAttempts_QuizAttemptIdAndQuestion_QuestionId(quizAttemptId, questionId);
    }

    @Override
    public boolean addUpdateAnswer(AttemptsAnswers attemptsAnswers) {
        AttemptsAnswers attemptsAnswersDB = null;
        if(attemptsAnswers.getAttemptAnswerId() != null){
            attemptsAnswersDB = attemptsAnswersRepository.getById(attemptsAnswers.getAttemptAnswerId());
            attemptsAnswersDB.setQuizAttempts(attemptsAnswers.getQuizAttempts());
            attemptsAnswersDB.setQuestionAnswers(attemptsAnswers.getQuestionAnswers());
            attemptsAnswersDB.setAnswerTF(attemptsAnswers.isAnswerTF());
            attemptsAnswersDB.setAnswerEssay(attemptsAnswers.getAnswerEssay());
            attemptsAnswersDB.setQuestion(attemptsAnswers.getQuestion());
        }else attemptsAnswersDB = attemptsAnswers;
        attemptsAnswersRepository.save(attemptsAnswersDB);
        return true;
    }
}
