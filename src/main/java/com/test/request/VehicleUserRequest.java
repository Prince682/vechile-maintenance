package com.test.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class VehicleUserRequest {

	@NonNull
	private Long vehicleId;

	@NonNull
	private Long userId;

}
