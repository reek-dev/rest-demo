package com.example.restdemo.service.impl;


import com.example.restdemo.dto.CourseCountResponseDTO;
import com.example.restdemo.dto.CourseDTO;
import com.example.restdemo.dto.CourseListDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.CourseCategory;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.CourseAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.mapper.CourseMapper;
import com.example.restdemo.repository.CourseCategoryRepository;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final CourseCategoryRepository courseCategoryRepository;

    @Override
    public Course createCourse(Course newCourse) {

        if (courseRepository.existsByCourseNameIgnoreCase(newCourse.getCourseName())) {
            throw new CourseAlreadyExistsException(newCourse.getCourseName());
        }

        return courseRepository.save(newCourse);
    }

    //    @Override
//    public List<CourseIdAndNameDTO> getAllCourseIdAndName() {
//        List<Course> possibleCourses = courseRepository.getAllCourses();
//        return possibleCourses.stream()
//                .map(CourseMapper::mapToCourseIdAndNameDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<CoursesListDTO> getAllCoursesList() {
//        List<Course> possibleUsers = courseRepository.getAllCourses();
//        return possibleUsers.stream()
//                .map(CourseMapper::mapToCoursesListDTO)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public CourseDTO getCourseDTOById(Long id) {
//        Course possibleExistingCourse = courseRepository.findById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(id)));
//        return CourseMapper.mapToCourseDTO(possibleExistingCourse);
//    }
//
    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course", "id", String.valueOf(id)));
    }

    @Override
    public Course assignFaculty(Course possibleExistingCourse, User possibleExistingInstructor) {

        Set<User> ins = new HashSet<>();

        ins.add(possibleExistingInstructor);

        System.out.println(ins);
        System.out.println(possibleExistingCourse);

        possibleExistingCourse.setAssociatedUsers(ins);
        System.out.println(possibleExistingCourse);

        return courseRepository.save(possibleExistingCourse);
    }

    @Override
    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    @Override
    public CourseDTO getCourseDtoById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(id)));

        return CourseMapper.mapToCourseDTO(course);

    }

    @Override
    public List<CourseListDTO> fetchCoursesByOrgnisation(Long organisationId) {
        List<Course> course = courseRepository.findCoursesByOrganizationId(organisationId);
        return course.stream()
                .map(CourseMapper::mapToCourseListDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseCountResponseDTO> getCourseCountByCategory(Long organisationId) {

        List<CourseCategory> allCategories = courseCategoryRepository.findCourseCategoriesByOrganizationId(organisationId);

        List<CourseCountResponseDTO> response = new ArrayList<>();

        for (CourseCategory category : allCategories) {
            CourseCountResponseDTO countResponse = new CourseCountResponseDTO();

            countResponse.setCourseCategoryName(category.getCategoryName());
            countResponse.setCourseCount(category.getCourses().size());

            response.add(countResponse);

        }

        return response;
    }
}
