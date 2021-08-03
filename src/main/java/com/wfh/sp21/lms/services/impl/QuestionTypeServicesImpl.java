package com.wfh.sp21.lms.services.impl;

import com.wfh.sp21.lms.model.module.QuestionEssay;
import com.wfh.sp21.lms.model.module.QuestionMultichoice;
import com.wfh.sp21.lms.model.module.QuestionTrueFalse;
import com.wfh.sp21.lms.repository.QuestionEssayRepository;
import com.wfh.sp21.lms.repository.QuestionMultichoiceRepository;
import com.wfh.sp21.lms.repository.QuestionTrueFalseRepository;
import com.wfh.sp21.lms.services.QuestionTypeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuestionTypeServicesImpl implements QuestionTypeServices {

    @Autowired
    private QuestionMultichoiceRepository questionMultichoiceRepository;

    @Autowired
    private QuestionTrueFalseRepository questionTrueFalseRepository;

    @Autowired
    private QuestionEssayRepository questionEssayRepository;

    @Override
    public QuestionMultichoice auQuestionMultichoice(QuestionMultichoice questionMultichoice) {
        return questionMultichoiceRepository.save(questionMultichoice);
    }

    @Override
    public QuestionTrueFalse auQuestionTrueFalse(QuestionTrueFalse questionTrueFalse) {
        return questionTrueFalseRepository.save(questionTrueFalse);
    }

    @Override
    public QuestionEssay auQuestionEssay(QuestionEssay questionEssay) {
        return questionEssayRepository.save(questionEssay);
    }
}
