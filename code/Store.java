import java.io.*;
import java.util.Scanner;

public class Store {
		
	public static void store(int address, int data, PrintWriter pw) {
		
	    String index = Integer.toString(address);

        pw.printf("," + index + "," + data + "\n");		
        pw.flush();
							
	}
	
}