package com.questify.questify.controller;

import com.questify.questify.controller.response.ProfileResponse;
import com.questify.questify.domain.exam.Exam;
import com.questify.questify.service.ExamService;
import com.questify.questify.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/questify")
public class QuestifyController {

  private final ExamService examService;
  private final UserService userService;

  @Autowired
  public QuestifyController(ExamService examService, UserService userService) {
    this.examService = examService;
    this.userService = userService;
  }

  @PostMapping("/notice/exams")
  public List<Exam> getExamsByApplicationId(Long applicationId) {
    return examService.getExamsByApplicationId(applicationId);
  }

  @GetMapping("/profile")
  public ProfileResponse getProfile() {
    return userService.getUserProfile();
  }
}
