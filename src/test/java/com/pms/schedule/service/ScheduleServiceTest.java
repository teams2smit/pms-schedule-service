package com.pms.schedule.service;

import com.pms.schedule.repository.DoctorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ScheduleServiceTest {

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private DoctorsRepository doctorsRepository;

//    @Test
//    void test_getSchedule() {
//
//        Iterable<Doctor> doctors = doctorsRepository.findAll();
//        List<Doctor> list = new ArrayList<>();
//        doctors.forEach(list::add);
//
//        try {
//            List<ScheduleResponse> schedule = scheduleService.getSchedule("29-JAN-2022");
//            assertEquals(5, schedule.size());
//
//            for (int i = 0; i < 5; i++) {
//                assertEquals(schedule.get(i).getDoctorName(), list.get(i).getDoctorName());
//                assertEquals(schedule.get(i).getContactNumber(), list.get(i).getContactNumber());
//                assertEquals(schedule.get(i).getSlot(), list.get(i).getSlotTiming());
//                assertEquals(schedule.get(i).getTreatingAilment(), list.get(i).getTreatingAilment());
//            }
//
//        } catch (ParseException | EmptyDateException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    void test_getSchedule_null_string_input() {
//        assertThrows(EmptyDateException.class, () -> {
//            scheduleService.getSchedule("");
//        });
//    }
//
//    @Test
//    void test_getSchedule_malformed_string_input() {
//        assertThrows(ParseException.class, () -> {
//            scheduleService.getSchedule("24/12/2022");
//        });
//    }


}