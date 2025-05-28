package com.questify.questify.domain.exam;

import com.questify.questify.controller.request.ExamRequestDto;
import com.questify.questify.domain.user.Department;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity(name = "Exam_Master")
@AllArgsConstructor
@Data
public class Exam {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long examId;
  private long organizerId;
  @Enumerated(EnumType.STRING)
  private Department department;
  private String name;
  @Column(name = "app_start_date")
  private LocalDate applicationStartDate;
  @Column(name = "app_end_date")
  private LocalDate applicationEndDate;
  @Column(name = "exam_start_time")
  private LocalTime examStartTime;
  @Column(name = "exam_end_time")
  private LocalTime examEndTime;
  @Column(name = "exam_start_date")
  private LocalDate examStartDate;
  @Column(name = "exam_end_date")
  private LocalDate examEndDate;
  private long totalMarks;
  private long passingMarks;
  @Enumerated(EnumType.STRING)
  private ExamStatus status;
  private long fees;
  private String syllabus;

  public Exam(Department dept, String examTitle, LocalDate appStartDate, LocalDate appEndDate, LocalTime examStartTime, LocalTime examEndTime,
      LocalDate examStartDate, LocalDate examEndDate, long totalMarks, long passingMarks, long fees, String syllabus) {
    this.department = dept;
    this.name = examTitle;
    this.applicationStartDate = appStartDate;
    this.applicationEndDate = appEndDate;
    this.examStartTime = examStartTime;
    this.examEndTime = examEndTime;
    this.examStartDate = examStartDate;
    this.examEndDate = examEndDate;
    this.totalMarks = totalMarks;
    this.passingMarks = passingMarks;
    this.status = ExamStatus.PENDING; // Default status
    this.fees = fees;
    this.syllabus = syllabus;
  }

  public static Exam createNew(ExamRequestDto examRequestDto) {
    return new Exam(examRequestDto.getDept(), examRequestDto.getExamTitle(),
        examRequestDto.getAppStartDate(), examRequestDto.getAppEndDate(),
        examRequestDto.getExamStartTime(), examRequestDto.getExamEndTime(),
        examRequestDto.getExamStartDate(), examRequestDto.getExamEndDate(),
        examRequestDto.getTotalMarks(), examRequestDto.getPassingMarks(),
        examRequestDto.getFees(), examRequestDto.getSyllabus());
  }
}
