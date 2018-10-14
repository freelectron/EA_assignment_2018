import java.util.Properties;
import java.util.Random;

public class Evaluate
{
    Var var;

    public Evaluate(){
        var = new Var();
    }

    public void setParamters (int populationSize, double F, double CR) {
        var.setPopulationSize(populationSize);
        var.setF(F);
        var.setCR(CR);
    }

    public double evaluate()
    {

        double maxFitness = 0;

        // ------------------------------------------------------------------------------------------

        // BenEva evaluation_ = new BenEva();
        SchEva evaluation_ = new SchEva();
        // KatEva evaluation_ = new KatEva();

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

        // Calculate fitness of current generation
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            if (evals < evaluations_limit_) {

                Mutant tempMutant = evolutionAlgorithm.population.getMutants()[i];
                Double fitness = (double) evaluation_.evaluate(tempMutant.getGenes());
                tempMutant.setFitness(fitness);

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

                    Mutant tempMutant = evolutionAlgorithm.populationBefore.getMutants()[i];
                    Double fitness = (double) evaluation_.evaluate(tempMutant.getGenes());
                    tempMutant.setFitness(fitness);
                    evals++;
                    // Select survivors
                }
            }

            // Evolving current population
            evolutionAlgorithm.evolveAfter();

            evolutionAlgorithm.population.sortByFitness();
            double currentCR = evolutionAlgorithm.population.getMutants()[0].getCR();
            double currentF = evolutionAlgorithm.population.getMutants()[0].getF();

            if (evolutionAlgorithm.population.getMutants()[0].getFitness() > maxFitness) {
                maxFitness = evolutionAlgorithm.population.getMutants()[0].getFitness();
            }
            System.out.println("eval: " + evals + ", " + currentF + ", " + currentCR + ", " + maxFitness );
        }
        return maxFitness;
    }
}