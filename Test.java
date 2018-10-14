import java.util.Arrays;

public class Test {
    public static void main(String args[]){
        Population population = new population();
        EvolutionAlgorithm evolutionAlgorithm = new EvolutionAlgorithm(population);
        Mutant testMutant = new Mutant(9);

        for(int i = 0; i < 10; i++){
            System.out.println(Arrays.toString(testMutant.getValues()));
            evolutionAlgorithm.mutateMutant(testMutant);
        }


    }
}