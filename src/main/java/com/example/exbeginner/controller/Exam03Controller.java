package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("/exam03")
public class Exam03Controller {
  @Autowired
  ServletContext application;

  @GetMapping("")
  public String index() {
    return "exam03";
  }

  @PostMapping("/result")
  public String result(Integer item1, Integer item2, Integer item3) {
    Integer sum = item1 + item2 + item3;
    application.setAttribute("sum", sum);

    Integer tax1 = item1 + (item1 / 10);
    Integer tax2 = item2 + (item2 / 10);
    Integer tax3 = item3 + (item3 / 10);
    Integer inTax = tax1 + tax2 + tax3;
    application.setAttribute("tax", inTax);
    
    return "exam03-result";
  }
}
