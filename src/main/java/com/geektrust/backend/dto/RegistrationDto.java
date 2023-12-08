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
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseID == null) ? 0 : courseID.hashCode());
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + (isAccepted ? 1231 : 1237);
        result = prime * result + ((regID == null) ? 0 : regID.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
            RegistrationDto other = (RegistrationDto) obj;
        if (courseID == null) {
            if (other.courseID != null)
                return false;
        } else if (!courseID.equals(other.courseID))
            return false;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (isAccepted != other.isAccepted)
            return false;
        if (regID == null) {
            if (other.regID != null)
                return false;
        } else if (!regID.equals(other.regID))
            return false;
        return true;
    }
    @Override
    public String toString() {
        return "SubscriptionDto [courseID=" + courseID + ", emailAddress=" + emailAddress
                + ", isAccepted=" + isAccepted + ", regID=" + regID + "]";
    }

    
}
