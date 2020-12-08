package com.cs336.pkg;

import java.util.Date;

public class SpecialSchedule {
	
	private int schedID;
	private int originID;
	private int destinationID;
	private int tID;
	private String start;
	private String end;
	private String lineName;
	private float fare;
	
	public SpecialSchedule(
			int schedID,
			int originID,
			int destinationID,
			int tID,
			String start,
			String end,
			String lineName,
			float fare) {
		this.schedID = schedID;
		this.originID = originID;
		this.destinationID = destinationID;
		this.tID = tID;
		this.start = start.substring(0, start.length() - 2);
		this.end = end.substring(0, end.length() - 2);
		this.lineName = lineName;
		this.fare = fare;
	}

	public int getSchedID() {
		return schedID;
	}
	
	public int getOriginID() {
		return originID;
	}
	
	public int getDestinationID() {
		return destinationID;
	}

	public int gettID() {
		return tID;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}
	
	public String getLineName() {
		return this.lineName;
	}

	public float getFare() {
		return fare;
	}
	
	
	public String toTableString() {
		return "<tr>"+
				"<td>"+this.getSchedID()+"</td>"+
				"<td>"+this.gettID()+"</td>"+
				"<td>"+this.getStart()+"</td>"+
				"<td>"+this.getEnd()+"</td>"+
				"<td>"+this.getFare()+"</td>"+
				"</tr>";
	}
	
	public String toTableStringRaw() {
		return "<td>"+this.getSchedID()+"</td>"+
				"<td>"+this.gettID()+"</td>"+
				"<td>"+this.getStart()+"</td>"+
				"<td>"+this.getEnd()+"</td>"+
				"<td>"+this.getFare()+"</td>";
	}

}
