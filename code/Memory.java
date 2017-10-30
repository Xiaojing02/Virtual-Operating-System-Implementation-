import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Memory {
	
	private static String[] address = new String[2000];

    public static void main(String[] args) {

        String filename = args[0];

        // initialize by reading a program file
        ReadAFile file = new ReadAFile();
        address = file.readFile(filename);

        Scanner sc = new Scanner(System.in);
        int index = 0;
        while (sc.hasNext()){
			
			String line = sc.nextLine();
			
			if(line.charAt(0) == ',') {
				
				write(line);
				
			} else if (line.equals("exit")){
				
//				int exitVal = exitValue(50);
//				System.out.println("the exitVal is " + exitVal);
				
			} else {
				
				index = Integer.parseInt(line);
                System.out.println(address[index]);
				
			}
            

        }

    }
	
	public static void write(String data) {
		
		String[] result = data.split(",");
		int index = Integer.parseInt(result[1].trim());
		address[index] = result[2];
//		System.out.println(index);		
//		System.out.println(result[2]);
//		System.out.println(address[index]);
		
	}

}
