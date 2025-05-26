package com.questify.questify.controller;

import com.questify.questify.common.dto.LoginDto;
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
  public ResponseEntity<String> checkLoginCred(@RequestBody LoginDto loginDto) {
    loginService.checkLoginCred(loginDto.getUsername(), loginDto.getPassword());
    return ResponseEntity.ok("Login packet received");
  }
}
