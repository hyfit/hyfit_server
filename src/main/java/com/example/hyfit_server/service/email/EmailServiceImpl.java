package com.example.hyfit_server.service.email;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:email.properties")
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender emailSender;

//    public static final String ePw = createKey();

    private MimeMessage createMessage(String to, String ePw)throws Exception{
        MimeMessage  message = emailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("HY-FIT [authentication mail]");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1 style='text-align:center'> This is the authentication mail for hy-fit sign up </h1>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h2 style='color:blue;'>Authentication code</h2>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= ePw+"</strong><div><br/> ";
        msgg+= "</div>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("${AdminMail.id}","hyfit Back-end Developer (Yoo Ayoung)"));//보내는 사람

        return message;
    }


    public static String createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 6; i++) { // 인증코드 6자리
            key.append(rnd.nextInt(10)); // 0~9 까지 랜덤
        }

        return key.toString();
    }
    @Override
    public String sendSimpleMessage(String to)throws Exception {
        // TODO Auto-generated method stub
        String ePw = createKey();
        MimeMessage message = createMessage(to,ePw);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}