package com.example.restdemo.service;

import com.example.restdemo.dto.CreateFeedbackDTO;
import com.example.restdemo.dto.FeedbackByOrganisationDTO;

import java.util.List;

public interface FeedbackService {

    public CreateFeedbackDTO createFeedback(CreateFeedbackDTO feedbackDTO);

    public List<FeedbackByOrganisationDTO> fetchFeedbackByOrganisation(Long organisationId);

    public List<FeedbackByOrganisationDTO> fetchFeedbackByInstructor(Long instructorId);

    public FeedbackByOrganisationDTO fetchFeedbackById(Long id);
}
