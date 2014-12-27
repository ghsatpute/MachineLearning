/*
 * @Author Ganesh Satpute
 * @Date  Friday 27-Dec-2014 12:24PM
 */


package ghsatpute.IO.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CSV {

	/**
	 * This method reads Double data from file
	 * 
	 * @param inputFileName : File name which is to be read
	 * @return List of list of Doubles read from file
	 * @throws IOException 
	 */
	public static List<ArrayList<Double>> readCSVDouble(String inputFileName) 
			throws IOException {
		File inputFile = new File(inputFileName);
		// If file does not exist throw exception
		if(!inputFile.exists())
			throw new FileNotFoundException("File " + inputFileName + " does not exists");
		
		// If cannot read file
		if(!inputFile.canRead())
			throw new IOException("Cannot read file : " + inputFileName);
		
		BufferedReader br = new BufferedReader(new FileReader(inputFileName));
		List<ArrayList<Double>> data = new LinkedList<ArrayList<Double>>();
		String line = null;
		while((line = br.readLine()) != null)
		{	
			// split the value by comma
			String[] values = line.split(",");
			ArrayList<Double> intValues = new ArrayList<Double>();
			
			// Foreach value in the CSV parse it to Double
			for(String value : values) 
			{
				intValues.add(Double.parseDouble(value));
			}
			
			data.add(intValues);
			
		}
		
		return data;
	}
}
