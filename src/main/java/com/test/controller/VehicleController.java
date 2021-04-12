package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.exception.ApiRequestException;
import com.test.model.Vehicle;
import com.test.request.VehicleRequest;
import com.test.request.VehicleUserRequest;
import com.test.service.VehicleService;
import com.test.util.ServiceResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
public class VehicleController {

	@Autowired
	private VehicleService vehicleService;

	private static final Logger LOG = Logger.getLogger(VehicleController.class);

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Create A Vehicle", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PostMapping("/v1/vehicles")
	public ResponseEntity createVehicle(@Valid @RequestBody VehicleRequest vehicleRequest, HttpServletRequest request)
			throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<Vehicle> userRegistrationResponse = vehicleService.createVechile(vehicleRequest, request);
		LOG.debug("TOTAL_PPROCESS_TIME=create vehicle : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Modify A Vehicle User", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PatchMapping("/v1/vehicles/users/vehicle_users/{id}")
	public ResponseEntity updateVehicleUser(@Valid @RequestBody VehicleUserRequest vehicleUserRequest,
			HttpServletRequest request, @PathVariable Long id) throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<String> userRegistrationResponse = vehicleService.updateVechileUser(vehicleUserRequest, request,
				id);
		LOG.debug("TOTAL_PPROCESS_TIME=create vehicle user : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Get All Vehicle Based on Filter", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@GetMapping("/v1/vehicles")
	public ResponseEntity getVehicle(HttpServletRequest request,
			@RequestParam(value = "model", required = false) String model,
			@RequestParam(value = "registrationNumber", required = false) String registrationNumber,
			@RequestParam(value = "color", required = false) String color,
			@RequestParam(value = "type", required = false) String type,
			@RequestParam(value = "manufactureCompany", required = false) String manufactureCompany,
			@RequestParam(value = "mobileNumber", required = false) String mobileNumber,
			@RequestParam(value = "emailId", required = false) String emailId)
			throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<List<Vehicle>> userRegistrationResponse = vehicleService.filterVehicle(model,
				registrationNumber, request,color,type,manufactureCompany,mobileNumber,emailId);
		LOG.debug("TOTAL_PPROCESS_TIME=filter vehicle : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

}
