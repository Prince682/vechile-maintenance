package com.test.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.test.exception.ApiRequestException;
import com.test.model.Users;
import com.test.request.UserRequest;
import com.test.response.UserResponse;
import com.test.util.ServiceResponse;

public interface UserService {

	ServiceResponse<Users> registerUser(UserRequest userRequest, HttpServletRequest request) throws ApiRequestException;

	ServiceResponse<String> updateUser(UserRequest userRequest, HttpServletRequest request, Long id)
			throws ApiRequestException;

	ServiceResponse<List<UserResponse>> filterUser(String emailId, String phoneNumber, HttpServletRequest request);

}
