import java.io.*;
import java.util.Scanner;

public class Fetch {
	
    public static int fetch(int address, PrintWriter pw, InputStream is, boolean modeFlag) {

        int data = 0;
		if(modeFlag == false && address > 999) {
				
			System.out.println("Error: try to load system message.");
			System.exit(0);
				
		} else {
				
            String index = Integer.toString(address);
	
            pw.printf(index + "\n");
            pw.flush();
				
            Scanner sc = new Scanner(is);

            if (sc.hasNext()) {
					
                String line = sc.nextLine();
	            data = Integer.parseInt(line.trim());

            }

        }			
			
        return data;
				
    }
}
