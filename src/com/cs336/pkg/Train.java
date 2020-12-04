package com.cs336.pkg;

public class Train {
	private int tId;
	private int numSeats;
	int numCars;
	
	public Train(
			int tId,
			int numSeats,
			int numCars) {
		this.tId = tId;
		this.numSeats = numSeats;
		this.numCars = numCars;
	}
	
	public int gettId() {
		return tId;
	}

	public int getNumSeats() {
		return numSeats;
	}

	public int getNumCars() {
		return numCars;
	}
	
	
}
