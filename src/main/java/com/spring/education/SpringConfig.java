package com.spring.education;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan("com.spring.education")
@PropertySource("classpath:musicPlayer.properties")
public class SpringConfig {

}
