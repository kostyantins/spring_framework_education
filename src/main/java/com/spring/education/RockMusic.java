package com.spring.education;

import lombok.ToString;

@ToString
public class RockMusic implements Music{

    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
