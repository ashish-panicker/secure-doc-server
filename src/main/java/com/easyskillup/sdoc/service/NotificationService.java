package com.easyskillup.sdoc.service;

public interface NotificationService {

    void sendNewAccountNotification(String name, String toEmail, String token);

    void sendPasswordResetNotification(String name, String toEmail, String token);
}
