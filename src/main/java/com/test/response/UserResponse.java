package com.test.response;

import java.util.List;

import com.test.model.VehicleMaintenance;

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
public class UserResponse {

	private Long userId;

	private String firstName;

	private String middleName;

	private String lastName;

	private String mobileNumber;

	private String emailId;

	private String dlNumber;

	private List<VehicleMaintenance> vehicleMaintenanceList;

}
