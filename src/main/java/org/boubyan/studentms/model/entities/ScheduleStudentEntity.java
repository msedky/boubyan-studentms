package org.boubyan.studentms.model.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@Entity
@Table(name = "SCHEDULE_STUDENTS")
public class ScheduleStudentEntity implements Serializable {

	private static final long serialVersionUID = -119157991802398236L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHEDULE_STUDENTS_SEQ")
	@SequenceGenerator(name = "SCHEDULE_STUDENTS_SEQ", sequenceName = "SCHEDULE_STUDENTS_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SCHEDULE_ID", nullable = false)
	private ScheduleEntity schedule;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "STUDENT_ID", nullable = false)
	private StudentEntity student;

	@Column(name = "REGISTRATION_DATE")
	private Date registrationDateTime;
}
