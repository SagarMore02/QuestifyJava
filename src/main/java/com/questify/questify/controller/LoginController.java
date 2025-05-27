package com.questify.questify.controller;

import com.questify.questify.common.dto.LoginDto;
import com.questify.questify.controller.response.LoginResponse;
import com.questify.questify.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

  private final LoginService loginService;

  @Autowired
  LoginController(LoginService loginService) {
    this.loginService = loginService;
  }

  @PostMapping("/login-packet")
  public ResponseEntity<LoginResponse> checkLoginCred(@RequestBody LoginDto loginDto) {
    return ResponseEntity.ok(loginService.checkLoginCred(loginDto.getUsername(), loginDto.getPassword()));
  }
}
