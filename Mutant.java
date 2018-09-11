import org.vu.contest.ContestSubmission;
import org.vu.contest.ContestEvaluation;

import java.util.Properties;

public class Mutant {

    private double fitness;

    private double[] genes;
    /*
    initializing genes array
     */



    public Mutant(int length) {
        genes = new double[length];
    }
    /*
    constructing Mutant
     */

    public Mutant initialize() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = (Math.random() * (Var.SEARCH_SPACE_MAX - Var.SEARCH_SPACE_MIN)) - Var.SEARCH_SPACE_MAX;
        }
        return this;
    }

    public double[] getValues() {
        return genes;
    }

    public void storeFitness(double fitness) {
        this.fitness = fitness;
    }

    public double getFitness() {
        return fitness;
    }

}