package com.example.vaccnow.util;

public interface EmailService {
    void sendSimpleMessage(String to, String subject, String text);
}
