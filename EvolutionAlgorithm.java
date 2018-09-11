import java.util.Arrays;

public class EvolutionAlgorithm {

    public Population evolve(Population population) {

        return mutatePopulation(crossoverPopulation(population));
    }

    public Population crossoverPopulation(Population population) {
        return population;
    }

    public Population mutatePopulation(Population population) {

        population.sortByFitness();

        Population mutatedPopulation = new Population(Var.POPULATION_SIZE);
        mutatedPopulation.initialize();

        for (int i = 0; i < Var.POPULATION_SIZE; i++) {

            for (int j = 0; j < Var.NUMBER_OF_GENES; j++) {
                mutatedPopulation.getMutants()[i].getValues()[j] = population.getMutants()[0].getValues()[j] + Var.MUTATION_RATE * (Math.random() - 0.5);

            }



        }

        return mutatedPopulation;
    }


}