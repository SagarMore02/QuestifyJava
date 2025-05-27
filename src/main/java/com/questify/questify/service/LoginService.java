package com.questify.questify.service;

import com.questify.questify.controller.response.LoginResponse;
import com.questify.questify.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
  private final UserFacade userFacade;
  @Autowired
  LoginService(UserFacade userFacade) {
    this.userFacade = userFacade;
  }
  public LoginResponse checkLoginCred(String username, String password) {
    return userFacade.verifyCredentials(username, password);
  }
}
