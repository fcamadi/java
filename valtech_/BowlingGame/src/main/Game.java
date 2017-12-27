package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game {
	
	
	int[] fst = new int[10];
	int[] snd = new int[10];
	int[] score = new int[10];
	int last; //third throw in last round possible
	
	boolean[] strike = new boolean[10];
	boolean[] spare = new boolean[10];

	int[] total = new int[10];
	
	
	private BufferedReader br;
	
	
	private void initialize() {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		
		for (int i=0; i<10; i++) {
			fst[i] = -1;
			snd[i] = -1;
			score[i] = -1;
			total[i] = -1;
		}
		
	}
	
	private void readPlayerRoundAndRecalculate(int r) {
		

        System.out.print("Enter first throw:");
        
        try {

        	int xi = Integer.parseInt(br.readLine());

        	if (xi >=0 && xi < 10) {
        		fst[r] = xi;
        		strike[r] = false;
        		System.out.print("Enter second throw:");

        		int yi = Integer.parseInt(br.readLine());

        		if (yi >=0 && yi < 10-fst[r]) {
        			snd[r] = yi;
        			spare[r] = false;
        			score[r] = fst[r] + snd[r];     // "normal case" (no strike, no spare)

        			// If no strike/spare this round, we must check previous rounds:
        			// if there were strikes/spares, we can calculate now those scores        			
        			recalculateScores(r);

        			if (r==0) {
        				total[r] = score[r];
        			}
        			else {
        				calculateTotal(r);
        			}

        		}
        		else if ( yi == 10-fst[r]) {
        			
        			spare[r] = true;
        			snd[r] = yi;
        			//3 throws possible in last round
        			if (r==9) {	
        				recalculateScores(r);     
                		lastAdditionalThrow();
            		}
        			
        		}
        		else {
        			System.out.print("Wrong input (Correct values: 0-10)");
        			System.exit(-1);
        		}       	
        	}
        	else if (xi == 10) {
        		
        		strike[r] = true;
        		fst[r] = 10;
        		snd[r] = 0;
        		if (r==9) {//3 throws possible in last round	
        			twoLastAdditionalThrows();
        		}
        		
        	}
        	else {
        		System.out.print("Wrong input (Correct values: 0-10)");
        		System.exit(-1);
        	}

        	

        }catch(NumberFormatException nfe){
            System.err.println("Invalid Format!");
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	private int calculateTotal(int r) {
		int result = 0;
		
		for (int i=0; i<=r; i++) {
			if (total[i]==-1) {  //no need to recalculate what was already calculated
				total[i] = sumatorio(i);
			}
		}
		
		return result;
	}
	
	private int sumatorio(int i) {
		int result = 0;
		
		for (int j=0; j<=i; j++) {
			result += score[j];
		}
		
		return result;
	}
	
	
	
	private void recalculateScores(int r) {
		
		int i=0;
		while (i<r && score[i]!=-1) {  //no need to recalculate what was already calculated
			i++;
		}
					
		while (i<=r) {
			
			if (spare[i]) {
				score[i] = 10 + (i==9? last : fst[i+1]);
			}
			else if (strike[i]) {
				score[i] = 10 + twoNextThrows(i);
			}		
			i++;
		}
		
	}
	
	
	private int twoNextThrows(int r) {
		int result = 0;
		
		//last two rounds 
		if (r==8) {
			result = fst[9] + snd[9];
			return result;
		}
		else if (r==9) {
			result = snd[9] + last;
			return result;
		}
		
		//"normal" rounds
		if (strike[r+1]) {
			result = 10 + (strike[r+2] ? 10 : fst[r+2]);
		}
		else {
			result = fst[r+1] + snd[r+1];
		}	
		return result;
	}
	
	
	private void lastAdditionalThrow() {
		
		System.out.print("Enter third throw:");

		try {
			last = Integer.parseInt(br.readLine());
			
			score[9] += last;          //
			calculateTotal(9);
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	private void twoLastAdditionalThrows() {
		
		System.out.print("Enter second throw:");

		try {
			int second = Integer.parseInt(br.readLine());
			
			snd[9] = second;
			score[9] += second;
			
			recalculateScores(9);           //
			calculateTotal(9);
			
			
			System.out.print("Enter third throw:");
			
			int trd = Integer.parseInt(br.readLine());
			
			score[9] += trd;
			total[9] += trd;
			
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	
	private void printPlayerRound(int r) {
		
		if (strike[r]) {
			System.out.println("Round ["+r+"]  STRIKE!!! total["+r+"]="+total[r] );
		}
		else if (spare[r]) {
			System.out.println("Round ["+r+"]  fst: ("+fst[r]+")  SPARE! total["+r+"]="+total[r]);
		}
		else {
			System.out.println("Round ["+r+"] fst: ("+fst[r]+") snd: ("+snd[r]+") | total["+r+"]="+total[r] );
		}
		
	}
	
	
	
	public static void main(String... args) {
		
		Game game = new Game();
		game.initialize();
		
		for (int round = 0; round < 10; round ++) {
			
			game.readPlayerRoundAndRecalculate(round);
			
			game.printPlayerRound(round);
		}
		
	}
	
}
