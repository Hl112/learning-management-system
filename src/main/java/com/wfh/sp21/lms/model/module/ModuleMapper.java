package com.wfh.sp21.lms.model.module;

import com.wfh.sp21.lms.model.CourseModules;
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
