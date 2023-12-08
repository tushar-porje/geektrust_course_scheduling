package com.geektrust.backend.dto;

public class RegistrationDto {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
    
    public RegistrationDto(String regID, String emailAddress, String courseID, boolean isAccepted) {
        this.regID = regID;
        this.emailAddress = emailAddress;
        this.courseID = courseID;
        this.isAccepted = isAccepted;
    }
    
    public RegistrationDto(String emailAddress, String courseID) {
        this.emailAddress = emailAddress;
        this.courseID = courseID;
    }

    
    public String getRegID() {
        return regID;
    }

    public void setRegID(String regID) {
        this.regID = regID;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getCourseID() {
        return courseID;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    @Override
    public String toString() {
        return "SubscriptionDto [courseID=" + courseID + ", emailAddress=" + emailAddress
                + ", isAccepted=" + isAccepted + ", regID=" + regID + "]";
    }

    
}
