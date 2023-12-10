package com.geektrust.backend.dto;


public class AllotResponse implements Comparable<AllotResponse>{
    //<course-registration-id> <email-id> <course-offering-id> <course-name> <instructor> <date-in-ddmmyyyy> <status>
    private String regId;
    private String emailId;
    private String courseId;
    private String courseName;
    private String instructor;
    private String date;
    private String status;

    public AllotResponse(String regId, String emailId, String courseId, String couseName,
            String instructor, String date, String status) {
        this.regId = regId;
        this.emailId = emailId;
        this.courseId = courseId;
        this.courseName = couseName;
        this.instructor = instructor;
        this.date = date;
        this.status = status;
    }
    public String getRegId() {
        return regId;
    }
    public void setRegId(String regId) {
        this.regId = regId;
    }
    public String getEmailId() {
        return emailId;
    }
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
    public String getCourseId() {
        return courseId;
    }
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
    public String getCouseName() {
        return courseName;
    }
    public void setCouseName(String couseName) {
        this.courseName = couseName;
    }
    public String getInstructor() {
        return instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((courseId == null) ? 0 : courseId.hashCode());
        result = prime * result + ((courseName == null) ? 0 : courseName.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + ((emailId == null) ? 0 : emailId.hashCode());
        result = prime * result + ((instructor == null) ? 0 : instructor.hashCode());
        result = prime * result + ((regId == null) ? 0 : regId.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        AllotResponse other = (AllotResponse) obj;
        if (courseId == null) {
            if (other.courseId != null)
                return false;
        } else if (!courseId.equals(other.courseId))
            return false;
        if (courseName == null) {
            if (other.courseName != null)
                return false;
        } else if (!courseName.equals(other.courseName))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (emailId == null) {
            if (other.emailId != null)
                return false;
        } else if (!emailId.equals(other.emailId))
            return false;
        if (instructor == null) {
            if (other.instructor != null)
                return false;
        } else if (!instructor.equals(other.instructor))
            return false;
        if (regId == null) {
            if (other.regId != null)
                return false;
        } else if (!regId.equals(other.regId))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }
    //<course-registration-id> <email-id> <course-offering-id> <course-name> <instructor> <date-in-ddmmyyyy> <status>
    @Override
    public String toString() {
        return ""+regId+" "+emailId+" "+courseId+" "+courseName+" "+instructor+" "+date+" "+status+"";
    }
    @Override
    public int compareTo(AllotResponse allotResponse) {
        return this.regId.compareTo(allotResponse.regId);
    }

    
}
