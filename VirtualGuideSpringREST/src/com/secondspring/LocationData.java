package com.secondspring;

import java.math.BigDecimal;

public class LocationData
{
	String locationName;
	BigDecimal latitude;
	BigDecimal longitude;
	String touristBuildingName;
	String text;
	String audioLinks[];
	String imageLinks[];
	public String getLocationName() 
	{
		return locationName;
	}
	public void setLocationName(String locationName) 
	{
		this.locationName = locationName;
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
	public String getTouristBuildingName() {
		return touristBuildingName;
	}
	public void setTouristBuildingName(String touristBuildingName) {
		this.touristBuildingName = touristBuildingName;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String[] getAudioLinks() {
		return audioLinks;
	}
	public void setAudioLinks(String[] audioLinks) {
		this.audioLinks = audioLinks;
	}
	public String[] getImageLinks() {
		return imageLinks;
	}
	public void setImageLinks(String[] imageLinks) {
		this.imageLinks = imageLinks;
	}
	
}
