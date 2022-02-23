package com.pms.schedule.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Representative {

    @Id
    @GeneratedValue
    private int id;
    private String representativeName;
    private String ailment;
}
