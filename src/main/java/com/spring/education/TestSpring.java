package com.spring.education;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final var musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.playMusic();

        context.close();
    }
}
