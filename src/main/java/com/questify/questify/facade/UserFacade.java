package com.questify.questify.facade;

import com.questify.questify.Error.InvalidLoginCredentialsException;
import com.questify.questify.Error.UserNotActiveException;
import com.questify.questify.controller.response.RedirectResponse;
import com.questify.questify.domain.user.Status;
import com.questify.questify.domain.user.User;
import com.questify.questify.domain.user.UserType;
import com.questify.questify.repository.UserRepository;
import com.questify.questify.security.JwtService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserFacade {

  private final JwtService jwtService;
  private final UserRepository userRepository;

  @Autowired
  UserFacade(UserRepository userRepository, JwtService jwtService) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
  }

  public RedirectResponse verifyCredentials(String username, String password) {
    User user = userRepository.findByUsernameAndPassword(username, password)
        .orElseThrow(() -> new InvalidLoginCredentialsException("Invalid username or password"));

    if (!user.getStatus().equals(Status.Active)) {
      throw new UserNotActiveException("User is not active");
    }
    String redirectUrl;
    switch (user.getUserType()) {
      case APPLICANT -> redirectUrl = "http://localhost:8080/applicant-dash";
      case ORGANIZER -> redirectUrl = "http://localhost:8080/html/OrganizerDash.html";
      case ORGANIZATION -> redirectUrl = "http://localhost:8080/html/OrganizationFrame.html";
      default -> throw new IllegalStateException("Unexpected value: " + user.getUserType());
    }

    String token = jwtService.generateToken(username);
    return new RedirectResponse(token, redirectUrl);
  }

  public User getLoggedinUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    Optional<User> user = userRepository.findByUsername(authentication.getName());
    if (user.isEmpty()) {
      throw new UsernameNotFoundException("User not found");
    }
    return user.get();
  }

  public long countUsersByType(UserType userType) {
    return userRepository.countByUserType(userType);
  }

}
