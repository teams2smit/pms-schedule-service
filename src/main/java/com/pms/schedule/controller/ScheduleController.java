package com.pms.schedule.controller;

import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.entity.Representative;
import com.pms.schedule.exception.EmptyDateException;
import com.pms.schedule.exception.RepresentativeNotFoundException;
import com.pms.schedule.exception.UnauthorizedException;
import com.pms.schedule.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

/* Getting Schedule
 *  Mapping done */

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleController.class);

    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/RepSchedule/{scheduleStartDate}")
    public List<ScheduleResponse> getSchedule(HttpServletRequest request, @PathVariable String scheduleStartDate)
            throws ParseException, EmptyDateException, RepresentativeNotFoundException, UnauthorizedException {

        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] initiated");

        List<ScheduleResponse> schedule = scheduleService.getSchedule(scheduleStartDate, request);
        logger.info("[schedule-service] [/RepSchedule/{scheduleStartDate}] process completed");
        return schedule;
    }

    @GetMapping("/rep/{repName}")
    public List<Representative> getRepresentativesByAilment(@PathVariable("repName") String repName)
            throws RepresentativeNotFoundException {

        return scheduleService.getRepresentativesByAilment(repName);
    }
}
