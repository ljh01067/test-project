package org.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user/main")
    public String mainPage() {
        // templates/main.html 파일을 반환
        return "user/main";
    }
    @GetMapping("/user/site1")
    public String site1Page() {
        // templates/main.html 파일을 반환
        return "user/site1";
    }
    @GetMapping("/user/site2")
    public String site2Page() {
        // templates/main.html 파일을 반환
        return "user/site2";
    }
    @GetMapping("/user/site3")
    public String site3Page() {
        // templates/main.html 파일을 반환
        return "user/site3";
    }
    @GetMapping("/user/site4")
    public String site4Page() {
        // templates/main.html 파일을 반환
        return "user/site4";
    }
}