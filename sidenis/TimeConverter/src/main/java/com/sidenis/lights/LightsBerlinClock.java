package com.sidenis.lights;

public enum LightsBerlinClock {
	RED("R"), YELLOW("Y"), OFF("O");
	
	String symbol;
	LightsBerlinClock(String symbol) {
		this.symbol=symbol;
	}

	public String toString() {
		return symbol;
	}
	

	public static String toStringAll() {
		StringBuffer result = new StringBuffer();
		for (LightsBerlinClock l : LightsBerlinClock.values()) {
			result.append(l.toString());
		}
		return result.toString();
	}
}
