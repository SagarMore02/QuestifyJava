package com.questify.questify.controller.request;

import com.questify.questify.domain.question.AnswerKey;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionRequestDto {

  public long examID;
  public String question;
  public List<String> options;
  public String correctOpt;
  public int questionMarks;
}
