package org.boubyan.studentms.repositories;

import java.util.List;

import org.boubyan.studentms.model.entities.CourseEntity;
import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
	List<ScheduleEntity> findByCourse(CourseEntity course);
}
