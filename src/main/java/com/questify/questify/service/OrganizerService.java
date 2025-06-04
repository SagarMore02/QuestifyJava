package com.questify.questify.service;

import com.questify.questify.Error.TotalQuestionMarksExceededsExamMarksException;
import com.questify.questify.controller.request.ExamRequestDto;
import com.questify.questify.controller.request.QuestionRequestDto;
import com.questify.questify.controller.response.organizer.DashBoardResponse;
import com.questify.questify.controller.response.organizer.QuestionResponse;
import com.questify.questify.domain.exam.Exam;
import com.questify.questify.domain.question.Question;
import com.questify.questify.domain.user.User;
import com.questify.questify.domain.user.UserType;
import com.questify.questify.facade.ApplicationFacade;
import com.questify.questify.facade.ExamFacade;
import com.questify.questify.facade.UserFacade;
import com.questify.questify.repository.QuestionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrganizerService {

  private final ApplicationFacade applicationFacade;
  private final ExamFacade examFacade;
  private final UserFacade userFacade;
  private final QuestionRepository questionRepository;

  @Autowired
  public OrganizerService(ApplicationFacade applicationFacade, ExamFacade examFacade, UserFacade userFacade, QuestionRepository questionRepository) {
    this.applicationFacade = applicationFacade;
    this.examFacade = examFacade;
    this.userFacade = userFacade;
    this.questionRepository = questionRepository;
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

  public QuestionResponse addQuestion(QuestionRequestDto questionRequestDto) {
    Question question = Question.createNew(
        questionRequestDto.examID,
        questionRequestDto.question,
        questionRequestDto.options,
        questionRequestDto.correctOpt,
        questionRequestDto.questionMarks
    );
    long examMarksLimit = examFacade.getExamMarksLimit(questionRequestDto.examID);
    long totalQuestionMarks = questionRepository.findAllByExamId(questionRequestDto.examID)
        .stream()
        .mapToLong(Question::getQuestionMarks)
        .sum();
    if (examMarksLimit < totalQuestionMarks + questionRequestDto.questionMarks) {
      throw new TotalQuestionMarksExceededsExamMarksException(
          "Total question marks exceeded exam marks for exam with ID: " + questionRequestDto.examID);
    } else {
      if(examMarksLimit == totalQuestionMarks + questionRequestDto.questionMarks){
        questionRepository.save(question);
        return new QuestionResponse("Question added successfully", question, examMarksLimit, true,
            totalQuestionMarks + questionRequestDto.questionMarks);
      }
      questionRepository.save(question);
      return new QuestionResponse("Question added successfully", question, examMarksLimit, false,
          totalQuestionMarks + questionRequestDto.questionMarks);
    }
  }

  public ResponseEntity<QuestionResponse> checkTest(long examId) {
    long examMarksLimit = examFacade.getExamMarksLimit(examId);
    List<Question> listOfQuestions = questionRepository.findAllByExamId(examId);
    long totalQuestionMarks = listOfQuestions
        .stream()
        .mapToLong(Question::getQuestionMarks)
        .sum();

    System.out.println("Exam Marks Limit: " + examMarksLimit);
    System.out.println("totalQuestionMarks: " + totalQuestionMarks);
    QuestionResponse questionResponse = new QuestionResponse();
    if (examMarksLimit > totalQuestionMarks) {
      questionResponse.setMessage("Exam Marks are less than Question Total Marks , Exam cannot be created!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionResponse);
    }
    if (listOfQuestions.isEmpty()) {
      questionResponse.setMessage("No Questions found for this exam!");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionResponse);
    }
    questionResponse.setCurrentTotalMarks(examMarksLimit);
    questionResponse.setAttemptedQuestionMarks(totalQuestionMarks);
    return ResponseEntity.ok(questionResponse);

  }

}
