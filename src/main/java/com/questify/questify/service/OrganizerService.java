package com.questify.questify.service;

import com.questify.questify.controller.request.ExamRequestDto;
import com.questify.questify.controller.request.QuestionRequestDto;
import com.questify.questify.controller.response.organizer.DashBoardResponse;
import com.questify.questify.domain.exam.Exam;
import com.questify.questify.domain.question.Question;
import com.questify.questify.domain.user.User;
import com.questify.questify.domain.user.UserType;
import com.questify.questify.facade.ApplicationFacade;
import com.questify.questify.facade.ExamFacade;
import com.questify.questify.facade.UserFacade;
import com.questify.questify.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

  private final ApplicationFacade applicationFacade;
  private final ExamFacade examFacade;
  private final UserFacade userFacade;
  private final QuestionRepository QuestionRepository;

  @Autowired
  public OrganizerService(ApplicationFacade applicationFacade, ExamFacade examFacade, UserFacade userFacade, QuestionRepository QuestionRepository) {
    this.applicationFacade = applicationFacade;
    this.examFacade = examFacade;
    this.userFacade = userFacade;
    this.QuestionRepository = QuestionRepository;
  }

  public DashBoardResponse getOrganizerDashboard() {
    User user = userFacade.getLoggedinUser();
    long organizerId = user.getUserId();
    long totalStudents = userFacade.countUsersByType(UserType.APPLICANT);
    long upComingExams = examFacade.countExamsByOrganizerId(organizerId);
    long totalApplications = applicationFacade.countApplicationsByOrganizerId(organizerId);
    long pendingApplications = applicationFacade.countPendingApplications(organizerId);
    return new DashBoardResponse(totalStudents, totalApplications, upComingExams, pendingApplications);
  }


  public Exam saveExam(ExamRequestDto examRequestDto) {
    return examFacade.saveExam(examRequestDto);
  }

  public Question addQuestion(QuestionRequestDto questionRequestDto) {
    Question question = Question.createNew(
        questionRequestDto.examID,
        questionRequestDto.question,
        questionRequestDto.options,
        questionRequestDto.correctOpt,
        questionRequestDto.questionMarks
    );
    return QuestionRepository.save(question);
  }
}
