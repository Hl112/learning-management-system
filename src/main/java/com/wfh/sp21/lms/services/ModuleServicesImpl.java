package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.Url;
import com.wfh.sp21.lms.repository.AssignmentRepository;
import com.wfh.sp21.lms.repository.FileModuleRepository;
import com.wfh.sp21.lms.repository.QuizRepository;
import com.wfh.sp21.lms.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServicesImpl implements ModuleServices{

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private FileModuleRepository fileModuleRepository;

    @Autowired
    private UrlRepository urlRepository;


    @Override
    public boolean addAssignment(Assignment assignment) {
        assignmentRepository.save(assignment);
        return true;
    }

    @Override
    public boolean addQuiz(Quiz quiz) {
        quizRepository.save(quiz);
        return true;
    }

    @Override
    public boolean addFile(FileModule fileModule) {
        fileModuleRepository.save(fileModule);
        return true;
    }

    @Override
    public boolean addUrl(Url url) {
        urlRepository.save(url);
        return true;
    }
}
