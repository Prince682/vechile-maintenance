package com.test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleMaintenanceRequest {

	@NonNull
	private Double maintenanceAmount;

	private String notes;

	@NonNull
	private Long vehicleId;

	@NonNull
	private Long userId;

}
