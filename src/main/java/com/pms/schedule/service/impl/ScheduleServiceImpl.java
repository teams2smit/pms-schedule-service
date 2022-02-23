package com.pms.schedule.service.impl;

import com.pms.schedule.dto.Medicines;
import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.entity.Doctor;
import com.pms.schedule.entity.Representative;
import com.pms.schedule.exception.EmptyDateException;
import com.pms.schedule.exception.RepresentativeNotFoundException;
import com.pms.schedule.exception.UnauthorizedException;
import com.pms.schedule.repository.DoctorsRepository;
import com.pms.schedule.repository.MedicineRepository;
import com.pms.schedule.repository.RepresentativeRepository;
import com.pms.schedule.service.ScheduleService;
import com.pms.schedule.utils.JWTUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private static final long MILLIS_IN_A_DAY = 1000 * 60 * 60 * 24;

    private static final Logger logger = LoggerFactory.getLogger(ScheduleServiceImpl.class);

    @Autowired
    private SimpleDateFormat simpleDateFormat;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private RepresentativeRepository representativeRepository;

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private DoctorsRepository doctorsRepository;

    @Override
    public List<ScheduleResponse> getSchedule(String scheduleStartDate, HttpServletRequest request) throws ParseException, EmptyDateException, RepresentativeNotFoundException, UnauthorizedException {
        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] Schedule start date received");

        if (scheduleStartDate.isEmpty()) {
            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Schedule start date is empty");
            throw new EmptyDateException("Date  must be not empty");
        }

        // Extract Username from request
        String username = getUsernameFromRequest(request);

        Optional<Representative> optional = representativeRepository.findByRepresentativeName(username);
        if (optional.isPresent()) {
            List<ScheduleResponse> scheduleList = new ArrayList<>();

            Date startDate = simpleDateFormat.parse(scheduleStartDate);

            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Representative retrieval operation started");
            List<Representative> representatives = new ArrayList<>();
            representativeRepository.findByAilment(optional.get().getAilment()).forEach(representatives::add);

            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Medicines retrieval operation started");
            List<Medicines> allMedicines = medicineRepository.getAllMedicines();

            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Doctor retrieval operation started");
            List<Doctor> doctorList = new ArrayList<>();
            doctorsRepository.findAllByTreatingAilment(optional.get().getAilment()).forEach(doctorList::add);

            Calendar cal = Calendar.getInstance();
            cal.setTime(startDate);

            int days = 0;

            Date tempDate = startDate;

            /* mapping available representatives for
             * a period of 5 days excluding sunday */
            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Scheduling Representative meeting for 5 days initiated");
            while (days != 5) {
                if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {

                    int repSize = representatives.size();

                    ScheduleResponse scheduleResponse = new ScheduleResponse();

                    scheduleResponse.setDate(simpleDateFormat.format(tempDate));
                    scheduleResponse.setRepresentativeName(representatives.get(days % repSize).getRepresentativeName());
                    scheduleResponse.setContactNumber(doctorList.get(days % doctorList.size()).getContactNumber());
                    scheduleResponse.setDoctorName(doctorList.get(days % doctorList.size()).getDoctorName());

                    // Static Data need to change
                    StringBuilder med = new StringBuilder();
                    for (Medicines medicines : allMedicines) {
                        if (medicines.getTargetAilments().equalsIgnoreCase(doctorList.get(days % doctorList.size()).getTreatingAilment())) {
                            med.append(medicines.getMedicineName());
                            med.append(", ");
                        }
                    }

                    scheduleResponse.setMedicines(med.toString().substring(0, med.toString().length() - 2));

                    scheduleResponse.setSlot(doctorList.get(days % doctorList.size()).getSlotTiming());
                    scheduleResponse.setTreatingAilment(doctorList.get(days % doctorList.size()).getTreatingAilment());

                    scheduleList.add(scheduleResponse);

                    days++;
                }
                tempDate = new Date(tempDate.getTime() + MILLIS_IN_A_DAY);
                cal.setTime(tempDate);
            }
            logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] schedule list :" + scheduleList + " generated.");
            return scheduleList;

        } else {
            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Representative Not Found");
            throw new RepresentativeNotFoundException("Representative Not Found.");
        }
    }

    @Override
    public List<Representative> getRepresentativesByAilment(String repName)
            throws RepresentativeNotFoundException {

        logger.debug("[schedule-service] [/rep/{repName}] Representative Retrieval by Ailment initiated");
        Optional<Representative> optional = representativeRepository.findByRepresentativeName(repName);

        if (optional.isPresent()) {
            logger.debug("[schedule-service] [/rep/{repName}] Representative found");
            return representativeRepository.findByAilment(optional.get().getAilment());
        } else {
            logger.debug("[schedule-service] [/rep/{repName}] No representative is present");
            throw new RepresentativeNotFoundException("Representative Not Found.");
        }
    }

    private String getUsernameFromRequest(HttpServletRequest request)
            throws UnauthorizedException {

        logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Username Retrieval from token initiated");
        try {
            String jwtToken = request.getHeader("Authorization").substring(7);
            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Username : " + jwtUtils.extractUsername(jwtToken) + "found");
            return jwtUtils.extractUsername(jwtToken);

        } catch (Exception e) {
            logger.debug("[schedule-service] [/RepSchedule/{scheduleStartDate}] Unauthorised access found");
            throw new UnauthorizedException("You're not authorized to access this resource.");
        }
    }

}
