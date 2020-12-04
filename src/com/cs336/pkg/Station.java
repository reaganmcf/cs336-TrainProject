package com.cs336.pkg;

public class Station {
	private int stationID;
	private String name;
	private String city;
	private String state;
	
	public Station(
			int stationID,
			String name,
			String city,
			String state) {
		this.stationID = stationID;
		this.name = name;
		this.city = city;
		this.state = state;
	}
	
	public int getStationID() {
		return this.stationID;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public String getState() {
		return this.state;
	}
}
