package com.sidenis.clocks;


import com.sidenis.lights.LightsBerlinClock;

/**
 * 
 * https://en.wikipedia.org/wiki/Mengenlehreuhr
 * 
 * @author fran
 *
 */
public class BerlinClock extends Clock {

	
	public BerlinClock() {
		formatRegexp = "["+LightsBerlinClock.toStringAll()+"]\\n["+
						LightsBerlinClock.toStringAll()+"]{4}\\n["+LightsBerlinClock.toStringAll()+"]{4}\\n["+
						LightsBerlinClock.toStringAll()+"]{11}\\n["+LightsBerlinClock.toStringAll()+"]{4}";
	}
	
	
	/*  Code refactored, when the new "DuesseldorfClock" was added,
	    the common code was moved to the "common" place in the parent class Clock
	
	public void setTime(String input) {
		if (validateTime(input)) {
			time = input;
		}
		else {
			time = TIME_NOT_VALID;
		}
	}
	
	public String toString() {
		return time;
	}

	
	@Override
	public boolean validateTime(String input) {
		try {
			Pattern p = Pattern.compile(formatRegexp);
			Matcher m = p.matcher(input);

			if (m.matches()) {
				return true;
			}
			return false;
		}
		catch (Exception e) {
			System.out.println("validateTime: "+e.getLocalizedMessage());
		}
		return false;
	}
	*/
}
