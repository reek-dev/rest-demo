package com.example.restdemo.service.impl;

import com.example.restdemo.dto.CreateFeedbackDTO;
import com.example.restdemo.entity.Course;
import com.example.restdemo.entity.Feedback;
import com.example.restdemo.entity.Organisation;
import com.example.restdemo.entity.User;
import com.example.restdemo.exception.NotATeacherException;
import com.example.restdemo.exception.ResourceNotFoundException;
import com.example.restdemo.repository.CourseRepository;
import com.example.restdemo.repository.FeedbackRepository;
import com.example.restdemo.repository.OrganisationRepository;
import com.example.restdemo.repository.UserRepository;
import com.example.restdemo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;
    private final OrganisationRepository organisationRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public CreateFeedbackDTO createFeedback(CreateFeedbackDTO feedbackDTO) {

        Organisation organisation = organisationRepository.findById(feedbackDTO.getOrganisationId())
                .orElseThrow(() -> new ResourceNotFoundException("Organisation", "id", String.valueOf(feedbackDTO.getOrganisationId())));

        User user = userRepository.findById(feedbackDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", String.valueOf(feedbackDTO.getInstructorId())));

        if (!user.getRole().toString().equals("TEACHER"))
            throw new NotATeacherException(feedbackDTO.getInstructorId());

        Course course = courseRepository.findById(feedbackDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", String.valueOf(feedbackDTO.getCourseId())));


        Feedback feedback = new Feedback();
        feedback.setOrganisation(organisation);
        feedback.setInstructor(user);
        feedback.setRating(feedbackDTO.getRating());
        feedback.setCourse(course);
        feedback.setReview(feedbackDTO.getReview());

        feedbackRepository.save(feedback);

        return feedbackDTO;
    }
}
