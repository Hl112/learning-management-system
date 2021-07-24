package com.wfh.sp21.lms.repository;

import com.wfh.sp21.lms.model.module.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByUrlId(Long urlId);
}
