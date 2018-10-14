import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Tuning_Eli {

	public static void main(String args[]) throws FileNotFoundException {

		// SET PARAMATERS
		int nRuns = 10;
		String runfile = "runTuning.sh";
		String filename = "output.txt";

		double[] fitnessArray = new double[nRuns];

		CommandToString com = new CommandToString();

		PrintWriter out = new PrintWriter(filename);

		out.println("HERE YOU CAN PRINT EXTRA INFORMATION ABOUT THIS RUN!" );
		out.println("VARIABLES THAT YOU CHOOSE, WHATEVER YOU WANT" );
		out.println(":) :) :) :) :)\n" );

		// Take the avg performance of 5 runs:
		for (int i=0; i < nRuns; i++){

			// Print the result:
			String[] cmd = {"sh",runfile};
			String result = com.executeCommand(cmd);

			out.println("XRUN: " + i + "\n" + result + "X\n" );

//			// Parse the result:
//			String delims = "[ \n]+";
//			String[] tokens = result.split("\\r?\\n")[0].split(delims);
//			double performance = Double.parseDouble(tokens[1]);
//			fitnessArray[i] = performance;
		}
		out.close ();


		System.out.println("DONE");

//		Statistics statisticsFitness = new Statistics(fitnessArray);
//
//		// OUTPUT!!!!
//		System.out.println("Number of runs: " + nRuns);
//		System.out.println("Mean: " + statisticsFitness.getMean());
//		System.out.println("Std: " + statisticsFitness.getStdDev());
	    
}


	
	
}

