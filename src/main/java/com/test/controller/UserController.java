package com.test.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.exception.ApiRequestException;
import com.test.model.Users;
import com.test.request.UserRequest;
import com.test.response.UserResponse;
import com.test.service.UserService;
import com.test.util.ServiceResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.AuthorizationScope;

@RestController
public class UserController {
	private static final Logger LOG = Logger.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Create A User", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PostMapping("/v1/users")
	public ResponseEntity registerUser(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request)
			throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<Users> userRegistrationResponse = userService.registerUser(userRequest, request);
		LOG.debug("TOTAL_PPROCESS_TIME=Registerd User : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.CREATED.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Modify Existing User", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@PutMapping("/v1/users/{id}")
	public ResponseEntity modifyUser(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request,
			@PathVariable Long id) throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<String> userRegistrationResponse = userService.updateUser(userRequest, request, id);
		LOG.debug("TOTAL_PPROCESS_TIME=Registerd User : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}

	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Get All User Details Based on Filter Parameters", authorizations = @Authorization(value = "Authorization", scopes = @AuthorizationScope(description = "write", scope = "write")))
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully got the list of Major Achievements for students"),
			@ApiResponse(code = 400, message = "Bad request that might happen because of header mismatch"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There is some error occurred and not handled") })
	@GetMapping("/v1/users")
	public ResponseEntity getUser(HttpServletRequest request,
			@RequestParam(value = "emailId", required = false) String emailId,
			@RequestParam(value = "phoneNumber", required = false) String phoneNumber) throws ApiRequestException {
		long startTime = System.currentTimeMillis();
		ServiceResponse<List<UserResponse>> userRegistrationResponse = userService.filterUser(emailId, phoneNumber,
				request);
		LOG.debug("TOTAL_PPROCESS_TIME=filter User : " + (System.currentTimeMillis() - startTime));
		return ResponseEntity.status(HttpStatus.OK.value()).body(userRegistrationResponse.getPayload());

	}
}
