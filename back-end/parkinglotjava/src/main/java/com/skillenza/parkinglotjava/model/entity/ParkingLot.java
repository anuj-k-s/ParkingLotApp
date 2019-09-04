package com.skillenza.parkinglotjava.model.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "parkinglots")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},
        allowGetters = true)
public class ParkingLot {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "created_at")
	private Date createdAt;
	
	@Column(unique = true)
	private int lot;
	
	@Column(name = "parking_amount")
	private int parkingAmount;
	
	@Column(name = "parking_duration")
	private int parkingDuration;
	
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "vehicle_number",unique = true)
	private int vehicleNumber;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getLot() {
		return lot;
	}

	public void setLot(int lot) {
		this.lot = lot;
	}

	public int getParkingAmount() {
		return parkingAmount;
	}

	public void setParkingAmount(int parkingAmount) {
		this.parkingAmount = parkingAmount;
	}

	public int getParkingDuration() {
		return parkingDuration;
	}

	public void setParkingDuration(int parkingDuration) {
		this.parkingDuration = parkingDuration;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public int getVehicleNumber() {
		return vehicleNumber;
	}

	public void setVehicleNumber(int vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}

	@Override
	public String toString() {
		return "ParkingLot [id=" + id + ", createdAt=" + createdAt + ", lot=" + lot + ", parkingAmount=" + parkingAmount
				+ ", parkingDuration=" + parkingDuration + ", updatedAt=" + updatedAt + ", vehicleNumber="
				+ vehicleNumber + "]";
	}
	
	
	
    
}
