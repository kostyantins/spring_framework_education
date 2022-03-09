package com.spring.education;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {

    private final Music music_01;
    private final Music music_02;

    @Autowired
    public MusicPlayer(@Qualifier("classicalMusic") Music music_01,
                       @Qualifier("rockMusic") Music music_02) {
        this.music_01 = music_01;
        this.music_02 = music_02;
    }

    public String playMusic() {
        return "Playing: " + music_01.getSong() + " and " + music_02.getSong();
    }
}
