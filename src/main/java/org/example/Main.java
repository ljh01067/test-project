package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication(scanBasePackages = "org.example")
@MapperScan("org.example.repository")
public class Main {
    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        SpringApplication app = new SpringApplication(Main.class);

        // Dotenv 값 Spring Environment로 전달
        app.setDefaultProperties(Map.of(
                "mail.username", dotenv.get("MAIL_USERNAME"),
                "mail.password", dotenv.get("MAIL_PASSWORD"),
                "company.phone", dotenv.get("COMPANY_PHONE", "전화번호 없음")
        ));

        app.run(args);
    }
}