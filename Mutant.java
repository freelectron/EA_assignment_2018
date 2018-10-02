//import org.vu.contest.ContestSubmission;
//import org.vu.contest.ContestEvaluation;

import java.util.Properties;
import java.util.Random;

public class Mutant {

    private double fitness;

    private double[] genes;

    private double[] standard_deviations;

    private double[][] correlations;

    /*
    initializing genes array
     */


    public Mutant(int length) {
        genes = new double[length];
        standard_deviations = new double[length];
        correlations = new double[length][length];
        this.initialize();
    }
    /*
    constructing Mutant
     */

    public void initialize() {
        for (int i = 0; i < genes.length; i++) {
            genes[i] = (Math.random() * (Var.SEARCH_SPACE_MAX - Var.SEARCH_SPACE_MIN)) - Var.SEARCH_SPACE_MAX;
            standard_deviations[i] = 0;
            for (int j=0; j <genes.length; j++){
                correlations[i][j] = 0;
            }
        }
    }
    /*
    get statements
     */
    public double[] getValues() {
        return genes;
    }

    public double getFitness() {
        return fitness;
    }

    public double[] getSDs() {
        return standard_deviations;
    }

    public double[][] getCorrelations() { return correlations; }


    /*
    set statements
     */
    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setValues(double[] genes){
        this.genes = genes;
    }

    public void setSDs(double[] SDs){
        standard_deviations = SDs;
    }

    public void setMutant(Mutant mutant){
        for (int j = 0; j<Var.NUMBER_OF_GENES; j++) {
            this.genes[j] = mutant.getValues()[j];
            this.standard_deviations[j] = mutant.getSDs()[j];
        }
    }

    /* WE LOVE MUTANTS, not vectors! */

}