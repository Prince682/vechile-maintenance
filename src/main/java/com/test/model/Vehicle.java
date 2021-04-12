package com.test.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vehicle_id", unique = true, nullable = false)
	private Long vechileId;

	@Column(name = "registration_number", unique = true, nullable = false)
	private String registrationNumber;

	@Column(name = "color", nullable = false)
	private String color;

	@Column(name = "model", nullable = false)
	private String model;

	@Column(name = "type", nullable = false)
	private String type;

	@Column(name = "manufacture_company", nullable = false)
	private String manufactureCompany;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	private Date modifiedDate;

	@OneToOne
	@JoinColumn(name = "user_id")
	private Users users;

	public Vehicle(String registrationNumber, String color, String model, String type, String manufactureCompany,
			Date createdDate, Date modifiedDate, Users users) {
		super();
		this.registrationNumber = registrationNumber;
		this.color = color;
		this.model = model;
		this.type = type;
		this.manufactureCompany = manufactureCompany;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.users = users;
	}

}
