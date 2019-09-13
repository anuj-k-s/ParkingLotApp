package com.skillenza.parkinglotjava.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skillenza.parkinglotjava.exception.ResourceNotFoundException;
import com.skillenza.parkinglotjava.model.dto.VechileParkingDetails;
import com.skillenza.parkinglotjava.model.entity.ParkingLot;
import com.skillenza.parkinglotjava.service.ParkingLotService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ParkingLotController {
	private static final Logger logger = LoggerFactory.getLogger(ParkingLotController.class);
	@Autowired
	private ParkingLotService parkingLotService;

	@GetMapping("/api/parkings")
	public ResponseEntity<List<ParkingLot>> getAllParkedVehicles() {
		logger.info("Entering into getAllParkedVehicles at {}", System.currentTimeMillis());
		try {
			return new ResponseEntity<List<ParkingLot>>(parkingLotService.findAll(), HttpStatus.OK); 
		} finally {
			logger.info("Exiting from getAllParkedVehicles at {}", System.currentTimeMillis());
		}

	}
	
	@GetMapping("/api/park")
	public ResponseEntity<String> testurl() {
		return new ResponseEntity<String>("OK done", HttpStatus.OK);

	}

	@PostMapping("/api/parkings")
	public ResponseEntity<ParkingLot> parkVechicle(@RequestBody VechileParkingDetails vehicleinfo) throws Exception {
		logger.info("Entering into parkVechicle at {}", System.currentTimeMillis());
		try {
				return new ResponseEntity<ParkingLot>(parkingLotService.saveVehicleDetails(vehicleinfo), HttpStatus.CREATED);
		} finally {
			logger.info("Exiting from parkVechicle at {}", System.currentTimeMillis());
		}

	}

}
