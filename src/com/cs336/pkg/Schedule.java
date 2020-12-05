package com.cs336.pkg;

import java.util.Date;

public class Schedule {
	private int schedID;
	private int originID;
	private int destinationID;
	private String lineName;
	private String startTime;
	private int tID;
	
	public Schedule(
			int schedID,
			int originID,
			int destinationID,
			String lineName,
			String startTime,
			int tID) {
		this.schedID = schedID;
		this.originID = originID;
		this.destinationID = destinationID;
		this.lineName = lineName;
		this.startTime = startTime.substring(0, startTime.length() - 2);
		this.tID = tID;
	}
	
	public int getSchedID() {
		return schedID;
	}
	
	public int getOriginID() {
		return this.originID;
	}

	public int getDestinationID() {
		return destinationID;
	}

	public String getLineName() {
		return lineName;
	}

	public String getStartTime() {
		return startTime;
	}
	
	public int getTID() {
		return this.tID;
	}
	
	public String toTableString() {
		return "<tr>"+
			"<td>"+this.getSchedID()+"</td>"+
			"<td>"+this.getOriginID()+"</td>"+
			"<td>"+this.getDestinationID()+"</td>"+
			"<td>"+this.getLineName()+"</td>"+
			"<td>"+this.getStartTime()+"</td>"+
			"<td>"+this.getTID()+"</td>"+
			"</tr>";
	}
}
