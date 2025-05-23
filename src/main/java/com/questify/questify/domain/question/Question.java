package com.questify.questify.domain.question;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Question_Master")
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
}
