package com.questify.questify.domain.attempt;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Attempt_Master")
public class Attempt {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long attemptId;
  private long examId;
  private long questionId;
  private long applicationId;
  @Column(name = "selected_option")
  @Enumerated(EnumType.STRING)
  private SelectedOption answer;
}
