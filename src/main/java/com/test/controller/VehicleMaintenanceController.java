package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.exception.ApiRequestException;
import com.test.model.VehicleMaintenance;
import com.test.request.VehicleMaintenanceRequest;
import com.test.service.VehicleMaintenanceService;
import com.test.util.ServiceResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
public class VehicleMaintenanceController {
	private static final Logger LOG = Logger.getLogger(VehicleMaintenanceController.class);

	@Autowired
	private VehicleMaintenanceService vehicleMaintenanceService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Create A Maintenance record for vehicle", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PostMapping("/v1/vehicles/maintenance")
	public ResponseEntity createVehicleMaintenance(
			@Valid @RequestBody VehicleMaintenanceRequest vehicleMaintenanceRequest, HttpServletRequest request)
			throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<VehicleMaintenance> userRegistrationResponse = vehicleMaintenanceService
				.createVechileMaintenance(vehicleMaintenanceRequest, request);
		LOG.debug("TOTAL_PPROCESS_TIME=create vehicle maintenance : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Modify Existing Maintenance record for vehicle", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PutMapping("/v1/vehicles/maintenance/{id}")
	public ResponseEntity updateVehicleMaintenance(
			@Valid @RequestBody VehicleMaintenanceRequest vehicleMaintenanceRequest, HttpServletRequest request,
			@PathVariable Long id) throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<String> userRegistrationResponse = vehicleMaintenanceService
				.updateVechileMaintenance(vehicleMaintenanceRequest, request, id);
		LOG.debug("TOTAL_PPROCESS_TIME=update vehicle maintenance : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Delete Existing Maintenance record for vehicle", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@DeleteMapping("/v1/vehicles/maintenance/{id}")
	public ResponseEntity deleteVechileMaintenance(HttpServletRequest request, @PathVariable Long id)
			throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<String> userRegistrationResponse = vehicleMaintenanceService.deleteVechileMaintenance(id,
				request);
		LOG.debug("TOTAL_PPROCESS_TIME=delete vehicle maintenance : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Get All Maintenance record", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@GetMapping("/v1/vehicles/maintenance")
	public ResponseEntity getVehicleMaintenance(HttpServletRequest request) throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<List<VehicleMaintenance>> userRegistrationResponse = vehicleMaintenanceService
				.getVechileMaintenance(request);
		LOG.debug("TOTAL_PPROCESS_TIME=get all vehicle maintenance : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

}
