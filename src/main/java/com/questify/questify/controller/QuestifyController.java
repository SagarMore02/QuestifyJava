package com.questify.questify.controller;

import com.questify.questify.domain.exam.Exam;
import com.questify.questify.service.ExamService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/questify")
public class QuestifyController {
  @Autowired
  private ExamService examService;
  @PostMapping("/notice/exams")
  public List<Exam> getExamsByApplicationId(Long applicationId) {
    return examService.getExamsByApplicationId(applicationId);
  }
}
