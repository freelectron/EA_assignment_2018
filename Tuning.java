import java.util.Arrays;
import java.util.Random;
import java.io.*;


public class Tuning{

	public static String[] selector = {"parentSelection_elitism","parentSelection_rankBased_LR", "parentSelection_nr1only"};
	public static String[] recombinator = {"crossoverPopulation_nPoint","crossoverPopulation_Arithmetic"};
	public static String[] mutator = {"mutatePopulation_UMN","mutatePopulation_CMN","mutatePopulation_Momentum"};
	public static int max_population_size = 15;
	public static int min_population_size = 10;
	public static double max_mutation_rate = 0.5;
	public static double min_mutation_rate = 0.00001;

	public String executeCommand(String[] command) {
		StringBuffer output = new StringBuffer();
	    Process p;
	    try {
	    	// System.out.println(command);
	        p = Runtime.getRuntime().exec(command);
	        p.waitFor();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = ""; 
	        // System.out.println(p.getInputStream());
	        while ((line = reader.readLine())!= null) {

	            output.append(line + "\n");
	        }

	    } 
	    catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println(output.toString());

	    return output.toString();
	    // return line;

		// ProcessBuilder pb = new ProcessBuilder("bash", "-c", command); 
		// StringBuilder builder = new StringBuilder();
		// 	 try
		// 	 {
		// 	 Process process = pb.start();
		// 	 process.waitFor();
		// 	 BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		// 	 String line = null;
		// 	 while ( (line = reader.readLine()) != null) {
		// 	 builder.append(line);
		// 	 }
			 
		// 	 }
		// 	 catch (IOException e)
		// 	 { System.out.print("error");
		// 	 e.printStackTrace();
		// 	 }
		// 	 String result = builder.toString();
		// 	 System.out.print(result);
		// 	 System.out.println("end of script execution");
		// 	 return result;


}



	public static void main(String args[]) {

		Tuning com = new Tuning();
		Parameter_vector[] pv = new Parameter_vector[20];

		// Creating random parameter vectors
		for (int i=0; i<20; i++){
			pv[i] = new Parameter_vector(Tuning.selector[new Random().nextInt(Tuning.selector.length)],Tuning.recombinator[new Random().nextInt(Tuning.recombinator.length)],Tuning.mutator[new Random().nextInt(Tuning.mutator.length)],Tuning.min_population_size + (int)(Math.random() * ((Tuning.max_population_size - Tuning.min_population_size) + 1)),(Math.random() * (Tuning.max_mutation_rate - Tuning.min_mutation_rate)) + Tuning.min_mutation_rate);
		}
		// String[] cmd = {"/bin/sh","ping google.com"};
		

		// Execute cases and evaluate:
		for (int i=0; i<20; i++){
			pv[i].setVariables();
			// Print all the values for reference:
			System.out.println(Var.SELECTOR+"\t"+Var.RECOMBINATOR+"\t"+Var.MUTATOR+"\t"+Var.POPULATION_SIZE+"\t"+Var.MUTATION_RATE);
			
			// Take the avg performance of 5 runs:
			for (int j=1; j<=5; j++){
				// Print the result:
				String[] cmd = {"sh","/home/cgnarendiran/Desktop/semeter1/evolutionary_computing/EA_assignment_2018/runb.sh"};
				String result = com.executeCommand(cmd);
				// String result = com.executeCommand("javac -cp contest.jar group62.java Var.java Mutant.java Population.java EvolutionAlgorithm.java Mutator.java Selector.java Recombinator.java MutantStorage.java Cholesky.java  &&  jar cmf MainClass.txt submission.jar group62.class Mutant.class Var.class Population.class EvolutionAlgorithm.class Mutator.class Selector.class Recombinator.class MutantStorage.class Cholesky.class  &&  java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1");
				// Parse the result:
				String delims = "[ \n]+";
				String[] tokens = result.split("\\r?\\n")[0].split(delims);
				double performance = Double.parseDouble(tokens[1]);
				pv[i].score = pv[i].score + performance;
			}
			pv[i].score = pv[i].score/5;
			System.out.println(pv[i].score);

	
		}
		
		// Sort:
		

		// REVAC:
		// Choose the best vectors: (sort the vectors based on performance)
		// Store in the corpus:
		// Recombinate vectors:
		// Muatate vectors:
		// Evalaute again
		// Replace the least performing vectors in the corpus with the new best vectors

	    
}


	
	
}

