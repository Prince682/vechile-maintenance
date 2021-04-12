package com.test.serviceImpl;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.exception.ApiRequestException;
import com.test.model.Users;
import com.test.model.Vehicle;
import com.test.repository.UserRepository;
import com.test.repository.VehicleRepository;
import com.test.request.VehicleRequest;
import com.test.request.VehicleUserRequest;
import com.test.service.VehicleService;
import com.test.util.ServiceResponse;

@Service
public class VehcileServiceImpl implements VehicleService {
	private static final Logger LOG = Logger.getLogger(VehcileServiceImpl.class);

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ServiceResponse<Vehicle> createVechile(VehicleRequest vehicleRequest, HttpServletRequest request)
			throws ApiRequestException {
		LOG.info("Processing create vehicle Request for : " + vehicleRequest.getVehicleRegistrationNumber());
		ServiceResponse<Vehicle> serviceResponse = new ServiceResponse<Vehicle>();
		Vehicle existingVechile = vehicleRepository
				.findByRegistrationNumber(vehicleRequest.getVehicleRegistrationNumber());
		if (existingVechile != null) {
			throw new ApiRequestException(HttpStatus.CONFLICT, "This vechile already created."

			);
		}
		Vehicle vechile = null;
		if (vehicleRequest.getUserId() != null && vehicleRequest.getUserId() != 0) {
			Optional<Users> optionalUser = validateUser(vehicleRequest.getUserId());
			vechile = vehicleRepository.save(new Vehicle(vehicleRequest.getVehicleRegistrationNumber(),
					vehicleRequest.getVehicleColor(), vehicleRequest.getModel(), vehicleRequest.getVehicleType(),
					vehicleRequest.getManufactureCompany(), new Date(System.currentTimeMillis()),
					new Date(System.currentTimeMillis()), optionalUser.get()));
		} else {
			vechile = vehicleRepository.save(new Vehicle(vehicleRequest.getVehicleRegistrationNumber(),
					vehicleRequest.getVehicleColor(), vehicleRequest.getVehicleType(),
					vehicleRequest.getManufactureCompany(), vehicleRequest.getModel(),
					new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), null));
		}
		serviceResponse.setBusinessCode(HttpStatus.CREATED.value());
		serviceResponse.setPayload(vechile);
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed create vehicle Request for : " + vehicleRequest.getVehicleRegistrationNumber());
		return serviceResponse;
	}

	private Optional<Users> validateUser(Long userId) throws ApiRequestException {
		Optional<Users> optionalUser = userRepository.findByUserId(userId);
		if (optionalUser.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND, "This User is not registered with us : " + userId);
		}
		return optionalUser;
	}

	@Override
	public ServiceResponse<String> updateVechileUser(VehicleUserRequest vehicleUserRequest, HttpServletRequest request,
			Long id) throws ApiRequestException {
		LOG.info("Processing update Vehicle user Request for vehicle id : " + vehicleUserRequest.getVehicleId()
				+ " and user id : " + vehicleUserRequest.getUserId());
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		Optional<Users> optionalUser = validateUser(vehicleUserRequest.getUserId());
		Vehicle existingvehicle = vehicleRepository.findByVechileIdAndUsers(id, optionalUser.get());
		if (existingvehicle != null) {
			throw new ApiRequestException(HttpStatus.CONFLICT,
					"This Vehicle is already assigned to user : " + existingvehicle.getVechileId());
		}
		Optional<Vehicle> vehicleOptional = vehicleRepository.findByVechileId(id);
		if (vehicleOptional.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND,
					"This Vehicle is not registered with us : " + vehicleUserRequest.getVehicleId());
		}
		Vehicle vechile = vehicleOptional.get();
		vechile.setUsers(optionalUser.get());
		vechile.setModifiedDate(new Date(System.currentTimeMillis()));
		vehicleRepository.save(vechile);
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		serviceResponse.setPayload("{\"Status\": \"success\"}");
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed update Vehicle user Request for vehicle id : " + vehicleUserRequest.getVehicleId()
				+ " and user id : " + vehicleUserRequest.getUserId());
		return serviceResponse;
	}

	@Override
	public ServiceResponse<List<Vehicle>> filterVehicle(String model, String registrationNumber,
			HttpServletRequest request, String color, String type, String manufactureCompany, String mobileNumber,
			String emailId) {
		ServiceResponse<List<Vehicle>> serviceResponse = new ServiceResponse<List<Vehicle>>();
		List<Vehicle> vehicleList = vehicleRepository.findByRegistrationNumberAndModel(registrationNumber, model, color,
				type, manufactureCompany, mobileNumber, emailId);
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		serviceResponse.setPayload(vehicleList);
		return serviceResponse;
	}

}