package com.example.restdemo.controller;

import com.example.restdemo.dto.CreateFeedbackDTO;
import com.example.restdemo.dto.ResponseDTO;
import com.example.restdemo.service.FeedbackService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
