import java.util.Arrays;

public class Population {

    public Mutant[] mutants;

    public Population(int populationSize) {
        mutants = new Mutant[populationSize];
        this.initialize();
    }

    public void initialize() {
        for (int i = 0; i < mutants.length; i++) {
            mutants[i] = new Mutant(Var.NUMBER_OF_GENES);
        }
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