package test.ghsatpute.IO.file;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ghsatpute.IO.file.CSV;

import org.junit.Test;

public class testCSV {
	
	@Test
	public void testReadCSVDouble()
	{
		try {
			@SuppressWarnings("unused")
			List<ArrayList<Double>> data = CSV.readCSVDouble("pima-indians-diabetes.data");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
