//import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

public class EvolutionAlgorithm {

    public Population population;

    public EvolutionAlgorithm(Population population){
        this.population = population;
    }

    public void evolve() {

        // Start with PARENT SELECTION
        parentSelection_nr1only();

        // Continue with RECOMBINATION


        // Finish with MUTATION
        mutatePopulation_UMN();

//        mutatePopulation_stupid();
    }


    private void parentSelection_nr1only() {
        population.sortByFitness();
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            population.getMutants()[i].setMutant(population.mutants[0]);
        }
    }



    private void crossoverPopulation() {
        ;
    }


    private void mutatePopulation_UMN() {

        Mutant[] mutants = population.getMutants();
        for (int i = 0; i < mutants.length; i++) {
            mutateMutant(mutants[i]);
        }
    }


    private void mutateMutant(Mutant mutant) {
        /*
        initialize
         */
        Random r = new Random();
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
//            if (genes[i] < Var.SEARCH_SPACE_MIN){
//                genes[i] = Var.SEARCH_SPACE_MIN;
//            }
//            if (genes[i] > Var.SEARCH_SPACE_MAX){
//                genes[i] = Var.SEARCH_SPACE_MAX;
//            }
        }

        /*
        save the new genes and standard_deviations
         */
        mutant.setValues(genes);
        mutant.setSDs(standard_deviations);
    }


    public void mutatePopulation_stupid() {
        population.sortByFitness();

        Population mutatedPopulation = new Population(Var.POPULATION_SIZE);


        for (int i = 0; i < Var.POPULATION_SIZE; i++) {

            for (int j = 0; j < Var.NUMBER_OF_GENES; j++) {
                mutatedPopulation.getMutants()[i].getValues()[j] = population.getMutants()[0].getValues()[j] + Var.MUTATION_RATE * (Math.random() - 0.5);

            }

        }
        population = mutatedPopulation;
    }
}