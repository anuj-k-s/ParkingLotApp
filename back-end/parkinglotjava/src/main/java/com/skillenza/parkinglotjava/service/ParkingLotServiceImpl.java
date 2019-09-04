package com.skillenza.parkinglotjava.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.skillenza.parkinglotjava.controller.ParkingLotController;
import com.skillenza.parkinglotjava.exception.BuisnessException;
import com.skillenza.parkinglotjava.exception.ResourceNotFoundException;
import com.skillenza.parkinglotjava.model.dto.Constants;
import com.skillenza.parkinglotjava.model.dto.VechileParkingDetails;
import com.skillenza.parkinglotjava.model.entity.ParkingLot;
import com.skillenza.parkinglotjava.repository.ParkingLotRepository;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {
	private static final Logger logger = LoggerFactory.getLogger(ParkingLotServiceImpl.class);
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;

	@Override
	public List<ParkingLot> findAll() {
		logger.info("Entering into findAll at {}",System.currentTimeMillis());
		try {
			List<ParkingLot> list = parkingLotRepository.findAll();
			if (null == list || list.isEmpty()) {
				logger.debug("List is Empty while retriving Vehicles from Lots");
				throw new BuisnessException(new Date(System.currentTimeMillis()), Constants.err1, "GetUri = /api/parkings");
			}
			return list;
		} catch (HibernateException e) {
			logger.error("Hibernate Exception caught in findAll at {}",System.currentTimeMillis());
			throw e;
		}
	}

	@Override
	public ParkingLot findbyVehicleNo(int vehicleNumber) {
		logger.info("Entering into findbyVehicleNo at {}",System.currentTimeMillis());
		return parkingLotRepository.findByVehicleNo(vehicleNumber);
		
	}

	@Override
	public ParkingLot saveVehicleDetails(VechileParkingDetails vechileParkingDetails) throws Exception {
		validateParkingDetailsOfVehicle(vechileParkingDetails);
		ParkingLot vehicleDetails = findbyVehicleNo(vechileParkingDetails.getVehicleNumber());
		ParkingLot entity = null;
		if(null != vehicleDetails) {
			throw new BuisnessException(new Date(System.currentTimeMillis()), Constants.err3 , "PostUri = /api/parkings");
		}else {
			entity = new ParkingLot();
			entity.setLot(vechileParkingDetails.getVehicleLot());
			entity.setParkingAmount(vechileParkingDetails.getVehicleAmount());
			entity.setParkingDuration(vechileParkingDetails.getVehicleDuration());
			entity.setVehicleNumber(vechileParkingDetails.getVehicleNumber());
			entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
			entity.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
			return parkingLotRepository.save(entity);
		}
		
		
	}

	private void validateParkingDetailsOfVehicle(VechileParkingDetails parkingdetails) throws Exception{
		if (null == parkingdetails || parkingdetails.getVehicleAmount()==0 || parkingdetails.getVehicleDuration() ==0 || parkingdetails.getVehicleLot() ==0 || parkingdetails.getVehicleNumber() ==0 ) {
			throw new BuisnessException(new Date(System.currentTimeMillis()), Constants.err2 , "PostUri = /api/parkings");
		}
	}

	
}
