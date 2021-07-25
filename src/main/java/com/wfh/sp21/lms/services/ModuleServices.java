package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.Url;

public interface ModuleServices {
    Assignment addAssignment(Assignment assignment);
    Quiz addQuiz(Quiz quiz);
    FileModule addFile(FileModule fileModule);
    Url addUrl(Url url);
}
