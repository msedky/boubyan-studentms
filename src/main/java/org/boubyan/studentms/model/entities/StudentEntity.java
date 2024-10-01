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
@Table(name = "STUDENTS")
public class StudentEntity implements Serializable {

	private static final long serialVersionUID = -6014242230480832071L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENTS_SEQ")
	@SequenceGenerator(name = "STUDENTS_SEQ", sequenceName = "STUDENTS_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME", nullable = false, length = 50)
	private String firstName;

	@Column(name = "SECOND_NAME", nullable = false, length = 50)
	private String secondName;

	@Column(name = "THIRD_NAME", nullable = false, length = 50)
	private String thirdName;

	@Column(name = "FOURTH_NAME", nullable = false, length = 50)
	private String fourthName;

	@Column(name = "birth_date", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Column(name = "gender", nullable = false)
	private String gender;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

}