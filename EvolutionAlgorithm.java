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
//        parentSelection_rankBased_LR(2,10);
//        parentSelection_elitism(20) ;

        // Continue with RECOMBINATION
//        crossoverPopulation_nPoint(3);

        // Finish with MUTATION
        mutatePopulation_UMN() ;
//        mutatePopulation_stupid();
    }


    private void parentSelection_nr1only() {
        /*
        select the most fittest parent and do stuff with it
        */
        population.sortByFitness();
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            population.getMutants()[i].setMutant(population.mutants[0]);
        }
    }

    private void parentSelection_rankBased_LR(double s, int q) {
        /*
        select the most fittest parents linear ranking
        @ s = ranking parameter in the interval [1;2]
        @ q = number of parents to return
        */
        /**
         * This function NEEDS TESTING !!! decreases the result :( but it just needs some minor fix !!
         */
        population.sortByFitness();
        int mu = Var.POPULATION_SIZE;
//        double mu = (double) mu*1.0 ;
        double[] sample_probabilities;
        sample_probabilities = new double[mu];
        double sum = 0.0;
        for (int i = 0; i < mu; i++) {
            // fucked up implementation, coz the next line does not work for me (taken from the lecture slides)
//            sample_probabilities[i] = ((2 - s) / 1.0 * mu) + (2 * i * (s - 1) / mu * (mu - 1));
            sample_probabilities[mu-1-i] = ((2 - s) / 1.0 * mu) + (2 * i * (s - 1) / mu * (mu - 1));
            sum += sample_probabilities[mu-1-i] ;
        }
        for (int i = 0; i < mu; i++) {
            // fucked up implementation cont.
            sample_probabilities[i] = sample_probabilities[i]/sum ;
        }
        double[] pp = sample_probabilities ;
        double[] cdf = pp.clone();
        for (int i = 1; i < cdf.length; i++)
            cdf[i] += cdf[i - 1];
        int[] values = new int[q];
        for (int i = 0; i < q; i++) {
            values[i] = Math.abs(Arrays.binarySearch(cdf, Math.random()));
            // for some reason 0th element which was the most likely one, was never returned. why?
            // so i decrease the rank
            if (values[i]>0){
                values[i] = values[i] - 1 ;
            }
        }
        // new population is the chosen parents
        for (int i = 0; i < q; i++) {
            population.getMutants()[i].setMutant(population.mutants[values[i]]) ;
        }
    }

    private void parentSelection_elitism(int pp) {
        /*
        select the most fittest parents for reco
        @ pp = percentage to keep e.g. 20 30 40 etc
        */
        population.sortByFitness() ;
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            if ( (i/(Var.POPULATION_SIZE*1.0)*100) <= pp*1.0){
                population.getMutants()[i].setMutant(population.mutants[i]) ;
            }else{
                Random r = new Random();
                int j = r.nextInt(pp/10);
                population.getMutants()[i].setMutant(population.mutants[j]) ;
            }
        }
    }

    private void crossoverPopulation_nPoint(int n) {
        /*
        perform crossover
        */
        Mutant[] mutants = population.getMutants();
        //function to select 2 parents
        for (int i = 0; i < mutants.length; i=i+2){
            nPointCrossover(mutants[i],mutants[i+1], n ) ;
        }
    }

    private void nPointCrossover(Mutant mutant1, Mutant mutant2, int n) {
        /*
        perform N-point crossover
        @ n - should be less or equal to 9
        */
        double[] genes1 = mutant2.getValues() ;
        double[] genes2 = mutant2.getValues() ;
        double[] kid1_genes = mutant1.getValues() ;
        double[] kid2_genes = mutant2.getValues() ;
//        Mutant kid1 = new Mutant(10) ;
        boolean f = true ;
        if ((10 % n) == 0){
            for (int i = 0; i < (10/n); i = i+n){
                for (int j = i; j < n; j++){
                    if (f){
                        f = false ;
                        kid2_genes[j] = genes1[9-j] ;
                    } else{
                        kid1_genes[j] = genes2[j] ;
                    }
                }
            }
        } else{
            for (int i = 0; i < ((10/n)-1); i = i+n){
                for (int j = i; j < n; j++){
                    if (f){
                        f = false ;
                        kid2_genes[j] = genes1[9-j] ;
                    } else{
                        kid1_genes[j] = genes2[j] ;
                    }
                }
            }
            // what to do with the last allele
            if (Math.random()>0.5){
                kid2_genes[9] = genes2[9] ;
                kid1_genes[9] = genes1[9] ;
            } else{
                kid2_genes[9] = genes1[9] ;
                kid1_genes[9] = genes2[9] ;
            }
        }
        mutant1.setValues(kid1_genes) ;
        mutant2.setValues(kid2_genes) ;
    }

    private void crossoverPopulation_Arithmetic() {
        /*
        perform crossover
        */
        Mutant[] mutants = population.getMutants();
        //function to select 2 parents
        for (int i = 0; i < mutants.length; i=i+2){
            arithmeticCrossover(mutants[i],mutants[i+1]) ;
        }
    }

    private void arithmeticCrossover(Mutant mutant1, Mutant mutant2) {
        /*
        perform some arithmetic crossover
        */
        double[] genes1 = mutant2.getValues() ;
        double[] genes2 = mutant2.getValues() ;
        double[] kid1_genes = mutant1.getValues() ;
        double[] kid2_genes = mutant2.getValues() ;
        boolean f = true ;
        //

        //
        mutant1.setValues(kid1_genes) ;
        mutant2.setValues(kid2_genes) ;
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