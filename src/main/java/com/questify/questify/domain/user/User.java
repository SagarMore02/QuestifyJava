package com.questify.questify.domain.user;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "User_Master")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long userId;
  private String username;
  private String password;
  private String firstName;
  private String lastName;
  private String mobile;
  @Enumerated(EnumType.STRING)
  private UserType userType;
  @Enumerated(EnumType.STRING)
  private Department department;
  private String email;
  @Enumerated(EnumType.STRING)
  private Status status;
}
