package com.wfh.sp21.lms.mapper;

import com.wfh.sp21.lms.model.CourseModules;
import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.Url;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ModuleMapper implements Serializable {
    private CourseModules courseModules;
    private Assignment assignment;
    private Quiz quiz;
    private FileModule fileModule;
    private Url url;
}
