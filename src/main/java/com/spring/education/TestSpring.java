package com.spring.education;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {

    public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final var music = context.getBean("rockMusic", Music.class);
        final var musicPlayer = new MusicPlayer(music);

        musicPlayer.playMusic();

        final var music_02 = context.getBean("classicalMusic", Music.class);
        final var musicPlayer_02 = new MusicPlayer(music_02);

        musicPlayer_02.playMusic();

        context.close();
    }
}
