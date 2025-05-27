package com.questify.questify.service;

import com.questify.questify.domain.exam.Exam;
import com.questify.questify.facade.ExamFacade;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

  private final ExamFacade examFacade;

  @Autowired
  public ExamService(ExamFacade examFacade) {
    this.examFacade = examFacade;
  }

  public List<Exam> getExamsByApplicationId(Long applicationId) {
    return examFacade.getExamFromApplicationId(applicationId);
  }

}
