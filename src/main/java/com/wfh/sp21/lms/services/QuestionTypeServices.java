package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.QuestionEssay;
import com.wfh.sp21.lms.model.module.QuestionMultichoice;
import com.wfh.sp21.lms.model.module.QuestionTrueFalse;

public interface QuestionTypeServices {
    QuestionMultichoice auQuestionMultichoice(QuestionMultichoice questionMultichoice);
    QuestionTrueFalse auQuestionTrueFalse(QuestionTrueFalse questionTrueFalse);
    QuestionEssay auQuestionEssay(QuestionEssay questionEssay);
}
