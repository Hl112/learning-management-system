package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.QuestionMultichoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionMultichoiceRepository extends JpaRepository<QuestionMultichoice, Long> {
}
