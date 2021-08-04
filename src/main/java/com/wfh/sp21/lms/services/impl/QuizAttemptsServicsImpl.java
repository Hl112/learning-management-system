package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.CourseModules;
import com.wfh.sp21.lms.model.User;
import com.wfh.sp21.lms.model.module.Question;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.QuizAttempts;
import com.wfh.sp21.lms.repository.QuizAttemptsRepository;
import com.wfh.sp21.lms.services.CourseModulesServices;
import com.wfh.sp21.lms.services.QuizAttemptsServices;
import com.wfh.sp21.lms.services.UserServices;
import com.wfh.sp21.lms.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class QuizAttemptsServicsImpl implements QuizAttemptsServices {

    @Autowired
    private QuizAttemptsRepository quizAttemptsRepository;

    @Autowired
    private UserServices userServices;

    @Autowired
    private CourseModulesServices courseModulesServices;

    @Override
    public QuizAttempts getAttemptsById(Long quizAttemptId) {
        return quizAttemptsRepository.findByQuizAttemptId(quizAttemptId);
    }

    @Override
    public QuizAttempts getOnProgressAttempts(String username, Long quizId) {
        return quizAttemptsRepository.findByUser_UsernameAndQuiz_QuizIdAndFinishedIsFalse(username,quizId);
    }

    @Override
    public List<QuizAttempts> getAllQuizAttempts(String username, Long quizId) {
        return quizAttemptsRepository.findAllByUser_UsernameAndQuiz_QuizId(username, quizId);
    }

    @Override
    public List<QuizAttempts> getAllFinishedQuizAttempts(String username, Long quizId) {
        return quizAttemptsRepository.findAllByUser_UsernameAndQuiz_QuizIdAndFinishedIsTrue(username,quizId);
    }

    @Override
    public QuizAttempts addQuizAttempts(String username, Long quizId, List<Long> listQuestionsID) {
        User user = userServices.getUserByUsername(username);
        CourseModules courseModules = courseModulesServices.getCourseModulesByCourseModulesId(quizId);
        QuizAttempts quizAttempts = QuizAttempts.builder()
                .quiz(courseModules.getQuiz())
                .user(user)
                .timeStart(new Date())
                .finished(false)
                .listQuestions(listQuestionsID.toString())
                .build();

      return  quizAttemptsRepository.save(quizAttempts);

    }

    @Override
    public boolean makeFinished(Long quizAttemptId) {
        QuizAttempts quizAttempts = quizAttemptsRepository.findByQuizAttemptId(quizAttemptId);
        quizAttempts.setFinished(true);
        quizAttempts.setTimeFinish(new Date());
        quizAttemptsRepository.save(quizAttempts);
        return true;
    }
}
