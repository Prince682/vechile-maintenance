package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.exception.ApiRequestException;
import com.test.model.Vehicle;
import com.test.request.VehicleRequest;
import com.test.request.VehicleUserRequest;
import com.test.util.ServiceResponse;

public interface VehicleService {

	ServiceResponse<Vehicle> createVechile(VehicleRequest vehicleRequest, HttpServletRequest request)
			throws ApiRequestException;

	ServiceResponse<String> updateVechileUser(VehicleUserRequest vehicleUserRequest, HttpServletRequest request,
			Long id) throws ApiRequestException;

	ServiceResponse<List<Vehicle>> filterVehicle(String model, String registrationNumber, HttpServletRequest request,
			String color, String type, String manufactureCompany, String mobileNumber, String emailId);

}
