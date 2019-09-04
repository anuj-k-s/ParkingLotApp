package com.skillenza.parkinglotjava;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.skillenza.parkinglotjava.controller.ParkingLotController;
import com.skillenza.parkinglotjava.model.dto.VechileParkingDetails;
import com.skillenza.parkinglotjava.model.entity.ParkingLot;
import com.skillenza.parkinglotjava.service.ParkingLotService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ParkinglotjavaApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Mock
	private ParkingLotService parkingLotService;
	
	@Mock
	private VechileParkingDetails vehcileParkingDetails;
	
	@InjectMocks
	private ParkingLotController parkingLotController;
	
	List<ParkingLot> parkingList;
	@Before
	public void initialize() {
		ParkingLot pkLot = new ParkingLot();
		pkLot.setId(1);
		pkLot.setParkingAmount(100);
		pkLot.setParkingAmount(102);
		pkLot.setParkingDuration(25);
		pkLot.setVehicleNumber(6717);
		parkingList = new ArrayList<>();
		parkingList.add(pkLot);
	}
	@Test
	public void indexMappingTest() {
		String body = this.restTemplate.getForObject("/", String.class);
		assertThat(body).isEqualTo("App working");
	}
	
	@Test
	public void getAllParkedVehiclesTest() {
		Mockito.when(parkingLotService.findAll()).thenReturn(parkingList);
		ResponseEntity<List<ParkingLot>> parkingLot = parkingLotController.getAllParkedVehicles();
		assertSame(parkingLot.getBody().get(0).getParkingAmount(),102);
	}
	
	@Test
	public void parkVechicleTest() throws Exception {
		Mockito.when(parkingLotService.saveVehicleDetails(vehcileParkingDetails)).thenReturn(parkingList.get(0));
		ResponseEntity<ParkingLot> parkingLot = parkingLotController.parkVechicle(new VechileParkingDetails());
		assertThat(parkingLot.getStatusCodeValue()).isEqualTo(201);
	}
}
