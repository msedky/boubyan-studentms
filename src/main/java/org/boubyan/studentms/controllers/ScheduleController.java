package org.boubyan.studentms.controllers;

import javax.servlet.http.HttpServletResponse;

import org.boubyan.studentms.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;

	@SuppressWarnings("rawtypes")
	@GetMapping("/printScheduleCourse")
	public ResponseEntity printCourseSchedule(@PathVariable Integer courseId, HttpServletResponse response) {
		scheduleService.printCourseSchedule(courseId, response);
		return ResponseEntity.ok().build();
	}

}
