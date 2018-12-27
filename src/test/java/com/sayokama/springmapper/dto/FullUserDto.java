package com.sayokama.springmapper.dto;

public class FullUserDto extends BaseUserDto {

    private String dob;
    private boolean isActive;

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
