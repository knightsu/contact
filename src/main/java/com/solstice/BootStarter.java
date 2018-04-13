package com.solstice;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
//@CrossOrigin(origins = {"http://localhost:8000", "null"})
//@EnableJpaRepositories(basePackages = "com.restbox.jparepository")
public class BootStarter {

    public static void main(String[] args)
    {
        SpringApplication.run(BootStarter.class, "--debug");
    }
}
