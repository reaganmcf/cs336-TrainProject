package com.cs336.pkg;

import java.util.Date;

public class Reservation {

	private int resNum;
	private float totalFare;
	private String passenger;
	private Date date;
	private int originID;
	private int destinationID;
	private String lineName;
	private String username;
	private boolean isChild;
	private boolean isSenior;
	private boolean isDisabled;
	private int schedID;
	
	public Reservation(
			int resNum,
			float totalFare,
			String passenger,
			Date date,
			int originID,
			int destinationID,
			String lineName,
			String username, // references Customer
			boolean isChild,
			boolean isSenior,
			boolean isDisabled,
			int schedID) {
		this.resNum = resNum;
		this.totalFare = totalFare;
		this.passenger = passenger;
		this.date = date;
		this.originID = originID;
		this.destinationID = destinationID;
		this.lineName = lineName;
		this.username = username;
		this.isChild = isChild;
		this.isSenior = isSenior;
		this.isDisabled = isDisabled;
		this.schedID = schedID;
	}
	
	public int getResNum() {
		return resNum;
	}

	public float getTotalFare() {
		return totalFare;
	}

	public String getPassenger() {
		return passenger;
	}

	public Date getDate() {
		return date;
	}

	public int getOriginID() {
		return originID;
	}

	public int getDestinationID() {
		return destinationID;
	}

	public String getLineName() {
		return lineName;
	}

	public String getUsername() {
		return username;
	}

	public boolean isChild() {
		return isChild;
	}

	public boolean isSenior() {
		return isSenior;
	}

	public boolean isDisabled() {
		return isDisabled;
	}
	
	public int getSchedID() {
		return this.schedID;
	}
	
	public String toTableString() {
		return "<tr>"+
				"<td>"+this.getResNum()+"</td>"+
				"<td>"+this.getTotalFare()+"</td>"+
				"<td>"+this.getPassenger()+"</td>"+
				"<td>"+this.getDate().toGMTString()+"</td>"+
				"<td>"+this.getOriginID()+"</td>"+
				"<td>"+this.getDestinationID()+"</td>"+
				"<td>"+this.getLineName()+"</td>"+
				"<td>"+this.getUsername()+"</td>"+
				"<td>"+(this.isChild() ? "YES" : "NO")+"</td>"+
				"<td>"+(this.isSenior() ? "YES" : "NO")+"</td>"+
				"<td>"+(this.isDisabled() ? "YES" : "NO")+"</td>"+
				"<td>"+this.getSchedID()+"</td>"+
				"</tr>";
	}
	
}
