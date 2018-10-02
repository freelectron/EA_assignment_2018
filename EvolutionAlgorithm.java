//import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

public class EvolutionAlgorithm {

    public Population population;

    public Mutator mutator;
    public Selector selector;
    public Recombinator recombinator;


    public EvolutionAlgorithm(Population population){

        this.population = population;
        this.mutator = new Mutator();
        this.selector = new Selector();
        this.recombinator = new Recombinator();
    }

    public void evolve() {

        // Start with PARENT SELECTION
//        selector.parentSelection_elitism(population, 20);

        selector.parentSelection_nr1only(population);
//        selector.parentSelection_rankBased_LR(population,2,10);
//       parentSelection_elitism(20) ;

        // Continue with RECOMBINATION
 //       recombinator.crossoverPopulation_nPoint(population, 5);
  //     crossoverPopulation_nPoint(5);

        // Finish with MUTATION

        mutator.mutatePopulation_UMN(population);
//        mutatePopulation_stupid();
    }


}