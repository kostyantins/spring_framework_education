# Spring framework basic education

## Contents:
**[1. Inversion of Control (IoC)](#nversion-of-control-(IoC))**

**[2. Dependency injection](#dependency-injection)**

**[3. Bean scope](#bean-scope)**

**[4. Bean lifecycle (init, destroy, factory methods)](#bean-lifecycle-init-destroy-factory-methods)**

**[5. Spring annotations](#spring-annotations)**

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

### Using XML
- Dependency injection via constructor:

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

- Dependency injection of object via setter method:

```
<bean id="musicPlayer"
      class="com.spring.education.MusicPlayer">
      <property name="music" ref="musicBean"/>
      
       <property name="name" value="Some name"/>
       <property name="volume" value="50"/>
</bean>
```

```
    final var musicPlayerViaSetter = new MusicPlayer();
 
    Music classical = new ClassicalMusic();
    musicPlayerViaSetter.setMusic(classical);

    musicPlayerViaSetter.playMusic();
```

- Dependency injection of variables via setter method

```
    final var musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

    musicPlayer.playMusic();

    System.out.println(musicPlayer.getName());
    System.out.println(musicPlayer.getVolume());
```

- Dependency injection of variables via .properties file

```
musicPlayer.properties
```

```
<context:property-placeholder location="classpath:musicPlayer.properties"/>
```

```
 <property name="musics">
            <list>
                <ref bean="rockMusic"/>
                <ref bean="classicalMusic"/>
            </list>
 </property>
        
 <property name="name" value="${musicPlayer.name}"/>
 <property name="volume" value="${musicPlayer.volume}"/>
```

```
public class MusicPlayer {
    // Music is an interface
    private Music music;
    private List<Music> musics;

    private String name;
    private int volume;

    public void playMusic() {
        System.out.println("Playing: " + music.getSong());
    }

    public void playAllMusic() {
        musics.forEach(music -> System.out.println("Playing: " + music.getSong()));
    }
```

```
public static void main(String[] args) {
        final var context = new ClassPathXmlApplicationContext("applicationContext.xml");

        final var musicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        musicPlayer.playMusic();
        musicPlayer.playAllMusic();

        System.out.println(musicPlayer.getName());
        System.out.println(musicPlayer.getVolume());

        context.close();
    }
```

## Bean scope

 **Scopes:**
 - **singleton** - used by default (all created objects will have the same reference) 
 - **prototype** - creates different objects with different references
 - **request**
 - **session**
 - **global-session**
 
## Bean lifecycle (init, destroy, factory methods)

### Init method
 Starts working on a step of bean initialization (kind of before all action: call resources, files, DB)

### Destroy method
 Same as init but works after all (kind of after all action)

    <bean id="classicalMusic"
          class="com.spring.education.ClassicalMusic"
          init-method="beforeMethod"
          destroy-method="afterMethod"
          factory-method="getClassicalMusic"
    </beans>

```
    private ClassicalMusic() {}

    public static ClassicalMusic getClassicalMusic (){
        return new ClassicalMusic();
    }

    public void beforeMethod(){
        System.out.println("Doing initialization of ClassicalMusic");
    }

    public void afterMethod(){
        System.out.println("Doing deconstructionist of ClassicalMusic");
    }
```
 **NOTE:** Spring doesn't start destroy-method for a bean with prototype scope
 
## Spring annotations
 - **@Component** - pointing Spring to the class that needs to be created as a bean
```
   @Component
   public class ClassicalMusic implements Music {
...
```

