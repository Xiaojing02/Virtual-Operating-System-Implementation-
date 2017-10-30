import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Read in a program file and return the file to memory.
 */
public class ReadAFile {

    public String[] readFile(String filename) {

//        Default value is null
        String[] address = new String[2000];

        try {

            File myFile = new File(filename);
            FileReader fileReader = new FileReader(myFile);

            BufferedReader reader = new BufferedReader(fileReader);

            String line = null;

            int i = 0;

            while ((line = reader.readLine()) != null) {

                String[] result;

//                If the line is blank, skip it.
                if(line.isEmpty() || line.charAt(0) == ' ') {

                    continue;

                } else if(line.charAt(0) == '.') {
					
					int index = Integer.parseInt(line.substring(1));
                    i = index - 1;					
					
				} else if(line.contains("//")) {

                    result = line.split("//");
                    address[i] = result[0];					

                } else {

                    result = line.split(" ");
                    address[i] = result[0];	
					
                }

                i++;

            }

            reader.close();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        return address;

    }

}
