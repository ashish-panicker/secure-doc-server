package com.easyskillup.sdoc.events.listeners;

import com.easyskillup.sdoc.events.UserEvent;
import com.easyskillup.sdoc.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEventListener {

    private final NotificationService emailService;

    @EventListener
    public void onUserEvent(UserEvent event) {
        var user = event.getUser();
        var type = event.getEvent();
        switch (type) {
            case REGISTER_ACCOUNT ->
                    emailService.sendNewAccountNotification(user.getFirstName(), user.getEmail(), (String) event.getData().get("key"));
            case RESET_PASSWORD ->
                    emailService.sendPasswordResetNotification(user.getFirstName(), user.getEmail(), (String) event.getData().get("key"));
        }
    }
}
