package com.spring.education;

import lombok.ToString;

@ToString
public class ClassicalMusic implements Music {

    private ClassicalMusic() {}

    public static ClassicalMusic getClassicalMusic (){
        return new ClassicalMusic();
    }

    public void beforeMethod() {
        System.out.println("Doing initialization of ClassicalMusic");
    }

    public void afterMethod() {
        System.out.println("Doing deconstructionist of ClassicalMusic");
    }

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
