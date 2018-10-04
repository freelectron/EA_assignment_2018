import java.util.Arrays;
import java.util.Random;
import java.io.*;



public class Parameter_vector{  
    String selector;
    String recombinator;
    String mutator;
    int population_size;
    double mutation_size;
    // performance score of the parameter vector
    double score = 0.0; 
    // Constructor:
    Parameter_vector(String selector, String recombinator, String mutator, int population_size, double mutation_size){  
        this.selector=selector;  
        this.recombinator=recombinator;  
        this.mutator=mutator;
        this.population_size=population_size;
        this.mutation_size=mutation_size;
        this.score = score;
    }
    public void setVariables() {
    	Var.setSELECTOR(this.selector);
		Var.setRECOMBINATOR(this.recombinator);
		Var.setMUTATOR(this.mutator);
		Var.setPOPULATION_SIZE(this.population_size);
		Var.setMUTATION_RATE(this.mutation_size);

    } 
} 

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
	    try {
	    	System.out.println(command);
	        p = Runtime.getRuntime().exec(command);
	        p.waitFor();
	        BufferedReader reader = 
	                        new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = ""; 
	        System.out.println(p.getInputStream());
	        while ((line = reader.readLine())!= null) {

	            output.append(line + "\n");
	        }
	        // while (reader.ready()){
	        // 	line = reader.readLine();
	        // 	// output.append(line);
	        // }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    System.out.println(output.toString());

	    return output.toString();
	    // return line;
}



	public static void main(String args[]) {

		Tuning com = new Tuning();
		Parameter_vector[] pv = new Parameter_vector[20];

		// Creating random parameter vectors
		for (i=0; i=i+1; i<20){
			pv[i] = {Tuning.selector[new Random().nextInt(Tuning.selector.length)],Tuning.recombinator[new Random().nextInt(Tuning.recombinator.length)],
				Tuning.mutator[new Random().nextInt(Tuning.mutator.length)],Tuning.min_population_size + (int)(Math.random() * ((Tuning.max_population_size - Tuning.min_population_size) + 1)),
				(Math.random() * (Tuning.max_mutation_rate - Tuning.min_mutation_rate)) + Tuning.min_mutation_rate}
		}

		// Execute cases:
		for (i=0; i=i+1; i<20){
			pv[i].setVariables();
			// Print all the values for reference:
			System.out.println(Var.SELECTOR+"\t"+Var.RECOMBINATOR+"\t"+Var.MUTATOR+"\t"+Var.POPULATION_SIZE+"\t"+Var.MUTATION_RATE);
			// Print the result:
			System.out.println(com.executeCommand("ls && javac -cp contest.jar group62.java Var.java Mutant.java Population.java EvolutionAlgorithm.java Mutator.java Selector.java Recombinator.java MutantStorage.java Cholesky.java  &&  jar cmf MainClass.txt submission.jar group62.class Mutant.class Var.class Population.class EvolutionAlgorithm.class Mutator.class Selector.class Recombinator.class MutantStorage.class Cholesky.class  &&  java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1"));
			String result = com.executeCommand("ls && javac -cp contest.jar group62.java Var.java Mutant.java Population.java EvolutionAlgorithm.java Mutator.java Selector.java Recombinator.java MutantStorage.java Cholesky.java  &&  jar cmf MainClass.txt submission.jar group62.class Mutant.class Var.class Population.class EvolutionAlgorithm.class Mutator.class Selector.class Recombinator.class MutantStorage.class Cholesky.class  &&  java -jar testrun.jar -submission=group62 -evaluation=BentCigarFunction -seed=1");
			// Parse the result and obtain performance.
			double performance = 0.0
			pv[i].score = performance;
			
		}
		
		

		
		// Execute commands:
	    
	    	    // System.out.println(com.executeCommand(""));
	    // System.out.println(com.executeCommand(""));

}


	
	
}

