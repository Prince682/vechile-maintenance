package com.test.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehicle_maintenance")
public class VehicleMaintenance implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long vechileMaintenanceId;

	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;

	@Column(name = "maintenance_amount")
	private Double maintenanceAmount;

	@Column(name = "maintenance_date")
	private Date maintenanceDate;

	@Column(name = "maintenance_notes")
	private String maintenanceNotes;

	@Column(name = "created_date", nullable = false)
	private Date createdDate;

	@Column(name = "updated_date", nullable = false)
	private Date modifiedDate;

	public VehicleMaintenance(Vehicle vehicle, Double maintenanceAmount, Date maintenanceDate, String maintenanceNotes,
			Date createdDate, Date modifiedDate) {
		super();
		this.vehicle = vehicle;
		this.maintenanceAmount = maintenanceAmount;
		this.maintenanceDate = maintenanceDate;
		this.maintenanceNotes = maintenanceNotes;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}

}
