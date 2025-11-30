package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final JavaMailSender mailSender;

    @PostMapping("/contact/send")
    public String sendMail(@RequestParam String name,
                           @RequestParam String contact,
                           @RequestParam String email,
                           @RequestParam String body,
                           RedirectAttributes redirectAttributes) {

        String to = System.getProperty("MAIL_USERNAME");

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("[CONTACT 요청] " + name + "님의 메시지");
        message.setText(
                "📨 Contact Form 메시지\n\n" +
                        "이름 : " + name + "\n" +
                        "연락처 : " + contact + "\n" +
                        "이메일 : " + email + "\n\n" +
                        "내용 : \n" + body
        );

        mailSender.send(message);

        // 성공 메시지를 Flash Attribute로 전달
        redirectAttributes.addFlashAttribute("successMessage", "메일이 성공적으로 전송되었습니다!");

        return "redirect:/user/contact"; // 리다이렉트
    }
}