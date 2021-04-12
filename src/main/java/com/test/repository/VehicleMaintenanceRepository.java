package com.test.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.model.VehicleMaintenance;

public interface VehicleMaintenanceRepository extends JpaRepository<VehicleMaintenance, Long> {

	Optional<VehicleMaintenance> findByVechileMaintenanceId(Long vechileMaintenanceId);

}
