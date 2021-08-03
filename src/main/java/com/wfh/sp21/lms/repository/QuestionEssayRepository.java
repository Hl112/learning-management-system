package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.QuestionEssay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionEssayRepository extends JpaRepository<QuestionEssay, Long> {
}
