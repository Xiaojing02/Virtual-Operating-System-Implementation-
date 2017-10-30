import java.io.*;
import java.util.Scanner;


public class cpu {

    public static void main(String[] args) {
		
		int IR;
        int PC = 0;
		boolean flag = true;
		String path = args[0];
		String filename = args[1];
		String timer = args[2];
		int t = Integer.parseInt(timer);
		int counter = 0;
		boolean modeFlag;
		

        try {

            Runtime rt = Runtime.getRuntime();
            String current = new java.io.File( "." ).getCanonicalPath();
            System.out.println("Current dir:"+current);
            Process proc = rt.exec("java -cp " + path + " Memory " + filename);


            InputStream is = proc.getInputStream();
            OutputStream os = proc.getOutputStream();
			
            PrintWriter pw = new PrintWriter(os);


            while(flag == true) {
				
			    modeFlag = Execute.returnFlag();			
		        IR = Fetch.fetch(PC, pw, is, modeFlag);
				
		        if(IR == 0) {
					
			        break;
					
		        } else if (IR == 50){
					
			       flag = false;				
			       System.exit(0);	
				   
		        } else {
					
			       PC = Execute.execute(PC, IR, pw, is);
					
		        }						

				if(modeFlag == true) {
					
					// do nothing
					
				} else {
					
					counter++;
		            PC = Execute.interruptCheck(PC, counter, t, pw);	
					
				}
		        

		        PC++;
			
            }

            proc.waitFor();
			
			
        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
