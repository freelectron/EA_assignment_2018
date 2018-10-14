import java.util.Random;
import java.util.Properties;

public class JustRun
{
    public static void main(String[] args)
    {
        Var var = new Var();

        // ------------------------------------------------------------------------------------------
        // CHOOSE FUNCTION HERE
        // KatEva evaluation_ = new KatEva();
        // BenEva evaluation_ = new BenEva();
        SchEva evaluation_ = new SchEva();
        // ------------------------------------------------------------------------------------------

        Random rnd_ = new Random();
        long seed = rnd_.nextLong();
        // System.out.println(seed);

        rnd_.setSeed(seed);

        // Get evaluation properties
        Properties props = evaluation_.getProperties();
        // Get evaluation limit
        int evaluations_limit_ = Integer.parseInt(props.getProperty("Evaluations"));
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

        // ------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------
        // ------------------------------------------------------------------------------------------
        // BELOW HERE IS THE CODE FROM OUR ALGORITHM

        int evals = 0;

        // Initializing population
        Population population = new Population(Var.POPULATION_SIZE);


        // Initialize generitcAlgorithm
        EvolutionAlgorithm evolutionAlgorithm = new EvolutionAlgorithm(population);

        // Initialize storage
        MutantStorage mutantStorage = new MutantStorage(evaluations_limit_);

        // Calculate fitness of current generation
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            if (evals < evaluations_limit_) {
                // Select parents
                // Apply crossover / mutation operators
                // Check fitness of unknown fuction

                Mutant tempMutant = evolutionAlgorithm.population.getMutants()[i];
                Double fitness = (double) evaluation_.evaluate(tempMutant.getValues());
                tempMutant.setFitness(fitness) ;
                mutantStorage.store(tempMutant);
//                    System.out.println(Arrays.toString(tempMutant.getValues())) ;  /////////
//                    System.out.println(evals);                                    ///////

                evals++;
                // Select survivors
            }
        }

        // Run code until we run out of evalutions
        while(evals < evaluations_limit_) {

            evolutionAlgorithm.evolveBefore();

            // Calculate fitness of current generation
            for (int i = 0; i < Var.POPULATION_SIZE; i++) {
                if (evals < evaluations_limit_) {
                    // Select parents
                    // Apply crossover / mutation operators
                    // Check fitness of unknown fuction

                    Mutant tempMutant = evolutionAlgorithm.populationBefore.getMutants()[i];
                    Double fitness = (double) evaluation_.evaluate(tempMutant.getValues());
                    tempMutant.setFitness(fitness) ;
                    mutantStorage.store(tempMutant);
//                    System.out.println(Arrays.toString(tempMutant.getValues())) ;  /////////
//                    System.out.println(evals);                                    ///////


                    evals++;
                    // Select survivors
                }
            }

            // Evolving current population
            evolutionAlgorithm.evolveAfter();

            evolutionAlgorithm.population.sortByFitness();
            System.out.println(evolutionAlgorithm.population.getMutants()[0].getFitness());
            // System.out.println();



//            if (evals >=19){
//                break ;                        ////////////////////////
//            }
        }

    }
}