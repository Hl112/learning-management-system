package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    Question findByQuestionId(Long questionId);
}
