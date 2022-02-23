package com.pms.schedule.config;

import com.pms.schedule.entity.Doctor;
import com.pms.schedule.entity.Representative;
import com.pms.schedule.repository.DoctorsRepository;
import com.pms.schedule.repository.RepresentativeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/* doctor details are loaded into the application from the doctor.csv file */

@Component
public class DBInit {

    private static final Logger logger = LoggerFactory.getLogger(DBInit.class);

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @PostConstruct
    void initRep() {
        logger.info("[schedule-service] [db-init] representative data initialization start");

        Representative rep1 = new Representative();
        rep1.setRepresentativeName("rep1");
        rep1.setAilment("Gynaecology");

        Representative rep2 = new Representative();
        rep2.setRepresentativeName("rep2");
        rep2.setAilment("Gynaecology");

        Representative rep3 = new Representative();
        rep3.setRepresentativeName("rep3");
        rep3.setAilment("General");

        Representative rep4 = new Representative();
        rep4.setRepresentativeName("rep4");
        rep4.setAilment("Orthopaedics");

        Representative rep5 = new Representative();
        rep5.setRepresentativeName("rep5");
        rep5.setAilment("Orthopaedics");

        Representative rep6 = new Representative();
        rep6.setRepresentativeName("rep6");
        rep6.setAilment("General");

        representativeRepository.save(rep1);
        representativeRepository.save(rep2);
        representativeRepository.save(rep3);
        representativeRepository.save(rep4);
        representativeRepository.save(rep5);
        representativeRepository.save(rep6);

        logger.info("[schedule-service] [db-init] representative data initialization complete");
    }

    @PostConstruct
    void init() throws FileNotFoundException {
        logger.info("[schedule-service] [db-init] doctors data initialization start");

        File file = ResourceUtils.getFile("classpath:doctors.csv");
        Scanner sc = new Scanner(file);

        while (sc.hasNext()) {
            String line = sc.next();
            String[] data = line.split(",");

            Doctor doctor = new Doctor();
            doctor.setDoctorId(Integer.parseInt(data[0]));
            doctor.setDoctorName(data[1]);
            doctor.setContactNumber(data[2]);
            doctor.setTreatingAilment(data[3]);
            doctor.setSlotTiming(data[4]);

            doctorsRepository.save(doctor);

        }

        logger.info("[schedule-service] [db-init] doctors data initialization complete");
    }
}
