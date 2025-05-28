package com.questify.questify.controller.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileResponse {
  String username;
  String firstName;
  String lastName;
  String email;
  String mobile;

}
