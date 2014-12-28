package test.ghsatpute.ML.supervised;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ghsatpute.IO.file.CSV;
import ghsatpute.ML.supervised.PerceptronLearningAlgo;

public class TestPerceptronLearningAlgo {
	
	@Test
	public  void testPerceptronLearningAlgo()
	{
		PerceptronLearningAlgo PLA = new PerceptronLearningAlgo("pima-indians-diabetes.data", 1);
		
		/*
		for(Double weight : PLA.getWeight())
		{
			System.out.println(weight +  "\t");
		}*/
		List<Double> list = new ArrayList<Double>();
		
		// Read the data, run the getOutcome on same data and analyze correctness of algorithm
		try {
			List<ArrayList<Double>> data = CSV.readCSVDouble("pima-indians-diabetes.data");
			int correct = 0;
			for(ArrayList<Double> row : data)
			{
				
				int actualOutput = (int)(double)row.remove(row.size() - 1);
				int algoOutput = (int)PLA.getOutcome(row);
				
				// If output from algorithm matches the actual output then incremnt the correct
				if(actualOutput == algoOutput)
					correct++;
				
			}
			System.out.println("Correctness of algorithm : " + correct + " / " + data.size());
			System.out.println("In percentage : " + ((double)correct/data.size() * 100) );
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
