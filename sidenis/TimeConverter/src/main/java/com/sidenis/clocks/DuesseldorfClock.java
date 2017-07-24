package com.sidenis.clocks;


import com.sidenis.lights.LightsDuesseldorfClock;

/**
 * 
 * In German, sorry!
 * 
 * https://de.wikipedia.org/wiki/Lichtzeitpegel    
 * http://www.duesseldorf.de/thema/sights/rheinturm/uhr.shtml
 * 
 * @author fran
 *
 */
public class DuesseldorfClock extends Clock {

	public DuesseldorfClock() {
		formatRegexp = "["+LightsDuesseldorfClock.toStringAll()+"]{2}\\n["+LightsDuesseldorfClock.toStringAll()+"]{9}\\n["+
						LightsDuesseldorfClock.toStringAll()+"]{5}\\n["+LightsDuesseldorfClock.toStringAll()+"]{9}\\n["+
						LightsDuesseldorfClock.toStringAll()+"]{5}\\n["+LightsDuesseldorfClock.toStringAll()+"]{9}";
	}
	

}
