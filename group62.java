import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Arrays;
import java.util.Random;
import java.util.Properties;

public class group62 implements ContestSubmission
{
	Random rnd_;
	ContestEvaluation evaluation_;
    private int evaluations_limit_;
	
	public group62()
	{
		rnd_ = new Random();
	}
	
	public void setSeed(long seed)
	{
		// Set seed of algortihms random process
		rnd_.setSeed(seed);
	}

	public void setEvaluation(ContestEvaluation evaluation)
	{
		// Set evaluation problem used in the run
		evaluation_ = evaluation;
		
		// Get evaluation properties
		Properties props = evaluation.getProperties();
        // Get evaluation limit
        evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
		// Property keys depend on specific evaluation
		// E.g. double param = Double.parseDouble(props.getProperty("property_name"));
        boolean isMultimodal = Boolean.parseBoolean(props.getProperty("Multimodal"));
        boolean hasStructure = Boolean.parseBoolean(props.getProperty("Regular"));
        boolean isSeparable = Boolean.parseBoolean(props.getProperty("Separable"));

		// Do sth with property values, e.g. specify relevant settings of your algorithm
        if(isMultimodal){
            // Do sth
        }else{
            // Do sth else
        }
    }
    
	public void run()
	{
		// Run your algorithm here

        int evals = 0;

        // Initializing population
        Population population = new Population(Var.POPULATION_SIZE);


        // Initialize generitcAlgorithm
        EvolutionAlgorithm evolutionAlgorithm = new EvolutionAlgorithm(population);
        int n_islands = 50;
        Islands islands = new Islands(n_islands);
        Population[] populations = islands.getPopulations();
        // Initialize storage
        MutantStorage mutantStorage = new MutantStorage(evaluations_limit_);

        // Run code until we run out of evalutions
        while(evals < evaluations_limit_) {

            // Calculate fitness of current generation
            for (int i = 0; i < n_islands; i++) {
                for (int j = 0; j < Var.POPULATION_SIZE; j++){
                    if (evals < evaluations_limit_) {
                        Mutant tempMutant = populations[i].getMutants()[j];
                        Double fitness = (double) evaluation_.evaluate(tempMutant.getValues());
                        tempMutant.setFitness(fitness);
                        mutantStorage.store(tempMutant);
                        evals++;
                    }
                }
            }

            // Evolving all populations

            islands.evolve();

//            if (evals >=19){
//                break ;                        ////////////////////////
//            }
        }


	}
}