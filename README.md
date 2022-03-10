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

- **@Component** - pointing Spring to the class that needs to be created as a bean/object

For the Spring to be able to looking for components annotations we need to add the line to the applicationContext.xml

```
    <context:component-scan base-package="com.spring.education"/>
```

```
   @Component
   public class ClassicalMusic implements Music {
...
```

- **@Autowired** - dependency injection using annotation development. Such annotation can be used for variables, setter
  methods, constructors
  **NOTE:** Throws an exception when we have two beans applied for dependency injection

```
   @Autowired
   private Music music;
```

```
   @Autowired
   public MusicPlayer(Music music) {
        this.music = music;
    }
    
    or with Lombok
    
    @AllArgsConstructor(onConstructor = @__(@Autowired))
```

```
   @Setter(onMethod=@__({@Autowired}))
   @Setter(onParam = @__({@Autowired}))
```

- **@Qualifier** - to say what expectantly bean needs to be used. Such annotation can be used for variables, setter
  methods, constructors

```
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
...
```

- **@Scope** - the scope of beans

```
@Scope("singleton")
@Scope("prototype")
```

- **@Value** - spring properties values

```
@Value("${musicPlayer.name}")
private String name;
@Value("${musicPlayer.value}")
private int value;
```

- **@PostConstruct** - before method

```
@PostConstruct
public void beforeMethod() {
        System.out.println("Doing initialization of ClassicalMusic");
   }
```

- **@PreDestroy** - after method

```
@PreDestroy
public void afterMethod() {
        System.out.println("Doing deconstructionist of ClassicalMusic");
   }
```

- **@Configuration** - equals applicationContext.xml

- **@ComponentScan** - equals <context:component-scan base-package="com.spring.education"/>

```
@ComponentScan("com.spring.education")
```

- **@PropertySource** - equals <context:property-placeholder location="classpath:musicPlayer.properties"/>

```
@PropertySource("classpath:musicPlayer.properties")
```