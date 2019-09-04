package com.skillenza.parkinglotjava.model.dto;

public class VechileParkingDetails {
	
	
	private int vehicleLot;
	private int vehicleAmount;
	private int vehicleDuration;
	private int vehicleNumber;
	public int getVehicleLot() {
		return vehicleLot;
	}
	public void setVehicleLot(int vehicleLot) {
		this.vehicleLot = vehicleLot;
	}
	public int getVehicleAmount() {
		return vehicleAmount;
	}
	public void setVehicleAmount(int vehicleAmount) {
		this.vehicleAmount = vehicleAmount;
	}
	public int getVehicleDuration() {
		return vehicleDuration;
	}
	public void setVehicleDuration(int vehicleDuration) {
		this.vehicleDuration = vehicleDuration;
	}
	public int getVehicleNumber() {
		return vehicleNumber;
	}
	public void setVehicleNumber(int vehicleNumber) {
		this.vehicleNumber = vehicleNumber;
	}
	
	
	@Override
	public String toString() {
		return "VechileParkingDetails [vehicleLot=" + vehicleLot + ", vehicleAmount=" + vehicleAmount
				+ ", vehicleDuration=" + vehicleDuration + ", vehicleNumber=" + vehicleNumber + "]";
	}
	
	
	
	
	
	
}
