package com.wfh.sp21.lms.model.module;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wfh_question_essay")
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class QuestionEssay {
    @Id
    private Long questionId;
    private boolean required;
    private int inputBoxSize = 10;
}
