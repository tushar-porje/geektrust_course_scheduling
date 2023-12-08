package com.geektrust.backend.model;

public class Registration {
    private String regID;
    private final String emailAddress;
    private final String courseID;
    private boolean isAccepted;
    
    public Registration(String regID, String emailAddress, String courseID, boolean isAccepted) {
        this.regID = regID;
        this.emailAddress = emailAddress;
        this.courseID = courseID;
        this.isAccepted = isAccepted;
    }
    public String getRegID() {
        return regID;
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
   
    @Override
    public String toString() {
        return "Subcription [courseID=" + courseID + ", emailAddress=" + emailAddress
                + ", isAccepted=" + isAccepted + ", regID=" + regID + "]";
    }

    
}
