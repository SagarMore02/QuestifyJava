package com.questify.questify.pageController;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PageController {

  @GetMapping
  @ResponseBody
  public Resource getPage() {
    return new ClassPathResource("static/home.html");
  }

  @GetMapping("/applicant-dash")
  @ResponseBody
  public Resource getApplicantDashPage() {return new ClassPathResource("static/html/ApplicantMain.html");}
}
