package com.questify.questify.service;

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
  public void checkLoginCred(String username, String password) {
    userFacade.verifyCredentials(username, password);
  }
}
