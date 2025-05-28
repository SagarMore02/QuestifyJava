package com.questify.questify.controller;

import com.questify.questify.controller.request.LoginDto;
import com.questify.questify.controller.response.RedirectResponse;
import com.questify.questify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginLogoutController {

  private final UserService loginService;

  @Autowired
  LoginLogoutController(UserService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login-packet")
  public ResponseEntity<RedirectResponse> checkLoginCred(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(loginService.checkLoginCred(loginDto.getUsername(), loginDto.getPassword()));
  }
}
