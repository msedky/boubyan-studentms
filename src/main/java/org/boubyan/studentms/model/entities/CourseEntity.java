package org.boubyan.studentms.model.entities;

import java.io.Serializable;

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
@Table(name = "COURSES")
public class CourseEntity implements Serializable {

	private static final long serialVersionUID = -7668069426666137481L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSES_SEQ")
	@SequenceGenerator(name = "COURSES_SEQ", sequenceName = "COURSES_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "CODE", unique = true, nullable = false, length = 10)
	private String code;

	@Column(name = "NAME", nullable = false, length = 25)
	private String name;
}