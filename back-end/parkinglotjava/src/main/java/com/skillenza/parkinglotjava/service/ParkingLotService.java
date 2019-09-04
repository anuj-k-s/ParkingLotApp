package com.skillenza.parkinglotjava.service;

import java.util.List;

import com.skillenza.parkinglotjava.model.dto.VechileParkingDetails;
import com.skillenza.parkinglotjava.model.entity.ParkingLot;

public interface ParkingLotService {
	
	public List<ParkingLot> findAll();

	public ParkingLot findbyVehicleNo(int vehicleNumber);

	public ParkingLot saveVehicleDetails(VechileParkingDetails vehicleinfo) throws Exception;
}
