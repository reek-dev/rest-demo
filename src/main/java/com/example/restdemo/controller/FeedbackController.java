package com.example.restdemo.controller;

import com.example.restdemo.dto.CreateFeedbackDTO;
import com.example.restdemo.dto.FeedbackByOrganisationDTO;
import com.example.restdemo.dto.ResponseDTO;
import com.example.restdemo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
@RequiredArgsConstructor
public class FeedbackController {

    private final FeedbackService feedbackService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO<String>> createFeedback(
            @RequestBody CreateFeedbackDTO feedbackDTO
            ) {
        CreateFeedbackDTO feedback = feedbackService.createFeedback(feedbackDTO);

        ResponseDTO<String> response =
                ResponseDTO.<String>builder()
                        .status("success")
                        .statusCode(HttpStatus.CREATED.value())
                        .message("thanks for your feedback.")
                        .data("feedback created.")
                        .build();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/getAllByOrg/{organisationId}")
    public ResponseEntity<ResponseDTO<List<FeedbackByOrganisationDTO>>> fetchFeedbackByOrganisation(
            @PathVariable("organisationId") Long organisationId
    ) {

        List<FeedbackByOrganisationDTO> feedbackByOrganisationDTOs = feedbackService.fetchFeedbackByOrganisation(organisationId);

        ResponseDTO<List<FeedbackByOrganisationDTO>> response =
                ResponseDTO.<List<FeedbackByOrganisationDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("feedbacks fetched successfully.")
                        .data(feedbackByOrganisationDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/getAllByIns/{instructorId}")
    public ResponseEntity<ResponseDTO<List<FeedbackByOrganisationDTO>>> fetchFeedbackByInstructor(
            @PathVariable("instructorId") Long instructorId
    ) {
        List<FeedbackByOrganisationDTO> feedbackByOrganisationDTOs = feedbackService.fetchFeedbackByInstructor(instructorId);

        ResponseDTO<List<FeedbackByOrganisationDTO>> response =
                ResponseDTO.<List<FeedbackByOrganisationDTO>>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("feedbacks fetched successfully.")
                        .data(feedbackByOrganisationDTOs)
                        .build();

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping("/getFeedback/{feedbackId}")
    public ResponseEntity<ResponseDTO<FeedbackByOrganisationDTO>> fetchFeedbackById(
            @PathVariable("feedbackId") Long feedbackId
    ) {
        FeedbackByOrganisationDTO feedbackByOrganisationDTO = feedbackService.fetchFeedbackById(feedbackId);

        ResponseDTO<FeedbackByOrganisationDTO> response =
                ResponseDTO.<FeedbackByOrganisationDTO>builder()
                        .status("success")
                        .statusCode(HttpStatus.OK.value())
                        .message("feedbacks fetched successfully.")
                        .data(feedbackByOrganisationDTO)
                        .build();
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
