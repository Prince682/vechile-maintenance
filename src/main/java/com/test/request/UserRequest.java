package com.test.request;

import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

	@NotBlank(message = "first name can not be null or empty or blank")
	private String firstName;

	private String middleName;

	@NotBlank(message = "last name can not be null or empty or blank")
	private String lastName;

	@NotBlank(message = "mobile number can not be null or empty or blank")
	private String mobileNumber;

	@NotBlank(message = "email id can not be null or empty or blank")
	private String emailId;

	@NotBlank(message = "dl number can not be null or empty or blank")
	private String dlNumber;

}
