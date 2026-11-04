package com.rankbook.hrms.automation.api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceRecordResponseDto {
    private Long id;
    private String punchInUtcTime;
    private String punchOutUtcTime;
    private String punchInNote;
    private String punchOutNote;
    private String state;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getPunchInUtcTime() { return punchInUtcTime; }
    public void setPunchInUtcTime(String punchInUtcTime) { this.punchInUtcTime = punchInUtcTime; }

    public String getPunchOutUtcTime() { return punchOutUtcTime; }
    public void setPunchOutUtcTime(String punchOutUtcTime) { this.punchOutUtcTime = punchOutUtcTime; }

    public String getPunchInNote() { return punchInNote; }
    public void setPunchInNote(String punchInNote) { this.punchInNote = punchInNote; }

    public String getPunchOutNote() { return punchOutNote; }
    public void setPunchOutNote(String punchOutNote) { this.punchOutNote = punchOutNote; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}