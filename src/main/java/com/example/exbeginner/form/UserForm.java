package com.example.exbeginner.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class UserForm {
  @NotBlank(message = "名前は必須です")
  private String name;

  @NotNull(message = "年齢は必須です")
  private String age;

  @NotBlank(message = "コメントは必須です")
  private String comment;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getAge() {
    return age;
  }
  public void setAge(String age) {
    this.age = age;
  }
  public String getComment() {
    return comment;
  }
  public void setComment(String comment) {
    this.comment = comment;
  }  
}
