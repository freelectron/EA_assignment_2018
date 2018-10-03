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

	public String executeCommand(String command) {
		StringBuffer output = new StringBuffer();
	    Process p;
	    String line = ""; 
	    try {
	        p = Runtime.getRuntime().exec(command);
	        p.waitFor();
	        BufferedReader reader = 
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));

	                  
	        // while ((line = reader.readLine())!= null) {
	        //     output.append(line + "\n");
	        // }
	        while (reader.ready()){
	        	line = reader.readLine();
	        	// output.append(line);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    // return output.toString();
	    return line;
}



	public static void main(String args[]) {

		Tuning com = new Tuning();

		// Creating random parameter vectors
		Var.SELECTOR = Tuning.selector[new Random().nextInt(Tuning.selector.length)];
		Var.RECOMBINATOR = Tuning.recombinator[new Random().nextInt(Tuning.recombinator.length)];
		Var.MUTATOR = Tuning.mutator[new Random().nextInt(Tuning.mutator.length)];
		Var.POPULATION_SIZE = Tuning.min_population_size + (int)(Math.random() * ((Tuning.max_population_size - Tuning.min_population_size) + 1));
		Var.MUTATION_RATE = (Math.random() * (Tuning.max_mutation_rate - Tuning.min_mutation_rate)) + Tuning.min_mutation_rate;

		// Print all the values:
		System.out.println(Var.SELECTOR+"\t"+Var.RECOMBINATOR+"\t"+Var.MUTATOR+"\t"+Var.POPULATION_SIZE+"\t"+Var.MUTATION_RATE);

		// Execute commands:
	    
	    System.out.println(com.executeCommand("javac -cp contest.jar group62.java Var.java Mutant.java Population.java EvolutionAlgorithm.java Mutator.java Selector.java Recombinator.java MutantStorage.java Cholesky.java"));
	    System.out.println(com.executeCommand("jar cmf MainClass.txt submission.jar group62.class Mutant.class Var.class Population.class EvolutionAlgorithm.class Mutator.class Selector.class Recombinator.class MutantStorage.class Cholesky.class"));
	    System.out.println(com.executeCommand("java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1"));

}


	
	
}

