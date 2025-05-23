package com.questify.questify.domain.exam;

import com.questify.questify.domain.user.Department;
import com.questify.questify.domain.user.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity(name = "Exam_Master")
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
}
