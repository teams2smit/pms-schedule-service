package com.pms.schedule.service;

import com.pms.schedule.dto.ScheduleResponse;
import com.pms.schedule.entity.Representative;
import com.pms.schedule.exception.EmptyDateException;
import com.pms.schedule.exception.RepresentativeNotFoundException;
import com.pms.schedule.exception.UnauthorizedException;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public interface ScheduleService {

    List<ScheduleResponse> getSchedule(String scheduleStartDate, HttpServletRequest request) throws ParseException, EmptyDateException, RepresentativeNotFoundException, UnauthorizedException;

    List<Representative> getRepresentativesByAilment(String repName) throws RepresentativeNotFoundException;
}
