package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.QuestionTrueFalse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionTrueFalseRepository extends JpaRepository<QuestionTrueFalse, Long> {
}
