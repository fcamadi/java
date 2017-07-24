package com.sidenis.lights;

public enum LightsDuesseldorfClock {
	RED("R"), WHITE("W"), OFF("O");
	
	String symbol;
	LightsDuesseldorfClock(String symbol) {
		this.symbol=symbol;
	}

	public String toString() {
		return symbol;
	}
	
	
	public static String toStringAll() {
		StringBuffer result = new StringBuffer();
		for (LightsDuesseldorfClock l : LightsDuesseldorfClock.values()) {
			result.append(l.toString());
		}
		return result.toString();
	}
}
