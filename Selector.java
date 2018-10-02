import java.util.Arrays;
import java.util.Random;

public class Selector {

    public void parentSelection_nr1only(Population population) {
        /*
        select the most fittest parent and do stuff with it
        */
        population.sortByFitness();
        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            population.getMutants()[i].setMutant(population.mutants[0]);
        }
    }

    public void parentSelection_rankBased_LR(Population population, double s, int q) {
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

    public void parentSelection_elitism(Population population, int pp) {
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
    }}
