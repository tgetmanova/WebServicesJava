package com.github.spb.tget;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class SpringBasedApp {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(SpringBasedApp.class);
        System.in.read();
    }
}
