package com.wfh.sp21.lms.services;

import com.wfh.sp21.lms.model.CourseModules;
import com.wfh.sp21.lms.model.CourseSections;
import com.wfh.sp21.lms.model.module.Assignment;
import com.wfh.sp21.lms.model.module.FileModule;
import com.wfh.sp21.lms.model.module.Quiz;
import com.wfh.sp21.lms.model.module.Url;
import com.wfh.sp21.lms.repository.CourseModulesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class CourseModulesServicesImpl implements CourseModulesServices {

    @Autowired
    private CourseModulesRepository courseModulesRepository;

    @Autowired
    private CourseSectionServicesImpl courseSectionServices;

    @Autowired
    private ModuleServicesImpl moduleServices;

    @Override
    public List<CourseModules> getAllBySectionId(Long sectionId) {
        return courseModulesRepository.findAllByCourseSections_CourseSectionId(sectionId);
    }

    @Override
    public List<CourseModules> getAllVisibleBySectionId(Long sectionId) {
        return courseModulesRepository.findAllByVisibleIsTrueAndCourseSections_CourseSectionId(sectionId);
    }

    @Override
    public boolean addModule(CourseModules courseModules, Long sectionId) {
        CourseSections sections = courseSectionServices.getCourseSectionById(sectionId);
        courseModules.setCourseSections(sections);
        courseModules.setVisible(true);
        courseModulesRepository.save(courseModules);
        return true;
    }

    @Transactional
    @Override
    public boolean addModule(CourseModules courseModules, Long courseSectionId, Object module) {
        CourseSections courseSections = courseSectionServices.getCourseSectionById(courseSectionId);
        courseModules.setCourseSections(courseSections);
        CourseModules afterSave = courseModulesRepository.save(courseModules);
        switch (afterSave.getTypeName()) {
            case "Assignment":
                ((Assignment)module).setAssignmentId(afterSave.getCourseModuleId());
                moduleServices.addAssignment((Assignment) module);
                break;
            case "Quiz":
                ((Quiz)module).setQuizId(afterSave.getCourseModuleId());
                moduleServices.addQuiz((Quiz) module);
                break;
            case "FileModule":
                ((FileModule)module).setFileId(afterSave.getCourseModuleId());
                moduleServices.addFile((FileModule) module);
                break;
            case "Url":
                ((Url)module).setUrlId(afterSave.getCourseModuleId());
                moduleServices.addUrl((Url) module);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public boolean hiddenShowModule(Long courseModuleId) {
        CourseModules courseModules = courseModulesRepository.getById(courseModuleId);
        boolean visible = courseModules.isVisible();
        courseModules.setVisible(!visible);
        courseModulesRepository.save(courseModules);
        return true;
    }

    @Override
    public boolean deleteModule(Long courseModuleId) {
        courseModulesRepository.deleteById(courseModuleId);
        return true;
    }

    @Override
    public boolean updateModule(CourseModules courseModules, Object typeModule) {
        CourseModules courseModulesDB = courseModulesRepository.getById(courseModules.getCourseModuleId());
        courseModulesDB.setName(courseModules.getName());
        courseModulesDB.setDescription(courseModules.getDescription());
        return false;
    }
}
