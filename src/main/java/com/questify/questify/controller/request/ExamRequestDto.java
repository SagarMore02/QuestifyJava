package com.questify.questify.controller.request;

import com.questify.questify.domain.user.Department;
import java.time.LocalDate;
import java.time.LocalTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamRequestDto {

  public Department dept;
  public String examTitle;
  public LocalDate appStartDate;
  public LocalDate appEndDate;
  public LocalTime examStartTime;
  public LocalTime examEndTime;
  public LocalDate examStartDate;
  public LocalDate examEndDate;
  public long totalMarks;
  public long passingMarks;
  public long fees;
  public String syllabus;
}
