package com.skillenza.parkinglotjava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.skillenza.parkinglotjava.model.entity.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{

	
	@Query("SELECT t FROM ParkingLot t WHERE t.vehicleNumber = ?1")
	public ParkingLot findByVehicleNo(int vehiclNo);
}
