package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("org.example.repository")
public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        System.setProperty("MAIL_USERNAME", dotenv.get("MAIL_USERNAME"));
        System.setProperty("MAIL_PASSWORD", dotenv.get("MAIL_PASSWORD"));
        System.out.println("📌 MAIL_USERNAME = " + System.getProperty("MAIL_USERNAME"));
        System.out.println("📌 MAIL_PASSWORD = " + System.getProperty("MAIL_PASSWORD"));
        SpringApplication.run(Main.class, args);
    }
}