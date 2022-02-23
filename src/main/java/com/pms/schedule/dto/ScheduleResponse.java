package com.pms.schedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ScheduleResponse {

    private String representativeName;
    private String doctorName;
    private String treatingAilment;
    private String medicines;
    private String slot;
    private String date;
    private String contactNumber;
}
