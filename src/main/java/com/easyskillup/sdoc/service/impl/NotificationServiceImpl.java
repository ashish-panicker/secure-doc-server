package com.easyskillup.sdoc.service.impl;

import com.easyskillup.sdoc.exceptions.ApiException;
import com.easyskillup.sdoc.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.easyskillup.sdoc.utils.EmailUtils.getNewAccountVerifyMessage;
import static com.easyskillup.sdoc.utils.EmailUtils.getResetPasswordMessage;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private static final String NEW_USER_ACCOUNT_VERIFICATION = "New user account verification Email.";
    private static final String USER_ACCOUNT_PASSWORD_RESET = "User account reset password.";
    private final JavaMailSender mailSender;
    private final Logger logger = LoggerFactory.getLogger(NotificationServiceImpl.class);

    @Value("${EMAIL_VERIFIED_HOST}")
    private String host;

    @Value("${EMAIL_ID}")
    private String fromEmail;

    @Override
    @Async
    public void sendNewAccountNotification(String name, String toEmail, String token) {
        System.err.println(fromEmail);
        try {
            var message = new SimpleMailMessage();
            message.setSubject(NEW_USER_ACCOUNT_VERIFICATION);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setText(getNewAccountVerifyMessage(name, host, token));
            mailSender.send(message);
        } catch (Exception ex) {
            logger.error("Error sending email {}", ex.getMessage());
            throw new ApiException("Cannot send email.");
        }
    }

    @Override
    @Async
    public void sendPasswordResetNotification(String name, String toEmail, String token) {
        try {
            var message = new SimpleMailMessage();
            message.setSubject(USER_ACCOUNT_PASSWORD_RESET);
            message.setFrom(fromEmail);
            message.setTo(toEmail);
            message.setText(getResetPasswordMessage(name, host, token));
            mailSender.send(message);
        } catch (Exception ex) {
            logger.error("Error sending email {}", ex.getMessage());
            throw new ApiException("Cannot send email.");
        }
    }
}
