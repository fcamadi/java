package com.sidenis.converter;


import com.sidenis.clocks.Clock;
import com.sidenis.clocks.DuesseldorfClock;
import com.sidenis.converter.TimeConverter;
import com.sidenis.converter.imp.TimeConverterImp;

import static org.junit.Assert.assertEquals;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import static com.sidenis.constants.TextConstants.*;

/**
 * @author fran
 */
public class UsualTimeToStandardDuesseldorfClockTest {

	private Clock clock = new DuesseldorfClock();
    private TimeConverter timeConverter = new TimeConverterImp(clock);

    @Rule public TestName testName = new TestName();
    
    
    /**
     *    negative tests (input with wrong formats, "impossible" times ...)
     */
    
    @Test
    public void testError_01() {
        String actual = timeConverter.convertTime("ab:c:ZZ");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_02() {
        String actual = timeConverter.convertTime("qerty:5&$");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_03() {
        String actual = timeConverter.convertTime("99:99:99");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_04() {
        String actual = timeConverter.convertTime("22:22:22:22");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_05() {
        String actual = timeConverter.convertTime("25:00:00");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_06() {
        String actual = timeConverter.convertTime("18:99:00");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void testError_07() {
        String actual = timeConverter.convertTime("18:00:99");
        String expected = FORMAT_KO;
       
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    
    /**
     *   positive tests
     */
    
    
    @Test
    public void test_0h() {
        
        String time =
                "OO\n" +
                "OOOOOOOOO\n" +
                "OOOOO\n" +
                "OOOOOOOOO\n" +
                "OOOOO\n" +
                "OOOOOOOOO";
        clock.setTime(time);
        String actual = timeConverter.convertTime("00:00:00");
        String expected = clock.toString();
        
        assertEquals(testName.getMethodName(),expected, actual);
    }

    @Test
    public void test_24h() {
    	String time =
    			"WW\n" +
    	        "WWWWOOOOO\n" +
    	        "OOOOO\n" +
    	        "OOOOOOOOO\n" +
    	        "OOOOO\n" +
    	        "OOOOOOOOO";
    	clock.setTime(time);
        String actual = timeConverter.convertTime("24:00:00");
        String expected = clock.toString();

        assertEquals(testName.getMethodName(),expected, actual);
    }
 
    @Test
    public void test_23_59_59() {
    	String time =
    			"WW\n" +
    	        "WWWOOOOOO\n" +
    	        "WWWWW\n" +
    	        "WWWWWWWWW\n" +
    	        "WWWWW\n" +
    	        "WWWWWWWWW";
    	clock.setTime(time);
        String actual = timeConverter.convertTime("23:59:59");
        String expected = clock.toString();

        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void test_01_01_01() {
    	String time =
    			"OO\n" +
    	        "WOOOOOOOO\n" +
    	        "OOOOO\n" +
    	        "WOOOOOOOO\n" +
    	        "OOOOO\n" +
    	        "WOOOOOOOO";
    	clock.setTime(time);
        String actual = timeConverter.convertTime("01:01:01");
        String expected = clock.toString();

        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    @Test
    public void test_10_31_01() {    
    	String time =
    			"WO\n" +
    	        "OOOOOOOOO\n" +
    	        "WWWOO\n" +
    	        "WOOOOOOOO\n" +
    	        "OOOOO\n" +
    	        "WOOOOOOOO";
        clock.setTime(time);
        String actual = timeConverter.convertTime("10:31:01");
        String expected = clock.toString();
        
        assertEquals(testName.getMethodName(),expected, actual);
    }

    @Test
    public void test_22_59_12() { 
    	String time =
    			"WW\n" +
    	        "WWOOOOOOO\n" +
    	        "WWWWW\n" +
    	        "WWWWWWWWW\n" +
    	        "WOOOO\n" +
    	        "WWOOOOOOO";
        clock.setTime(time);
        clock.setTime(time);
        String actual = timeConverter.convertTime("22:59:12");
        String expected = clock.toString();
        
        assertEquals(testName.getMethodName(),expected, actual);
    }
    
    
}
