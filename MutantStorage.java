import java.util.Arrays;

public class MutantStorage {


    private double[][] mutantStorageGenes;

    private double[] mutantStorageFitness;

    private int current;

    public MutantStorage(int numberOfIterations){

        mutantStorageGenes = new double[numberOfIterations][Var.NUMBER_OF_GENES];
        mutantStorageFitness = new double[numberOfIterations];
        current = 0;

    }

    public void store(Mutant mutant) {
        for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
            mutantStorageGenes[current][i] = mutant.getValues()[i];
        }

        mutantStorageFitness[current] = mutant.getFitness();

        current += 1;
    }

    public double[] getGenes(int location){
        return mutantStorageGenes[location];
    }

    public double getFitness(int location){
        return mutantStorageFitness[location];
    }

    private double distance(double[] genes1, double[] genes2) {
        double d = 0;
        for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
            d += Math.pow(genes1[i]-genes2[i],2);
        }
        return Math.sqrt(d);
    }

    public double[] distanceFromNeighbours(Mutant mutant){
        double[] distanceArray = new double[current];
        double[] mutantGenes = mutant.getValues();

        for (int i = 0; i < current; i++){
            distanceArray[i] = distance(mutantStorageGenes[i],mutantGenes);
        }
        return distanceArray;
    }

    public double[][] NeighboursGenesWithinRange(Mutant mutant, double r) {
        double[] distanceArray = distanceFromNeighbours(mutant);
        int numberOfNeighbours = 0;

        for (int i = 0; i < current; i++) {
            if (distanceArray[i] < r) {
                numberOfNeighbours += 1;
            }
        }

        double[][] actualNeighbours = new double[numberOfNeighbours][Var.NUMBER_OF_GENES];
        int counter = 0;
        for (int i = 0; i < current; i++) {
            if (distanceArray[i] < r) {
                for (int j = 0; j < Var.NUMBER_OF_GENES; j++){
                    actualNeighbours[counter][j] = mutantStorageGenes[i][j];
                }
                counter++;
            }
        }
        return actualNeighbours;
    }

    public double[] NeighboursFitnessWithinRange(Mutant mutant, double r) {
        double[] distanceArray = distanceFromNeighbours(mutant);
        int numberOfNeighbours = 0;

        for (int i = 0; i < current; i++) {
            if (distanceArray[i] < r) {
                numberOfNeighbours += 1;
            }
        }

        double[] actualNeighboursFitness = new double[numberOfNeighbours];
        int counter = 0;
        for (int i = 0; i < current; i++) {
            if (distanceArray[i] < r) {
                actualNeighboursFitness[counter] = mutantStorageFitness[i];
                counter++;
            }
        }
        return actualNeighboursFitness;
    }


}
