package com.spring.education;

import lombok.ToString;

@ToString
public class ClassicalMusic implements Music {

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
