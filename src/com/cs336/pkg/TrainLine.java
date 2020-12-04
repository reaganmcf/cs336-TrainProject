package com.cs336.pkg;

public class TrainLine {
	private String lineName;
	private int travelTimeBetweenStop;
	private int totalTime;
	private String listStops;
	private float farePerStop;
	private int originID;
	private int destinationID;
	
	public TrainLine(
			String lineName,
			int travelTimeBetweenStop,
			int totalTime,
			String listStops,
			float farePerStop,
			int originID,
			int destinationID) {
		this.lineName = lineName;
		this.travelTimeBetweenStop = travelTimeBetweenStop;
		this.totalTime = totalTime;
		this.listStops = listStops;
		this.farePerStop = farePerStop;
		this.originID = originID;
		this.destinationID = destinationID;
	}
	
	public String getLineName() {
		return lineName;
	}

	public int getTravelTimeBetweenStop() {
		return travelTimeBetweenStop;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public String getListStops() {
		return listStops;
	}

	public float getFarePerStop() {
		return farePerStop;
	}
	
	public int getOriginID() {
		return this.originID;
	}
	
	public int getDestinationID() {
		return this.destinationID;
	}
	
	
}
