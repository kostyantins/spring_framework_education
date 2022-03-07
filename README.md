# Spring framework basic education

## Inversion of Control (IoC)

 - Manual inversion of control
```
public class MusicPlayer {
    // Music an interface
    private final Music music;

    // IoC
    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playMusic() {
        System.out.println("Playing:" + music.getSong());
    }
}
```
 - Execution
```
    final var music = context.getBean("musicBean", Music.class);
    MusicPlayer musicPlayer = new MusicPlayer(music);
    musicPlayer.playMusic();
```

## Dependency injection

- Using XML

```
     <bean id="musicBean"
          class="com.spring.education.RockMusic">
    </bean>

    <bean id="musicPlayer"
          class="com.spring.education.MusicPlayer">

        <constructor-arg ref="musicBean"/>
    </bean>
```

```
    MusicPlayer musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
    musicPlayer.playMusic();
```
