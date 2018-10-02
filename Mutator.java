import java.util.Random;

public class Mutator {


    public void mutatePopulation_UMN(Population population) {

        Mutant[] mutants = population.getMutants();
        for (int i = 0; i < mutants.length; i++) {
            mutateMutant(mutants[i]);
        }
    }

    private void mutateMutant(Mutant mutant) {
        /*
        initialize
        */
        Random r = new Random() ;
        double[] genes = mutant.getValues();
        double[] standard_deviations = mutant.getSDs();

        /*
        constants
         */
        double tau_1 = Var.TAU_1;
        double tau_2 = Var.TAU_2;
        double boundry = Var.BOUNDRY;
        for (int i = 0; i < genes.length; i++) {
            /*
            mutate sigma's
             */
            standard_deviations[i] = standard_deviations[i] * Math.exp(tau_1 * r.nextGaussian() + tau_2 * r.nextGaussian());

            if (standard_deviations[i] < boundry) {
                standard_deviations[i] = boundry;
            }
            /*
            mutate genes
            */
            genes[i] = genes[i] + standard_deviations[i] * r.nextGaussian();
        }
        /*
        save the new genes and standard_deviations
        */
        mutant.setValues(genes);
        mutant.setSDs(standard_deviations);
    }


//    public void mutatePopulation_stupid() {
//        population.sortByFitness();
//
//        Population mutatedPopulation = new Population(Var.POPULATION_SIZE);
//
//
//        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
//
//            for (int j = 0; j < Var.NUMBER_OF_GENES; j++) {
//                mutatedPopulation.getMutants()[i].getValues()[j] = population.getMutants()[0].getValues()[j] + Var.MUTATION_RATE * (Math.random() - 0.5);
//
//            }
//
//        }
//        population = mutatedPopulation;
//    }

}
