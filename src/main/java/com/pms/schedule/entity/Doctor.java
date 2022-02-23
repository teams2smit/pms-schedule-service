package com.pms.schedule.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Data
@NoArgsConstructor
public class Doctor {

    @Id
    private Integer doctorId;

    @NotBlank(message = "Doctor Name is mandatory")
    private String doctorName;

    @NotBlank(message = "Treating Ailment is mandatory")
    private String treatingAilment;

    @Size(min = 10, max = 10, message = "Contact number is mandatory")
    private String contactNumber;

    @NotBlank(message = "Timings are mandatory")
    private String slotTiming;
}
