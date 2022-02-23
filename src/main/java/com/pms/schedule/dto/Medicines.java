package com.pms.schedule.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Medicines {

    private Integer id;
    private String medicineName;
    private String chemicalComposition;
    private String dateOfExpiry;
    private Long numbersOfTabletsInStock;
    private String targetAilments;
}
