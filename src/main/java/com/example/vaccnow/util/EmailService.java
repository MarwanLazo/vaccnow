package com.example.vaccnow.util;

public interface EmailService {
    void sendMessage(String to, String from, String subject, String text);
}
