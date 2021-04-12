package com.test.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", unique = true, nullable = false)
	private Long userId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "mobile_number", unique = true, nullable = false)
	private String mobileNumber;

	@Column(name = "email_id", unique = true, nullable = false)
	private String emailId;

	@Column(name = "dl_number", unique = true, nullable = false)
	private String dlNumber;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	private Date modifiedDate;

	public Users(String firstName, String middleName, String lastName, String mobileNumber, String emailId,
			String dlNumber, Date createdDate, Date modifiedDate) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.dlNumber = dlNumber;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

}
