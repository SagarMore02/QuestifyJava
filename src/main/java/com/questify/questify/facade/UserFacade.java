package com.questify.questify.facade;

import com.questify.questify.Error.InvalidLoginCredentialsException;
import com.questify.questify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacade {

  private final UserRepository userRepository;

  @Autowired
  UserFacade(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void verifyCredentials(String username, String password) {
    userRepository.findByUsernameAndPassword(username, password)
        .ifPresentOrElse(
            user -> System.out.println("User found:"),
            () -> {
              throw new InvalidLoginCredentialsException("Invalid username or password");
            }
        );
  }
}
