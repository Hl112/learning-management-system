package com.wfh.sp21.lms.mapper;

import com.wfh.sp21.lms.model.module.Question;
import com.wfh.sp21.lms.model.module.QuestionEssay;
import com.wfh.sp21.lms.model.module.QuestionMultichoice;
import com.wfh.sp21.lms.model.module.QuestionTrueFalse;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class QuestionMapper implements Serializable {
    private Question question;
    private QuestionMultichoice questionMultichoice;
    private QuestionTrueFalse questionTrueFalse;
    private QuestionEssay questionEssay;
}
