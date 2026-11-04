package com.rankbook.hrms.automation.api.models.requests;

public class EmployeeLocationUpdateDto {
    private Integer locationId;
    private String joinedDate;
    private Integer jobTitleId;

    public EmployeeLocationUpdateDto() {}

    public EmployeeLocationUpdateDto(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(String joinedDate) {
        this.joinedDate = joinedDate;
    }

    public Integer getJobTitleId() {
        return jobTitleId;
    }

    public void setJobTitleId(Integer jobTitleId) {
        this.jobTitleId = jobTitleId;
    }
}