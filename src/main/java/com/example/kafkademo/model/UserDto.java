package com.example.kafkademo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data

public class UserDto{
    private Long age;
    private String name;
    private Address address;

    public UserDto() {
    }
    public UserDto(Long age , String name) {
        this.age = age;
        this.name = name;
    }

}
