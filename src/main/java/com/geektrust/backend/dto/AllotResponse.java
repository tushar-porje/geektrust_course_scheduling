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
