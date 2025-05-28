package com.questify.questify.service;

import com.questify.questify.controller.response.RedirectResponse;
import com.questify.questify.controller.response.ProfileResponse;
import com.questify.questify.domain.user.User;
import com.questify.questify.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserFacade userFacade;
  @Autowired
  UserService(UserFacade userFacade) {
    this.userFacade = userFacade;
  }
  public RedirectResponse checkLoginCred(String username, String password) {
    return userFacade.verifyCredentials(username, password);
  }

  public ProfileResponse getUserProfile() {
    User user = userFacade.getLoggedinUser();
    return new ProfileResponse(user.getUsername(),
        user.getFirstName(),
        user.getLastName(),
        user.getEmail(),
        user.getMobile());
  }
}
