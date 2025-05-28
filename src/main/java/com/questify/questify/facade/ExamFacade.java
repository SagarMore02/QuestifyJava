package com.questify.questify.facade;

import com.questify.questify.controller.request.ExamRequestDto;
import com.questify.questify.domain.exam.Exam;
import com.questify.questify.repository.ExamRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamFacade {
  private final ExamRepository examRepository;
  @Autowired
  public ExamFacade(ExamRepository examRepository) {
    this.examRepository = examRepository;
  }
  public List<Exam> getExamFromApplicationId(Long applicationId) {
    return examRepository.findExamsByApplicationId(applicationId);
  }

  public long countExamsByOrganizerId(long organizerId) {
    return examRepository.countByOrganizerId(organizerId);
  }

  public Exam saveExam(ExamRequestDto examRequestDto) {
    Exam exam = Exam.createNew(examRequestDto);
    return examRepository.save(exam);
  }
}
