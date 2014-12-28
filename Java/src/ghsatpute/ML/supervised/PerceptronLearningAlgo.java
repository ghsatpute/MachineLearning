/**
 * 
 */
package ghsatpute.ML.supervised;

import ghsatpute.IO.file.CSV;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Ganesh Satpute
 * @date 27-Dec-2014 12:05PM
 */

/**
 * This class implements perceptron learning algorithm
 * This is simplest implementation of this algorithm and we assume some things here
 * 1. Data is *Linearly separable*
 * 2. Data is in form floating points or Doubles
 * 3. Data has equal attributes 
 * 4. Last value in data row is outcome, for now we assume that it is 0 or 1
 * 
 * Trying to use dataset from here http://archive.ics.uci.edu/ml/datasets/Pima+Indians+Diabetes
 * For this database after learning the algorithm when run on the same dataset to verify the output
 * We got 61% accuracy for 1 iteration. Result does not improve with number of iterations.
 * Maybe this can happen because dataset is linearly separable. 
 */

// TODO: Need to make this singleton
public class PerceptronLearningAlgo {
	
	List<ArrayList<Double>> data = new LinkedList<ArrayList<Double>>();
	List<Double> weight = new ArrayList<Double>();
	private int numberOfColumns = 0;
	
	
	public List<Double> getWeight() { return weight;} 
	
	/**
	 * 
	 * @param args : name of data file
	 */
	public static void Main(String[] args)
	{
		if(args.length != 1)
		{
			System.out.println("Invalid number of argumets");
		}
	}
	
	public PerceptronLearningAlgo(String fileName) 
	{
		this(fileName, 1);
	}
	
	public PerceptronLearningAlgo(String fileName, int iterations) 
	{	
				
				if(fileName == null)
					throw new IllegalArgumentException("File name cannot be null");
				
				if(iterations < 0)
					throw new IllegalArgumentException("iterations cannot be -ve " + iterations);
				
				// read the data from file
				try 
				{
					 data = CSV.readCSVDouble(fileName);
					 if(data == null) 
					 {
						 System.out.println("data is empty");
					 }
				}
				catch(IOException ex)
				{
					System.out.println("Exception occurred " +  ex.getMessage());
				}
				
				// Read the first array list from the list and set the number of columns
				// Hence forth, we assume that all other rows will be consistent with first
				numberOfColumns = data.get(0).size() - 1; // Last value is supposed to be outcome
				
				// Innitialize the weight to random numbers
				// TODO: Can we do better?
				for(int i = 0 ; i < numberOfColumns; i++) 
				{
					weight.add(Math.random());
				}
				
				for(int i = 0 ; i < iterations; i++)
				{
					System.out.println("Iterations : " + i);
					learn();
				}
	}
	
	private void learn() 
	{
		if(data == null)
			return;
		
		// For each observation
		for(ArrayList<Double> row : data)
		{
			if(row.size() -1 != numberOfColumns)
			{
				System.out.println("Inconsistent data. Returning " + row.toString() + " rowsize : " 
							+ row.size() + " number columns " + numberOfColumns);
				return;
			}
			
			//System.out.println(row.toString());
			// Calculate sum of w(i) * x(i)
			double sum = 0.0;
			for(int i = 0 ; i < numberOfColumns; i++) 
			{
				sum += weight.get(i) * row.get(i);
			}
			
			// If sum is positive means output should be 1 otherwise 0
			int output = 0;
			if(sum >= 0)
				output  = 1;
			
			
			// Get actual outcome for current row
			// Need to cast from Double to double and then to int
			int actualOutput = (int) ((double)row.get(row.size() - 1));
			//System.out.println("Actual Output : " + actualOutput + " Program's output " + output);
			if(actualOutput != 0 && actualOutput != 1)
			{
				System.out.println("Invalid output " + actualOutput +" \n Output ideally should be 1 or 0" );
				return;
			}
			
			// If our output matches
			if(output == actualOutput)
			{
				// TODO: Check do we need to do anything here?
				continue;
			}
			else if(output < actualOutput)
				// output is 0 and should be 1
				// Hence we add the weight
			{
				for(int i = 0 ; i < weight.size(); i++)
				{
					weight.set(i,weight.get(i) +  row.get(i));
				}
			}
			else if(output > actualOutput)
				// output is 1 and should be 0
				// Hence we substract the weight
			{
				for(int i = 0 ; i < weight.size(); i++)
				{
					weight.set(i,weight.get(i) -  row.get(i));
				}
			}
		}
	}

	public int getOutcome(List<Double> input)  
	{
		if(input == null)
			return -1;
		if(input.size() != numberOfColumns)
			throw new IllegalArgumentException("Lenght of input should be "+numberOfColumns);
		
		int sum = 0;
		for(int i = 0 ; i < weight.size(); i++)
		{
			sum += weight.get(i) *  input.get(i);
		}
		if(sum >= 0)
			return 1;
		else
			return 0;
	}
}
