//import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

public class EvolutionAlgorithm {

    public Population population;
    public Mutator mutator;
    public Selector selector;
    public Recombinator recombinator;
    public boolean flip;

    public EvolutionAlgorithm(Population population){

        this.population = population;
        this.mutator = new Mutator();
        this.selector = new Selector();
        this.recombinator = new Recombinator();
    }

    public void evolve() {

        // Start with PARENT SELECTION
        if (Var.SELECTOR == "parentSelection_elitism"){
            selector.parentSelection_elitism(population, 20);
        }
        if (Var.SELECTOR == "parentSelection_rankBased_LR"){
            selector.parentSelection_rankBased_LR(population, 2, 10);
        }
        if (Var.SELECTOR == "parentSelection_nr1only"){
            selector.parentSelection_nr1only(population);
        }



        // Continue with RECOMBINATION
        if (Var.RECOMBINATOR == "crossoverPopulation_nPoint"){
            recombinator.crossoverPopulation_nPoint(population, 5);
        }
        if (Var.RECOMBINATOR == "crossoverPopulation_Arithmetic"){
            recombinator.crossoverPopulation_Arithmetic(population);
        }




        // Finish with MUTATION
        if (Var.MUTATOR == "mutatePopulation_UMN"){
            mutator.mutatePopulation_UMN(population);
        }
        if (Var.MUTATOR == "mutatePopulation_CMN"){
            mutator.mutatePopulation_CMN(population);
        }
        if (Var.MUTATOR == "mutatePopulation_Momentum"){
            mutator.mutatePopulation_Momentum(population);
        }

    }


}