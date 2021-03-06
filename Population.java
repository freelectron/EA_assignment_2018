import java.util.Arrays;

public class Population {

    private Mutant[] mutants;

    public Population(int populationSize) {
        mutants = new Mutant[populationSize];
    }

    public Population initialize() {
        for (int i = 0; i < mutants.length; i++) {
            mutants[i] = new Mutant(Var.NUMBER_OF_GENES).initialize();
        }
        return this;
    }

    public Mutant[] getMutants() {
        return mutants;
    }

    public void sortByFitness() {
        Arrays.sort(mutants, (mutant1, mutant2) -> {
            int flag = 0;
            if (mutant1.getFitness() > mutant2.getFitness()) {
                flag = -1;
            } else if (mutant1.getFitness() < mutant2.getFitness()) {
                flag = 1;
            }
            return flag;
        });
    }

}