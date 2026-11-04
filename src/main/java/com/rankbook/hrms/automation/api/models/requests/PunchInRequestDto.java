package com.rankbook.hrms.automation.api.models.requests;

public class PunchInRequestDto {
    private String date;
    private String time;
    private String timezone;
    private String note;

    public PunchInRequestDto() {}
    public PunchInRequestDto(String date, String time, String timezone, String note) {
        this.date = date;
        this.time = time;
        this.timezone = timezone;
        this.note = note;
    }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }

    public String getTimezone() { return timezone; }
    public void setTimezone(String timezone) { this.timezone = timezone; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}