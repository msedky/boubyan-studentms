package org.boubyan.studentms.repositories;

import java.util.Optional;

import org.boubyan.studentms.model.entities.ScheduleEntity;
import org.boubyan.studentms.model.entities.ScheduleStudentEntity;
import org.boubyan.studentms.model.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleStudentRepository extends JpaRepository<ScheduleStudentEntity, Long> {
	Optional<ScheduleStudentEntity> findByScheduleAndStudent(ScheduleEntity schedule, StudentEntity student);
}
