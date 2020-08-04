package com.thoughtworks.rslist.domain;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class User {

    @NotNull
    @Size(max = 8)
    private String name;

    @NotNull
    private String gender;

    @Max(100)
    @Min(18)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "1\\d{10}")
    private String phone;
    private int vote =10;

    public User(String name, String gender, int age, String email, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }
}
