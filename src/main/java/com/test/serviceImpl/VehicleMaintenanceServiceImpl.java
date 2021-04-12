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
import com.test.model.VehicleMaintenance;
import com.test.repository.UserRepository;
import com.test.repository.VehicleMaintenanceRepository;
import com.test.repository.VehicleRepository;
import com.test.request.VehicleMaintenanceRequest;
import com.test.service.VehicleMaintenanceService;
import com.test.util.ServiceResponse;

@Service
public class VehicleMaintenanceServiceImpl implements VehicleMaintenanceService {
	private static final Logger LOG = Logger.getLogger(VehicleMaintenanceServiceImpl.class);

	@Autowired
	private VehicleMaintenanceRepository vehicleMaintenanceRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Override
	public ServiceResponse<VehicleMaintenance> createVechileMaintenance(
			VehicleMaintenanceRequest vehicleMaintenanceRequest, HttpServletRequest request)
			throws ApiRequestException {
		LOG.info("Processing create Vehicle Maintenance Request for vehicle id : "
				+ vehicleMaintenanceRequest.getVehicleId() + " and user id : " + vehicleMaintenanceRequest.getUserId());
		ServiceResponse<VehicleMaintenance> serviceResponse = new ServiceResponse<VehicleMaintenance>();
		Vehicle vehicle = validateUserAndVehicle(vehicleMaintenanceRequest);
		VehicleMaintenance maintenaneResponse = vehicleMaintenanceRepository
				.save(new VehicleMaintenance(vehicle, vehicleMaintenanceRequest.getMaintenanceAmount(),
						new Date(System.currentTimeMillis()), vehicleMaintenanceRequest.getNotes(),
						new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
		serviceResponse.setBusinessCode(HttpStatus.CREATED.value());
		serviceResponse.setPayload(maintenaneResponse);
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed create Vehicle Maintenance Request for vehicle id : "
				+ vehicleMaintenanceRequest.getVehicleId() + " and user id : " + vehicleMaintenanceRequest.getUserId());
		return serviceResponse;
	}

	private Vehicle validateUserAndVehicle(VehicleMaintenanceRequest vehicleMaintenanceRequest)
			throws ApiRequestException {
		Optional<Users> optionalUser = userRepository.findByUserId(vehicleMaintenanceRequest.getUserId());
		if (optionalUser.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND,
					"This User is not registered with us : " + vehicleMaintenanceRequest.getUserId());
		}
		Vehicle vehicle = vehicleRepository.findByVechileIdAndUsers(vehicleMaintenanceRequest.getVehicleId(),
				optionalUser.get());
		if (vehicle == null) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND,
					"This Vehicle is not registered with us : " + vehicleMaintenanceRequest.getVehicleId());
		}
		return vehicle;
	}

	@Override
	public ServiceResponse<String> updateVechileMaintenance(VehicleMaintenanceRequest vehicleMaintenanceRequest,
			HttpServletRequest request, Long id) throws ApiRequestException {
		LOG.info("Processing update Vehicle Maintenance Request for vehicle id : "
				+ vehicleMaintenanceRequest.getVehicleId() + " and user id : " + vehicleMaintenanceRequest.getUserId());
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		validateUserAndVehicle(vehicleMaintenanceRequest);
		Optional<VehicleMaintenance> vehicleMaintenanceOptional = vehicleMaintenanceRepository
				.findByVechileMaintenanceId(id);
		if (vehicleMaintenanceOptional.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND, "This Vehicle Maintenance record is Not Available : ");
		}
		VehicleMaintenance vehicleMaintenance = vehicleMaintenanceOptional.get();
		vehicleMaintenance.setMaintenanceAmount(vehicleMaintenanceRequest.getMaintenanceAmount());
		vehicleMaintenance.setMaintenanceNotes(vehicleMaintenanceRequest.getNotes());
		vehicleMaintenance.setModifiedDate(new Date(System.currentTimeMillis()));
		vehicleMaintenanceRepository.save(vehicleMaintenance);
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		serviceResponse.setPayload("{\"Status\": \"success\"}");
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed update Vehicle Maintenance Request for vehicle id : "
				+ vehicleMaintenanceRequest.getVehicleId() + " and user id : " + vehicleMaintenanceRequest.getUserId());
		return serviceResponse;
	}

	@Override
	public ServiceResponse<String> deleteVechileMaintenance(Long id, HttpServletRequest request)
			throws ApiRequestException {
		LOG.info("Processing delete Vehicle Maintenance Request for vehicle  Maintenance id : " + id);
		ServiceResponse<String> serviceResponse = new ServiceResponse<String>();
		Optional<VehicleMaintenance> vehicleMaintenanceOptional = vehicleMaintenanceRepository
				.findByVechileMaintenanceId(id);
		if (vehicleMaintenanceOptional.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND, "This Vehicle Maintenance record is Not Available : ");
		}
		vehicleMaintenanceRepository.delete(vehicleMaintenanceOptional.get());
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		serviceResponse.setPayload("{\"Status\": \"success\"}");
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed delete Vehicle Maintenance Request for vehicle  Maintenance id : " + id);
		return serviceResponse;
	}

	@Override
	public ServiceResponse<List<VehicleMaintenance>> getVechileMaintenance(HttpServletRequest request) {
		LOG.info("Processing get all  Vehicle Maintenance Request  ");
		ServiceResponse<List<VehicleMaintenance>> serviceResponse = new ServiceResponse<List<VehicleMaintenance>>();
		List<VehicleMaintenance> vehicleMaintenanceList = vehicleMaintenanceRepository.findAll();
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		serviceResponse.setPayload(vehicleMaintenanceList);
		serviceResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed get all  Vehicle Maintenance Request  ");
		return serviceResponse;
	}

}
