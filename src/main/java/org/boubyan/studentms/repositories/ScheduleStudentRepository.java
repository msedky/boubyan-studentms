package org.boubyan.studentms.repositories;

import java.util.List;
import java.util.Optional;

import org.boubyan.studentms.model.dtos.response.CourseScheduleReportDto;
import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.boubyan.studentms.model.entities.ScheduleStudentEntity;
import org.boubyan.studentms.model.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleStudentRepository extends JpaRepository<ScheduleStudentEntity, Long> {
	Optional<ScheduleStudentEntity> findByScheduleAndStudent(ScheduleEntity schedule, StudentEntity student);

	@Query("SELECT new org.boubyan.studentms.model.dtos.response.CourseScheduleReportDto(c.code, c.name, "
			+ "TO_CHAR(s.startDate, 'YYYY-MM-DD'), TO_CHAR(s.endDate, 'YYYY-MM-DD'), COUNT(ss.student.id)) "
			+ "FROM ScheduleStudentEntity ss JOIN ss.schedule s JOIN s.course c WHERE s.course.id = ?1 "
			+ "GROUP BY c.code, c.name, s.startDate, s.endDate ORDER BY s.startDate DESC")
	List<CourseScheduleReportDto> getCourseScheduleReport(Integer courseId);
}
