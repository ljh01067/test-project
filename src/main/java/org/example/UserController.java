package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/main")
    public String mainPage() {
        // templates/main.html 파일을 반환
        return "main/main";
    }
}