package com.example.exbeginner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.exbeginner.form.UserForm;

@Controller
@RequestMapping("/exam04")
public class Exam04Controller {
  @GetMapping("")
  public String index(UserForm form) {
    return "exam04";
  }

  @PostMapping("/register")
  public String register(@Validated UserForm form, BindingResult result) {
    if(result.hasErrors()) {
      return index(form);
    }
    return "exam04-result";
  }
}
