//import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

public class EvolutionAlgorithm {

    public Population population;
    public Population populationBefore;

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
//        selector.parentSelection_nr1only(population);
//        selector.parentSelection_rankBased_LR(population,2,10);


        // Continue with RECOMBINATION
        recombinator.crossoverPopulation_nPoint(population, 5);

        // Finish with MUTATION
//        mutator.mutatePopulation_UMN(population);
        mutator.mutatePopulation_CMN(population);
//        mutatePopulation_stupid();

    }
    public void evolve_before() {

        Population populationNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){
            Mutant tempMutant = new Mutant(Var.NUMBER_OF_GENES) ;
            tempMutant = recombinator.differentialMutation(population, 0.5, i) ;
            populationNew.getMutants()[i].setMutant(tempMutant) ;
        }
        Population populationNewNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){
            Mutant tempMutant = new Mutant(Var.NUMBER_OF_GENES) ;
            tempMutant = recombinator.differentialCrossover(populationNew.getMutants()[i], population.getMutants()[i], 0.5);
            populationNewNew.getMutants()[i].setMutant(teit utant);
        }
        this.populationBefore = populationNewNew ;
    }

    public void evolveAfter() {
//        Population populationAfter = new Population(Var.POPULATION_SIZE) ;
        population = recombinator.differentialSelection(populationBefore,population);

    }
}