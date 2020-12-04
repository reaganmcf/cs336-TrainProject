package com.cs336.pkg;

import java.util.Date;

public class SpecialSchedule {
	
	private int schedID;
	private int tID;
	private String start;
	private String end;
	private float fare;
	
	public SpecialSchedule(
			int schedID,
			int tID,
			String start,
			String end,
			float fare) {
		this.schedID = schedID;
		this.tID = tID;
		this.start = start;
		this.end = end;
		this.fare = fare;
	}

	public int getSchedID() {
		return schedID;
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
