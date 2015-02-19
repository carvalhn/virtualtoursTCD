package com.secondspring;

import java.math.BigDecimal;

public class Location {

	private BigDecimal latitude;
	private BigDecimal longitude;
	private String touristCentreLocationName;
	
	public String getTouristCentreLocationName() {
		return touristCentreLocationName;
	}
	public void setTouristCentreLocationName(String touristCentreLocationName) {
		this.touristCentreLocationName = touristCentreLocationName;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}
	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}
	public BigDecimal getLongitude() {
		return longitude;
	}
	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
}
