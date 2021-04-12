package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.exception.ApiRequestException;
import com.test.model.VehicleMaintenance;
import com.test.request.VehicleMaintenanceRequest;
import com.test.util.ServiceResponse;

public interface VehicleMaintenanceService {

	ServiceResponse<VehicleMaintenance> createVechileMaintenance(VehicleMaintenanceRequest vehicleMaintenanceRequest,
			HttpServletRequest request) throws ApiRequestException;

	ServiceResponse<String> updateVechileMaintenance(VehicleMaintenanceRequest vehicleMaintenanceRequest,
			HttpServletRequest request, Long id) throws ApiRequestException;

	ServiceResponse<String> deleteVechileMaintenance(Long id, HttpServletRequest request) throws ApiRequestException;

	ServiceResponse<List<VehicleMaintenance>> getVechileMaintenance(HttpServletRequest request);

}
