package com.questify.questify.controller.response.organizer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DashBoardResponse {

  long totalStudents;
  long studentsApplied;
  long upcomingExams;
  long pendingApprovals;
}
