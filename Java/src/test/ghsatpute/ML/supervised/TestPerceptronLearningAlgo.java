package test.ghsatpute.ML.supervised;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ghsatpute.ML.supervised.PerceptronLearningAlgo;

public class TestPerceptronLearningAlgo {
	
	@Test
	public  void testPerceptronLearningAlgo()
	{
		PerceptronLearningAlgo PLA = new PerceptronLearningAlgo("pima-indians-diabetes.data");
		
		/*
		for(Double weight : PLA.getWeight())
		{
			System.out.println(weight +  "\t");
		}*/
		List<Double> list = new ArrayList<Double>();


		list.add(1.0);   // 1
		list.add(81.0);
		list.add(74.0);
		list.add(41.0);
		list.add(57.0);   // 5
		list.add(46.3);
		list.add(1.096);
		list.add(32.0);  // 8
		
		System.out.println(list.toString());
		System.out.println("Outcome for given list is : "+ PLA.getOutcome(list));
	}

}
