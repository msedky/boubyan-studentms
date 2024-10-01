package org.boubyan.studentms.model.entities;

import java.io.Serializable;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USERS")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = -2438494791571298939L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USERNAME", unique = true, nullable = false, length = 50)
	private String username;

	@Column(name = "PASSWORD", nullable = false, length = 512)
	private String password;

}
