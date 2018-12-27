package com.sayokama.springmapper.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

public class UserUtil {

    public static User generateFullUserActive() {
        User user = new User();

        user.setGuid(UUID.randomUUID().toString());
        user.setState(User.State.ACTIVE);

        Profile profile = user.getProfile();
        profile.setFirstName("firstName");
        profile.setLastName("lastName");
        profile.setDob(LocalDateTime.now());

        return user;
    }


}
