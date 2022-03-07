package com.spring.education;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MusicPlayer {
    // Music is an interface
    private Music music;
    private List<Music> musics;

    private String name;
    private int volume;

    // IoC via constructor without lombok
//    public MusicPlayer(Music music) {
//        this.music = music;
//    }

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public void playAllMusic() {
        musics.forEach(music -> System.out.println("Playing: " + music.getSong()));
    }
}
