package ru.itis.timetable.service;

public interface EmailService {

    void sendMail(String subject, String text, String email);
}
