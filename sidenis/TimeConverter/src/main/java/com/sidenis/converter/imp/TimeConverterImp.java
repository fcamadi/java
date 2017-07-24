package com.sidenis.converter.imp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import com.sidenis.clocks.BerlinClock;
import com.sidenis.clocks.Clock;
import com.sidenis.clocks.DuesseldorfClock;
import com.sidenis.converter.TimeConverter;

import static com.sidenis.constants.TextConstants.*;

/**
 * @author fran
 */
public class TimeConverterImp implements TimeConverter {

	final static Logger logger = Logger.getLogger(TimeConverterImp.class);
	
	private Clock clock;
	
	/**
	 * private instance with the concrete time converter
	 */
	private TimeConverterImp converter;
	
	
	/**
	 * empty constructor
	 */
	public TimeConverterImp() {	}
	
	
	/**
	 * constructor that creates the private instance with the concrete implementation
	 * @param clock
	 */
	public TimeConverterImp(Clock clock) {
		this.clock = clock;
		
		if (clock instanceof BerlinClock) {
			converter = new BerlinClockConverter();
		}
		else if (clock instanceof DuesseldorfClock) {
			converter = new DuesseldorfClockConverter();
		}
		else {
			logger.error(CLOCK_NOT_VALID);
			throw new IllegalArgumentException(CLOCK_NOT_VALID);
		}
	}
	
	
	/**
	 * 
	 */
	public String convertTime(String time) {
		
		try {
			Pattern p = Pattern.compile(clock.getTimeRegexp());
			Matcher m = p.matcher(time);

			if (m.matches()) {
				String result = converter.convertTime(time);
				logger.info("\n"+result);
				return result;
			}
			else {
				logger.error(FORMAT_KO);
				return FORMAT_KO;
			}
		}
		catch (PatternSyntaxException e) {
			logger.error(e.getLocalizedMessage());
			return REGEXP_KO;
		}		
		catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			return e.getLocalizedMessage();
		}
	}
	


	public static void main(String... args) {

		Clock clock = new BerlinClock();
		TimeConverter tc = new TimeConverterImp(clock);
		//test the format of the input (testing the regExp at the very beginning)
		
		clock.setTime(tc.convertTime("ab:c:ZZ"));
		System.out.println("testabc: \n" + clock.toString());
		
		String t = tc.convertTime("00:00:00");
		clock.setTime(t);
		System.out.println("00:00:00 : \n" + clock.toString());
		
		String t235959 = tc.convertTime("23:59:59");
		clock.setTime(t235959);
		System.out.println("23:59:59 : \n" + clock.toString());
		
			
		/*
		System.out.println("test: \n" + tc.convertTime("qerty:5&$"));
		
		System.out.println("test1: \n" + tc.convertTime("00:00:00"));
		System.out.println("test2: \n" + tc.convertTime("23:59:59"));
		System.out.println("test3: \n" + tc.convertTime("99:99:99"));
		System.out.println("test4: \n" + tc.convertTime("24:00:00"));
		System.out.println("test5: \n" + tc.convertTime("23:64:59"));
		System.out.println("test6: \n" + tc.convertTime("23:59:65"));

		System.out.println("test7: \n" + tc.convertTime("10:31:01"));
		System.out.println("test8: \n" + tc.convertTime("22:59:59"));	
		*/
		
		
		clock = new DuesseldorfClock();
		tc = new TimeConverterImp(clock);
		
		clock.setTime(tc.convertTime("ab:c:ZZ"));
		System.out.println("testabc: \n" + clock.toString());
		
		String t2 = tc.convertTime("00:00:00");
		clock.setTime(t2);
		System.out.println("test 00:00:00 : \n" + clock.toString());
		
	}

}
