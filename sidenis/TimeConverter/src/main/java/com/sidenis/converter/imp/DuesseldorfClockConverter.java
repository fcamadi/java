package com.sidenis.converter.imp;

import com.sidenis.lights.LightsDuesseldorfClock;


public class DuesseldorfClockConverter extends TimeConverterImp {


	public String convertTime(String time) {
		
		String[] aux = time.split(":");
		return	getHours(Integer.parseInt(aux[0])) +
					getMinutes(Integer.parseInt(aux[1])) +	
						getSeconds(Integer.parseInt(aux[2]));	
	}

	
	/**
	 * TBD
	 * @param hours
	 * @return
	 */
	public String getHours(int hours) {
		StringBuffer result = new StringBuffer();
		int activatedUpperRow = hours/10;
		int activatedLowerRow = hours%10;

		//upper row
		for (int i=0; i<activatedUpperRow; i++) {
			result.append(LightsDuesseldorfClock.WHITE.toString());
		}
		for (int i=activatedUpperRow; i<2; i++) {
			result.append(LightsDuesseldorfClock.OFF.toString());
		}
		result.append("\n");
		//lower row
		for (int i=0; i<activatedLowerRow; i++) {
			result.append(LightsDuesseldorfClock.WHITE.toString());
		}
		for (int i=activatedLowerRow; i<9; i++) {
			result.append(LightsDuesseldorfClock.OFF.toString());
		}
		result.append("\n");

		return result.toString();
	}
	
	
	/**
	 * TBD
	 * @param minutes
	 * @return
	 */
	public String getMinutes(int input) {	
		return getSeconds(input)+"\n";
	}

	

	/**
	 * TBD
	 * @param seconds
	 * @return
	 */
	public String getSeconds(int input) {
		StringBuffer result = new StringBuffer();
		int activatedUpperRow = input/10;
		int activatedLowerRow = input%10;

		//upper row
		for (int i=0; i<activatedUpperRow; i++) {
			result.append(LightsDuesseldorfClock.WHITE.toString());
		}
		for (int i=activatedUpperRow; i<5; i++) {
			result.append(LightsDuesseldorfClock.OFF.toString());
		}
		result.append("\n");
		//lower row
		for (int i=0; i<activatedLowerRow; i++) {
			result.append(LightsDuesseldorfClock.WHITE.toString());
		}
		for (int i=activatedLowerRow; i<9; i++) {
			result.append(LightsDuesseldorfClock.OFF.toString());
		}

		return result.toString();

	}
	
}
