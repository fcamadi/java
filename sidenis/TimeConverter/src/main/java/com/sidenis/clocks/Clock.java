package com.sidenis.clocks;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import static com.sidenis.constants.TextConstants.*;

public abstract class Clock {
	
	final static Logger logger = Logger.getLogger(Clock.class);
	
	protected String formatRegexp;	//each subclass must provide it, 
									//because it's different for each kind of clock
	protected String inputTimeRegexp = "[012][01234]:[012345]\\d:[012345]\\d";  
										//common for all kind of clocks 
										// (but each concrete descendant could modify it)
	
	
	protected String time;
	
	
	public String getTimeRegexp() {
		return inputTimeRegexp;
	}
    
	protected void setTimeRegexp(String timeRegexp) {
		this.inputTimeRegexp = timeRegexp;
	}
	
	
	public String toString() {
		return time;
	}

	public void setTime(String input) {
		if (validateTime(input)) {
			time = input;
		}
		else {
			time = TIME_NOT_VALID;
		}
	}
	
	/**
	 * It checks that the input time has the right format
	 * @param input
	 * @return boolean
	 */
	private boolean validateTime(String input) {
		
		try {
			Pattern p = Pattern.compile(formatRegexp);
			Matcher m = p.matcher(input);

			if (m.matches()) {
				return true;
			}
			return false;
		}
		catch (PatternSyntaxException e) {
			logger.error(e.getLocalizedMessage());
			return false;
		}		
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return false;
		}
	}
}
