package com.rankbook.hrms.automation.api.models.responses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceHistoryDto {
    private List<AttendanceRecordResponseDto> data;

    public List<AttendanceRecordResponseDto> getData() { return data; }
    public void setData(List<AttendanceRecordResponseDto> data) { this.data = data; }
}