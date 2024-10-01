package org.boubyan.studentms.services;

import org.boubyan.studentms.model.dtos.ScheduleStudentDto;
import org.boubyan.studentms.model.dtos.request.CancelCourseRegRequestDto;
import org.boubyan.studentms.model.dtos.request.RegisterCourseRequestDto;

public interface ScheduleStudentService {
	ScheduleStudentDto register(RegisterCourseRequestDto registerCourseRequest);

	void cancelRegistration(CancelCourseRegRequestDto cancelCourseRegistrationRequestDto);
}
