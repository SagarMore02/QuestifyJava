package com.questify.questify.controller.response.organizer;

import com.questify.questify.domain.question.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionResponse {

  String message;
  Question receivedData;
  long currentTotalMarks;
  boolean triggerSetTest;
  long attemptedQuestionMarks;
}
