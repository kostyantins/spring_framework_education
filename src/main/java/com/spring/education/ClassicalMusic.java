package com.spring.education;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope("singleton")
public class ClassicalMusic implements Music {

    @PostConstruct
    public void beforeMethod() {
        System.out.println("Doing initialization of ClassicalMusic");
    }

    @PreDestroy
    public void afterMethod() {
        System.out.println("Doing deconstructionist of ClassicalMusic");
    }

    @Override
    public String getSong() {
        return "Hungarian Rhapsody";
    }
}
