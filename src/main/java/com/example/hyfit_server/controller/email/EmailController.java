package com.example.hyfit_server.controller.email;

import com.example.hyfit_server.config.response.BaseResponse;
import com.example.hyfit_server.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/email")
@RestController
public class EmailController {

    private final EmailService emailService;


    @PostMapping("/confirm")
    public BaseResponse<String> emailConfirm(@RequestParam String email) throws Exception {

        String confirm = emailService.sendSimpleMessage(email);

        return new BaseResponse<>(confirm);
    }
}
