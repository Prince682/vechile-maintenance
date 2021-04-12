package com.test.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.test.model.Users;
import com.test.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

	Vehicle findByRegistrationNumber(String vehicleRegistrationNumber);

	Optional<Vehicle> findByVechileId(Long vechileId);

	Vehicle findByVechileIdAndUsers(Long vechileId, Users users);

	@Query("select v from Vehicle v where (:registrationNumber is null or v.registrationNumber = :registrationNumber) "
			+ "and (:model is null or v.model = :model) and (:mobileNumber is null or v.users.mobileNumber = :mobileNumber)"
			+ "and (:emailId is null or v.users.emailId = :emailId) and" + "(:color is null or v.color = :color) and "
			+ "(:type is null or v.type = :type) and "
			+ "(:manufactureCompany is null or v.manufactureCompany = :manufactureCompany)")
	List<Vehicle> findByRegistrationNumberAndModel(@Param("registrationNumber") String registrationNumber,
			@Param("model") String model, @Param("color") String color, @Param("type") String type,
			@Param("manufactureCompany") String manufactureCompany, @Param("mobileNumber") String mobileNumber,
			@Param("emailId") String emailId);

}
