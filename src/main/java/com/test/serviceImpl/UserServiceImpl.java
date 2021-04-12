package com.test.serviceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.test.exception.ApiRequestException;
import com.test.model.Users;
import com.test.model.VehicleMaintenance;
import com.test.repository.UserRepository;
import com.test.repository.VehicleMaintenanceRepository;
import com.test.request.UserRequest;
import com.test.response.UserResponse;
import com.test.service.UserService;
import com.test.util.ServiceResponse;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger LOG = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private VehicleMaintenanceRepository vehicleMaintenanceRepository;

	@Override
	public ServiceResponse<Users> registerUser(UserRequest userRequest, HttpServletRequest request)
			throws ApiRequestException {
		LOG.info("Processing User Registration Request for : " + userRequest.getEmailId());
		ServiceResponse<Users> registrationResponse = new ServiceResponse<Users>();
		Users existingUser = userRepository.findByEmailId(userRequest.getEmailId());
		if (existingUser != null) {
			throw new ApiRequestException(HttpStatus.CONFLICT,
					"This email id is already registered with us. Please try to login or try registring with different email"

			);
		}
		Users users = userRepository.save(new Users(userRequest.getFirstName(), userRequest.getMiddleName(),
				userRequest.getLastName(), userRequest.getMobileNumber(), userRequest.getEmailId(),
				userRequest.getDlNumber(), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
		registrationResponse.setBusinessCode(HttpStatus.CREATED.value());
		registrationResponse.setPayload(users);
		registrationResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed User Registration Request for : " + userRequest.getEmailId());
		return registrationResponse;
	}

	@Override
	public ServiceResponse<String> updateUser(UserRequest userRequest, HttpServletRequest request, Long id)
			throws ApiRequestException {
		LOG.info("Processing Update User Request for : " + userRequest.getEmailId());
		ServiceResponse<String> registrationResponse = new ServiceResponse<String>();
		Optional<Users> optionalUser = userRepository.findByUserId(id);
		if (optionalUser.isEmpty()) {
			throw new ApiRequestException(HttpStatus.NOT_FOUND, "This id is not Available. Please check : " + id);
		}
		Users users = optionalUser.get();
		users.setEmailId(userRequest.getEmailId());
		users.setDlNumber(userRequest.getDlNumber());
		users.setFirstName(userRequest.getFirstName());
		users.setLastName(userRequest.getLastName());
		users.setMiddleName(userRequest.getMiddleName());
		users.setMobileNumber(userRequest.getMobileNumber());
		users.setModifiedDate(new Date(System.currentTimeMillis()));
		userRepository.save(users);
		registrationResponse.setBusinessCode(HttpStatus.OK.value());
		registrationResponse.setPayload("{\"Status\": \"success\"}");
		registrationResponse.setExtraDetails("{\"Status\": \"success\"}");
		LOG.info("Processed  Update User Request for : " + userRequest.getEmailId());
		return registrationResponse;
	}

	@Override
	public ServiceResponse<List<UserResponse>> filterUser(String emailId, String phoneNumber,
			HttpServletRequest request) {
		ServiceResponse<List<UserResponse>> serviceResponse = new ServiceResponse<List<UserResponse>>();
		List<UserResponse> userRespnoseList = new ArrayList<UserResponse>();
		List<Users> userList = userRepository.findAll();
		List<VehicleMaintenance> vehicleMaintenanceList = vehicleMaintenanceRepository.findAll();
		if (!StringUtils.isBlank(emailId)) {
			vehicleMaintenanceList = vehicleMaintenanceList.stream()
					.filter(data -> emailId.equalsIgnoreCase(data.getVehicle().getUsers().getEmailId()))
					.collect(Collectors.toList());
			userList = userList.stream().filter(data -> emailId.equalsIgnoreCase(data.getEmailId()))
					.collect(Collectors.toList());
		}
		if (!StringUtils.isBlank(phoneNumber)) {
			vehicleMaintenanceList = vehicleMaintenanceList.stream()
					.filter(data -> phoneNumber.equalsIgnoreCase(data.getVehicle().getUsers().getMobileNumber()))
					.collect(Collectors.toList());

			userList = userList.stream().filter(data -> phoneNumber.equalsIgnoreCase(data.getMobileNumber()))
					.collect(Collectors.toList());
		}
		System.out.println("userList size :" + userList.size());
		for (Users users : userList) {
			System.out.println("vehicleMaintenanceList size :" + vehicleMaintenanceList.size());
			vehicleMaintenanceList = vehicleMaintenanceList.stream()
					.filter(data -> users.getUserId().equals(data.getVehicle().getUsers().getUserId()))
					.collect(Collectors.toList());
			userRespnoseList.add(new UserResponse(users.getUserId(), users.getFirstName(), users.getMiddleName(),
					users.getLastName(), users.getMobileNumber(), users.getEmailId(), users.getDlNumber(),
					vehicleMaintenanceList));
		}
		serviceResponse.setPayload(userRespnoseList);
		serviceResponse.setBusinessCode(HttpStatus.OK.value());
		return serviceResponse;
	}
}
