package com.thoughtworks.rslist.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    public User(String name, String gender, int age, String email, String phone) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.email = email;
        this.phone = phone;
    }

    @NotNull
    @Size(max = 8)
    private String name;

    @NotNull
    private String gender;

    @Min(18)
    @Max(100)
    private int age;

    @Email
    private String email;

    @Pattern(regexp = "1\\d{10}")
    private String phone;

    private int vote = 10;
}
