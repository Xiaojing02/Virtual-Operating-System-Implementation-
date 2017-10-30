import java.io.*;
import java.util.Scanner;

public class Execute {
	
	private static int AC = 0;
	private static int X = 0;
	private static int Y = 0;
	private static int SP = 1000;
//	flag = false represents user mode. The default mode is user mode.
	private static boolean modeFlag = false;  
	
		
	public static int execute(int PC, int IR, PrintWriter pw, InputStream is) {
		
		if(IR == 1) {
					
			PC++;
			int t;
				
			t = Fetch.fetch(PC, pw, is, modeFlag);
			AC = t;
			
		} else if(IR == 2) {

            PC++;
            int t1;
            int t2;
            t1 = Fetch.fetch(PC, pw, is, modeFlag);	
            t2 = Fetch.fetch(t1, pw, is, modeFlag);
            AC = t2;		

        } else if(IR == 3) {

            PC++;
            int t1;
            int t2;				
            t1 = Fetch.fetch(PC, pw, is, modeFlag);
            t2 = Fetch.fetch(t1, pw, is, modeFlag);
            AC = Fetch.fetch(t2, pw, is, modeFlag);
			
        } else if(IR == 4) {
					
			PC++;
		    int t1;				
		    t1 = Fetch.fetch(PC, pw, is, modeFlag);
			int t2;
			t2 = t1 + X;
			AC = Fetch.fetch(t2, pw, is, modeFlag);
					
		} else if(IR == 5) {
				
            PC++;
            int t1;
            t1 = Fetch.fetch(PC, pw, is, modeFlag);
            int t2 = t1 + Y;
            AC = Fetch.fetch(t2, pw, is, modeFlag);

        } else if(IR == 6) {
			
            int t1 = SP + X;
            AC = Fetch.fetch(t1, pw, is, modeFlag);	
			
        } else if(IR == 7) {
			
		    PC++;
			int t;
			t = Fetch.fetch(PC, pw, is, modeFlag);
			Store.store(t, AC, pw);

        } else if(IR == 8) {
					
            int c;
            c = (int) (Math.random() * 100);
            AC = c;

        } else if(IR == 9) {
					
            PC++;
            int port = Fetch.fetch(PC, pw, is, modeFlag);

            if(port == 1) {

                System.out.print(AC);

            } else if(port == 2) {

			    char result = (char) AC;
                System.out.print(result);
						
            }
					
        } else if(IR == 10) {

            AC = AC + X;

        } else if(IR == 11) {

            AC = AC + Y;

        } else if(IR == 12) {

            AC = AC - X;

        } else if(IR == 13) {

            AC = AC - Y;

        } else if(IR == 14) {

            X = AC;

        } else if(IR == 15) {

            AC = X;

        } else if(IR == 16) {

            Y = AC;

        } else if(IR == 17) {

            AC = Y;

        } else if(IR == 18) {
			
			SP = AC;

        } else if(IR == 19) {

            AC = SP;

        } else if(IR == 20) {
//            jump to the address
            PC++;				
            int t1 = Fetch.fetch(PC, pw, is, modeFlag);
            PC = t1 -1;

        } else if(IR == 21) {

            PC++;
			int t1;
            t1 = Fetch.fetch(PC, pw, is, modeFlag);
            if(AC == 0) {
//                jump to the address
//                There's PC++ after this line of code
                PC = t1 -1;

            }			

        } else if(IR == 22) {

            PC++;
			int t1;				
            t1 = Fetch.fetch(PC, pw, is, modeFlag);
            if(AC != 0) {
//                jump to the address
                PC = t1 -1;

            }				
			
        } else if(IR == 23) {
				
            PC++;
            int index =  Fetch.fetch(PC, pw, is, modeFlag);
//			    push onto stack
            PC++;
            SP--;
            Store.store(SP, PC, pw);
//              fetch and jump to address			
			PC = index -1;
			
        } else if(IR == 24) {	
			
//              Pop return address from the stack, jump to the address
            int index =  Fetch.fetch(SP, pw, is, modeFlag);
			SP++;			
//			    bring back PC = Fetch.fetch(SP, pw, is);
			PC = index -1;

        } else if(IR == 25) {

            X++;

        } else if(IR == 26) {

            X--;

        } else if(IR == 27) {
				
//             push AC on the stack
            SP--;
			Store.store(SP, AC, pw);

        } else if(IR == 28) {
			
//              pop from the stack into AC
            AC = Fetch.fetch(SP, pw, is, modeFlag);
			SP++;
			
        } else if(IR == 29) {
//            perform system call, switch to kernel mode.
//          check whether it's a nested interrupt.

            if(modeFlag == true) {
//           how two handle second interrupt while in an interrupt.	
	
		    } else {

                modeFlag = true;
			    int t1 = SP;
			    SP = 1999;
//				First, store current SP to system stack
			    Store.store(SP, t1, pw);
			    SP--;
//				Second, store current PC to system stack
			    Store.store(SP, PC, pw);
			    PC = 1500 - 1;
			
		    }

			
        } else if(IR == 30) {

//			First, restore PC from system stack.
            PC = Fetch.fetch(SP, pw, is, modeFlag);
		    SP++;
//          Second, restore SP from system stack.
            int t = Fetch.fetch(SP, pw, is, modeFlag);
		    SP = t;
//            return from system call, switch back to user mode.
            modeFlag = false;
			
        } 
				
		return PC;
	}
	
	public static boolean returnFlag() {
		
		return modeFlag;
		
	}
	
	public static int interruptCheck(int PC, int counter, int timer, PrintWriter pw) {
		
	    if(counter % timer == 0) {
//			interrupt
            if (modeFlag == true) {
				
			} else {
				
			    modeFlag = true;
			    int t1 = SP;
			    SP = 1999;
//				First, store current SP to system stack
			    Store.store(SP, t1, pw);
			    SP--;
//				Second, store current PC to system stack
			    Store.store(SP, PC, pw);
			    PC = 1000 - 1;
			    
			}
                    
		}
         
        return PC;		 
		
	}

}
