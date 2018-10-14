import java.util.Random;

public class Mutant {

    private double fitness;
    private double[] genes;
    private double CR;
    private double F;
    

    public Mutant(int length) {
        genes = new double[length];
        this.initialize();
    }
    /*
    constructing Mutant
     */

    public void initialize() {
        Random r = new Random();
        for (int i = 0; i < genes.length; i++) {
            genes[i] = (Math.random() * (Var.SEARCH_SPACE_MAX - Var.SEARCH_SPACE_MIN)) - Var.SEARCH_SPACE_MAX;
        }
        this.CR = 0.5;
        this.F = Var.F;
    }
    /*
    get statements
     */
    public double[] getGenes() {
        return genes;
    }
    public double getFitness() {
        return fitness;
    }

    public double getCR(){
        return CR;
    }

    public double getF(){
        return F;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    public void setCR(double CR) {
        this.CR = CR;
    }

    public void setF(double F) {
        this.F = F;
    }

    public void setMutant(Mutant mutant){
        for (int j = 0; j<Var.NUMBER_OF_GENES; j++) {
            this.genes[j] = mutant.getGenes()[j];
        }
        this.fitness = mutant.getFitness();
        this.CR = mutant.getCR();
        this.F = mutant.getF();
    }

    /* WE LOVE MUTANTS, not vectors! */

}