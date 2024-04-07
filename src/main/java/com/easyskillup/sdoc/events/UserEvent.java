package com.easyskillup.sdoc.events;

import com.easyskillup.sdoc.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class UserEvent {

    private User user;
    private EventType event;
    private Map<?, ?> data;
}
