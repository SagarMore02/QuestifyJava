package com.questify.questify.controller.organizer;

import com.questify.questify.controller.request.ExamRequestDto;
import com.questify.questify.controller.request.QuestionRequestDto;
import com.questify.questify.controller.response.RedirectResponse;
import com.questify.questify.controller.response.organizer.DashBoardResponse;
import com.questify.questify.domain.exam.Exam;
import com.questify.questify.domain.question.Question;
import com.questify.questify.service.OrganizerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/questify/organizer")
public class OrganizerController {
private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }
    @GetMapping("/dashboard")
    public DashBoardResponse getOrganizerDashboard() {
        return organizerService.getOrganizerDashboard();
    }

    @PostMapping("/add-exam")
    public RedirectResponse saveExam(@RequestBody ExamRequestDto examRequestDto) {
        Exam exam = organizerService.saveExam(examRequestDto);
        return new RedirectResponse(exam.getExamId()+"", "http://localhost:8080/html/teacher_que.html");
    }
    @PostMapping("/add-question")
    public Question addQuestion(@RequestBody QuestionRequestDto questionRequestDto) {
        return organizerService.addQuestion(questionRequestDto);
    }

}
