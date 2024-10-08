package org.boubyan.studentms.repositories;

import java.util.Optional;

import org.boubyan.studentms.model.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
	Optional<CourseEntity> findByCode(String code);
}
