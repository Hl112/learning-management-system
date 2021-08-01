package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.QuizQuestion;
import com.wfh.sp21.lms.model.module.QuizQuestionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizQuestionRepository extends JpaRepository<QuizQuestion, QuizQuestionKey> {
    List<QuizQuestion> findAllByQuestion_StatusAndQuiz_QuizId(boolean questionStatus, Long quizId);

}
