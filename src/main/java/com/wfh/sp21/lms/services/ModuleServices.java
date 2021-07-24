package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.Url;

public interface ModuleServices {
    boolean addAssignment(Assignment assignment);
    boolean addQuiz(Quiz quiz);
    boolean addFile(FileModule fileModule);
    boolean addUrl(Url url);
}
