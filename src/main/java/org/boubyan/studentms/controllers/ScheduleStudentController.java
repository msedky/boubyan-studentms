package org.boubyan.studentms.controllers;

import org.boubyan.studentms.model.dtos.ScheduleStudentDto;
import org.boubyan.studentms.model.dtos.request.CancelCourseRegRequestDto;
import org.boubyan.studentms.model.dtos.request.RegisterCourseRequestDto;
import org.boubyan.studentms.services.ScheduleStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scheduleStudent")
public class ScheduleStudentController {

	@Autowired
	private ScheduleStudentService scheduleStudentService;

	@PostMapping("/registerToCourse")
	public ScheduleStudentDto register(@RequestBody RegisterCourseRequestDto registerCourseRequest) {
		return scheduleStudentService.register(registerCourseRequest);
	}

	@PostMapping("/cancelRegistration")
	public void cancelRegistration(@RequestBody CancelCourseRegRequestDto cancelCourseRegistrationRequestDto) {
		scheduleStudentService.cancelRegistration(cancelCourseRegistrationRequestDto);
	}
}
