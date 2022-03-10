package com.spring.education;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        //Such class needs to be used just in case of spring .xml configuration
        //final var context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final var context = new AnnotationConfigApplicationContext(SpringConfig.class);

        final var musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getValue());

        context.close();
    }
}
