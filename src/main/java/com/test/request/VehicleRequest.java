package com.test.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VehicleRequest {

	private String vehicleRegistrationNumber;

	private String vehicleColor;

	private String vehicleType;

	private String manufactureCompany;

	private String model;

	private Long userId;

}
