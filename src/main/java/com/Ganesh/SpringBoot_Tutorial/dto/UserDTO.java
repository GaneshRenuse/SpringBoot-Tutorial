package com.Ganesh.SpringBoot_Tutorial.dto;

import jakarta.validation.constraints.*;

public class UserDTO {

    private int id;

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Min(value = 18, message = "Age must be 18 and above")
    private int age;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
