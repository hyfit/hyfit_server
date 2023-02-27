package com.example.hyfit_server.service.email;

public interface EmailService {
    String sendSimpleMessage(String to)throws Exception;
}