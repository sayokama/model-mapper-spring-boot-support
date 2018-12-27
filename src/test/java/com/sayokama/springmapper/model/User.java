package com.sayokama.springmapper.model;

public class User {

    private String guid;
    private Profile profile;
    private State state;

    public User() {
        this.profile = new Profile();
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public enum State {
        ACTIVE, DISABLED, TERMINATED;
    }
}
