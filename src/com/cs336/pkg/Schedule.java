package com.cs336.pkg;

import java.util.Date;

public class Schedule {
	private int schedID;
	private int destinationID;
	private String lineName;
	private Date startTime;
	
	public Schedule(
			int schedID,
			int originID,
			int destinationID,
			String lineName,
			Date startTime,
			int tID) {
		this.schedID = schedID;
		this.destinationID = destinationID;
		this.lineName = lineName;
		this.startTime = startTime;
	}
	
	public int getSchedID() {
		return schedID;
	}

	public int getDestinationID() {
		return destinationID;
	}

	public String getLineName() {
		return lineName;
	}

	public Date getStartTime() {
		return startTime;
	}
}
