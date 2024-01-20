package com.geektrust.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import com.geektrust.backend.utils.Constant;

@Data
@AllArgsConstructor
public class AllotResponse{
    //<course-registration-id> <email-id> <course-offering-id> <course-name> <instructor> <date-in-ddmmyyyy> <status>
    private String regId;
    private String emailId;
    private String courseId;
    private String courseName;
    private String instructor;
    private String date;
    private String status;
 
    //<course-registration-id> <email-id> <course-offering-id> <course-name> <instructor> <date-in-ddmmyyyy> <status>
    @Override
    public String toString() {
        return ""+regId+Constant.SPACE+emailId+Constant.SPACE+courseId+Constant.SPACE+courseName+Constant.SPACE+instructor+Constant.SPACE+date+Constant.SPACE+status+"";
    } 
}
