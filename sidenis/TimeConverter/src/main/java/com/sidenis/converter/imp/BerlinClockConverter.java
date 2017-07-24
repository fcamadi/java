package com.sidenis.converter.imp;

import com.sidenis.lights.LightsBerlinClock;


public class BerlinClockConverter extends TimeConverterImp {


	
	public String convertTime(String time) {
		
		String[] aux = time.split(":");
		return	getSeconds(Integer.parseInt(aux[2]))+
					getHours(Integer.parseInt(aux[0]))+
						getMinutes(Integer.parseInt(aux[1]));		
	}
	
	
	/**
	 * @param seconds
	 * @return OFF for even seconds (0,2,4 ...), and "YELLOW_LIGHT" for odd seconds (1,3,5 ...)
	 * (it is not specified this way or the contrary, only 
	 *  "The top lamp is a pump which is blinking on/off every two seconds.")
	 */
	private String getSeconds(int seconds) {
		if((seconds%2)==0){
			return LightsBerlinClock.OFF.toString()+"\n";
		}
		else{
			return LightsBerlinClock.YELLOW.toString()+"\n";
		}
	}

	/**
	 * 
	 * @param hours
	 * @return two lines representing the two first lines of the Berlin Clock
	 *         
	 */
	private String getHours(int hours) {

		StringBuffer result = new StringBuffer();
		int activatedUpperRow = hours/5;
		int activatedLowerRow = hours%5;

		//upper row
		for (int i=0; i<activatedUpperRow; i++) {
			result.append(LightsBerlinClock.RED.toString());
		}
		for (int i=activatedUpperRow; i<4; i++) {
			result.append(LightsBerlinClock.OFF.toString());
		}
		result.append("\n");
		//lower row
		for (int i=0; i<activatedLowerRow; i++) {
			result.append(LightsBerlinClock.RED.toString());
		}
		for (int i=activatedLowerRow; i<4; i++) {
			result.append(LightsBerlinClock.OFF.toString());
		}
		result.append("\n");

		return result.toString();
	}


	/**
	 * 
	 * @param minutes
	 * @return the last two lines representing the minutes in the Berlin Clock
	 */
	private String getMinutes(int minutes) {

		StringBuffer result = new StringBuffer();
		int activatedUpperRow = minutes/5;
		int activatedLowerRow = minutes%5;

		//upper row
		for (int i=0; i<activatedUpperRow; i++) {
			if (i==2 || i==5 || i==8) {
				result.append(LightsBerlinClock.RED.toString());
			}
			else {
				result.append(LightsBerlinClock.YELLOW.toString());
			}
		}
		for (int i=activatedUpperRow; i<11; i++) {
			result.append(LightsBerlinClock.OFF.toString());
		}
		result.append("\n");
		//lower row
		for (int i=0; i<activatedLowerRow; i++) {
			result.append(LightsBerlinClock.YELLOW.toString());
		}
		for (int i=activatedLowerRow; i<4; i++) {
			result.append(LightsBerlinClock.OFF.toString());
		}
		//result.append("\n");

		return result.toString();
	}
	
}
