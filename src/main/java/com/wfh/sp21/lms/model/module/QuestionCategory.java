package com.wfh.sp21.lms.model.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "wfh_question_category")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class QuestionCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionCategoryId;
    private String categoryInfo;

    @OneToMany(mappedBy = "questionCategory", cascade = CascadeType.ALL)
    private Collection<Question> questions;
}
