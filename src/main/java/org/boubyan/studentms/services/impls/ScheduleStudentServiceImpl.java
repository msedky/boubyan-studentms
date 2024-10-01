package org.boubyan.studentms.services.impls;

import java.util.Date;

import org.boubyan.studentms.mapper.ScheduleMapper;
import org.boubyan.studentms.mapper.ScheduleStudentMapper;
import org.boubyan.studentms.mapper.StudentMapper;
import org.boubyan.studentms.model.dtos.ScheduleStudentDto;
import org.boubyan.studentms.model.dtos.request.CancelCourseRegRequestDto;
import org.boubyan.studentms.model.dtos.request.RegisterCourseRequestDto;
import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.boubyan.studentms.model.entities.ScheduleStudentEntity;
import org.boubyan.studentms.model.entities.StudentEntity;
import org.boubyan.studentms.repositories.ScheduleStudentRepository;
import org.boubyan.studentms.services.ScheduleStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ScheduleStudentServiceImpl implements ScheduleStudentService {

	@Autowired
	private ScheduleStudentRepository scheduleStudentRepository;

	@Autowired
	private ScheduleMapper scheduleMapper;

	@Autowired
	private StudentMapper studentMapper;

	@Autowired
	private ScheduleStudentMapper scheduleStudentMapper;

	@Override
	public ScheduleStudentDto register(RegisterCourseRequestDto registerCourseRequest) {
		ScheduleEntity scheduleEntity = scheduleMapper.fromDtoToEntity(registerCourseRequest.getSchedule());
		StudentEntity studentEntity = studentMapper.fromDtoToEntity(registerCourseRequest.getStudent());
		ScheduleStudentEntity scheduleStudentEntity = ScheduleStudentEntity.builder().schedule(scheduleEntity)
				.student(studentEntity).registrationDateTime(new Date()).build();
		scheduleStudentEntity = scheduleStudentRepository.save(scheduleStudentEntity);
		ScheduleStudentDto scheduleStudentDto = scheduleStudentMapper.fromEntityToDto(scheduleStudentEntity);
		return scheduleStudentDto;
	}

	@Override
	public void cancelRegistration(CancelCourseRegRequestDto cancelCourseRegistrationRequestDto) {
		ScheduleEntity scheduleEntity = scheduleMapper
				.fromDtoToEntity(cancelCourseRegistrationRequestDto.getSchedule());
		StudentEntity studentEntity = studentMapper.fromDtoToEntity(cancelCourseRegistrationRequestDto.getStudent());
		ScheduleStudentEntity scheduleStudentEntity = scheduleStudentRepository
				.findByScheduleAndStudent(scheduleEntity, studentEntity).orElseThrow(
						() -> new RuntimeException("Student [" + cancelCourseRegistrationRequestDto.getStudent().getId()
								+ "] is not already registered in Schedule ["
								+ cancelCourseRegistrationRequestDto.getSchedule().getId() + "]"));
		scheduleStudentRepository.delete(scheduleStudentEntity);
	}

}
