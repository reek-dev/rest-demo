package com.example.restdemo.service.impl;


import com.example.restdemo.dto.*;
import com.example.restdemo.entity.*;
import com.example.restdemo.exception.CourseAlreadyExistsException;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.exception.ResourceAlreadyExistsException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.mapper.CourseMapper;
import com.example.restdemo.repository.CourseCategoryRepository;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    private final OrganisationRepository organisationRepository;

    @Override
    public CreateCourseDTO createCourse(CreateCourseDTO courseDTO) {

        Organisation organisation = organisationRepository.findById(courseDTO.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(courseDTO.getOrganisationId())));

        for (Course course : organisation.getCourses()) {
            if (course.getCourseName().equals(courseDTO.getCourseName()))
                throw new ResourceAlreadyExistsException("Course", "name", courseDTO.getCourseName());
        }

        CourseCategory category = courseCategoryRepository.findById(courseDTO.getCourseCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(courseDTO.getCourseCategoryId())));

        Set<User> possibleInstructors = new HashSet<>();

        for (Long id : courseDTO.getInstructorIds()) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
            if (!user.getRole().toString().equals("TEACHER"))
                throw new NotATeacherException(id);
            else possibleInstructors.add(user);
        }

        String levelString = courseDTO.getCourseLevel().trim().toUpperCase();

        CourseLevel level = switch (levelString) {
            case "BEGINNER" -> CourseLevel.BEGINNER;
            case "INTERMEDIATE" -> CourseLevel.INTERMEDIATE;
            case "ADVANCED" -> CourseLevel.ADVANCED;
            case "UG" -> CourseLevel.UG;
            case "PG" -> CourseLevel.PG;
            default -> null;
        };

        String formatString = courseDTO.getCourseFormat().trim().toUpperCase();

        CourseFormat format = switch(formatString) {
            case "ONLINE" -> CourseFormat.ONLINE;
            case "OFFLINE" -> CourseFormat.OFFLINE;
            case "HYBRID" -> CourseFormat.HYBRID;
            default -> null;
        };

        Course newCourse = new Course();
        newCourse.setOrganisation(organisation);
        newCourse.setCourseName(courseDTO.getCourseName());
        newCourse.setCategory(category);
        newCourse.setAssociatedUsers(possibleInstructors);
        newCourse.setCourseDescription(courseDTO.getCourseDescription());
        newCourse.setCourseDuration(courseDTO.getCourseDuration());
        newCourse.setCourseLevel(level);
        newCourse.setCourseFees(courseDTO.getCourseFees());
        newCourse.setEnrollment(courseDTO.getEnrollment());
        newCourse.setPrerequisites(courseDTO.getPrerequisites());
        newCourse.setCourseFormat(format);
        newCourse.setStartDate(LocalDate.parse(courseDTO.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        newCourse.setEndDate(LocalDate.parse(courseDTO.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));


        courseRepository.save(newCourse);
        return courseDTO;
    }

    @Override
    public CourseDTO updateCourse(CourseDTO courseDTO) {

        Course course = courseRepository.findById(courseDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(courseDTO.getCourseId())));

        CourseCategory category = courseCategoryRepository.findById(courseDTO.getCourseCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", String.valueOf(courseDTO.getCourseCategoryId())));

        String levelString = courseDTO.getCourseLevel().trim().toUpperCase();

        CourseLevel level = switch (levelString) {
            case "BEGINNER" -> CourseLevel.BEGINNER;
            case "INTERMEDIATE" -> CourseLevel.INTERMEDIATE;
            case "ADVANCED" -> CourseLevel.ADVANCED;
            case "UG" -> CourseLevel.UG;
            case "PG" -> CourseLevel.PG;
            default -> null;
        };

        Set<User> possibleInstructors = new HashSet<>();

        for (Long id : courseDTO.getInstructorId()) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(id)));
            if (!user.getRole().toString().equals("TEACHER"))
                throw new NotATeacherException(id);
            else possibleInstructors.add(user);
        }

        String formatString = courseDTO.getCourseFormat().trim().toUpperCase();

        CourseFormat format = switch(formatString) {
            case "ONLINE" -> CourseFormat.ONLINE;
            case "OFFLINE" -> CourseFormat.OFFLINE;
            case "HYBRID" -> CourseFormat.HYBRID;
            default -> null;
        };

        course.setCourseName(courseDTO.getCourseName());
        course.setCategory(category);
        course.setCourseDescription(courseDTO.getCourseDescription());
        course.setCourseDuration(courseDTO.getCourseDuration());
        course.setCourseLevel(level);
        course.setCourseFees(courseDTO.getCourseFees());
        course.setEnrollment(courseDTO.getEnrollment());
        course.setPrerequisites(courseDTO.getPrerequisites());
        course.setAssociatedUsers(possibleInstructors);
        course.setCourseFormat(format);
        course.setStartDate(LocalDate.parse(courseDTO.getStartDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        course.setEndDate(LocalDate.parse(courseDTO.getEndDate(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));

        Course updatedCourse = courseRepository.save(course);

        return CourseMapper.mapToCourseDTO(updatedCourse);
    }

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

    @Override
    public List<CourseIdAndNameDTO> getCourseByInstructor(Long instructorId) {

        User user = userRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(instructorId)));

        if (!user.getRole().toString().equals("TEACHER"))
            throw new NotATeacherException(instructorId);

        List<Course> courseByInstructor = courseRepository.findCourseByInstructor(instructorId);

        return courseByInstructor.stream()
                .map(CourseMapper::mapToCourseIdAndNameDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseByFeedbackDTO getCourseByFeedback(Long feedbackId) {
        Course course = courseRepository.fetchCourseDetailsByFeedback(feedbackId);
        return CourseMapper.mapToCourseByFeedbackDTO(course);
    }
}
