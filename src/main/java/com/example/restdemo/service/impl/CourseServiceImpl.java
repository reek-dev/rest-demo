package com.example.restdemo.service.impl;


import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.CourseAlreadyExistsException;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.CourseCategoryRepository;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        System.out.println("in the service layer, received course: " + newCourse);


//        Set<User> possibleInstructors = new HashSet<>();
//
//        for (Long id : instructorIds) {
//
//            System.out.println(id);
//            User possibleInstructor = userRepository.findById(id)
//                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
//
//            System.out.println(possibleInstructor);
//
//            if (!possibleInstructor.getRole().toString().equals("TEACHER"))
//                throw new NotATeacherException(id);
//            else possibleInstructors.add(possibleInstructor);
//            System.out.println(possibleInstructors);
//
//        }

//        System.out.println(possibleInstructors);
//        System.out.println(newCourse);
//
//        if (!possibleInstructors.isEmpty())
//            newCourse.setAssociatedUsers(possibleInstructors);

        System.out.println(newCourse);

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
}
