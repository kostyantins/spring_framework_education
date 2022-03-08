package com.spring.education;

import lombok.ToString;
import org.springframework.stereotype.Component;

@ToString
@Component
public class RockMusic implements Music{

    @Override
    public String getSong() {
        return "Wind cries Mary";
    }
}
