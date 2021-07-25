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
    public Assignment addAssignment(Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    @Override
    public FileModule addFile(FileModule fileModule) {
        return fileModuleRepository.save(fileModule);
    }

    @Override
    public Url addUrl(Url url) {
        return urlRepository.save(url);
    }
}
