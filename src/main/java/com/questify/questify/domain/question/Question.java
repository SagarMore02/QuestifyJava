package com.questify.questify.domain.question;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Question_Master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long questionId;
  private long examId;
  private String questionType;
  private String question;
  private String optionA;
  private String optionB;
  private String optionC;
  private String optionD;
  private String optionE;
  private String optionF;
  @Enumerated(EnumType.STRING)
  private AnswerKey answerKey;
  private int questionMarks;

  public Question(long examID, String question, String s, String s1, String s2, String s3, String s4, String s5, String correctOpt,
      int questionMarks) {
    this.examId = examID;
    this.question = question;
    this.optionA = s;
    this.optionB = s1;
    this.optionC = s2;
    this.optionD = s3;
    this.optionE = s4;
    this.optionF = s5;
    this.answerKey = AnswerKey.valueOf(correctOpt);
    this.questionMarks = questionMarks;
  }

  public static Question createNew(long examID, String question, List<String> options, String correctOpt, int questionMarks) {
    return new Question(
        examID,
        question,
        options.get(0),
        options.get(1),
        options.size() > 2 ? options.get(2) : null,
        options.size() > 3 ? options.get(3) : null,
        options.size() > 4 ? options.get(4) : null,
        options.size() > 5 ? options.get(5) : null,
        correctOpt,
        questionMarks
    );
  }
}
