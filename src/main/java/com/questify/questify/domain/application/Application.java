package com.questify.questify.domain.application;

import com.questify.questify.domain.user.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Application_Master")
public class Application {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long personalId;
  private long applicationId;
  private long examId;
  private String adhaarcard;
  @Enumerated(EnumType.STRING)
  private FeeStatus feeStatus;
  private String tokenid;
  @Enumerated(EnumType.STRING)
  private Status appStatus;
  @Enumerated(EnumType.STRING)
  private Attendance attendance;
  private long marks;
}
