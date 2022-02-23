package com.pms.schedule.repository;

import com.pms.schedule.entity.Doctor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class DoctorsRepositoryTest {

    @Autowired
    private DoctorsRepository doctorsRepository;


//    void test_doctors_repository() throws FileNotFoundException {
//
//        Iterable<Doctor> doctorIterable = doctorsRepository.findAll();
//        List<Doctor> listFromDB = new ArrayList<>();
//        List<Doctor> listFromFile = new ArrayList<>();
//        doctorIterable.forEach(listFromDB::add);
//
//        File file = ResourceUtils.getFile("classpath:doctors.csv");
//        Scanner sc = new Scanner(file);
//
//        while (sc.hasNext()) {
//            String line = sc.next();
//            String[] data = line.split(",");
//
//            Doctor doctor = new Doctor();
//            doctor.setDoctorId(Integer.parseInt(data[0]));
//            doctor.setDoctorName(data[1]);
//            doctor.setContactNumber(data[2]);
//            doctor.setTreatingAilment(data[3]);
//            doctor.setSlotTiming(data[4]);
//
//            listFromFile.add(doctor);
//        }
//
//        assertEquals(listFromFile, listFromDB);
//
//    }
}