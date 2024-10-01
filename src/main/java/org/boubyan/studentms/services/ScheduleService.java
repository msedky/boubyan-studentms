package org.boubyan.studentms.services;

import javax.servlet.http.HttpServletResponse;

public interface ScheduleService {
	void printCourseSchedule(Integer courseId, HttpServletResponse response);
}
