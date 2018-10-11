import java.util.Random;

public class Recombinator {

    public void crossoverPopulation_nPoint(Population population, int n) {
        /*
        perform crossover
        */
        Mutant[] mutants = population.getMutants();
        //function to select 2 parents
        for (int i = 0; i < mutants.length; i=i+2){
            nPointCrossover(mutants[i],mutants[i+1], n ) ;
        }
    }

    private void nPointCrossover(Mutant mutant1, Mutant mutant2, int n) {
        /*
        perform N-point crossover
        @ n - should be less or equal to 9
        */
        double[] genes1 = mutant2.getValues() ;
        double[] genes2 = mutant2.getValues() ;
        double[] kid1_genes = mutant1.getValues() ;
        double[] kid2_genes = mutant2.getValues() ;
        boolean f = true ;
        if ((10 % n) == 0){
            for (int i = 0; i < (10/n); i = i+n){
                for (int j = i; j < n; j++){
                    if (f){
                        f = false ;
                        kid2_genes[j] = genes1[9-j] ;
                    } else{
                        kid1_genes[j] = genes2[j] ;
                    }
                }
            }
        } else{
            for (int i = 0; i < ((10/n)-1); i = i+n){
                for (int j = i; j < n; j++){
                    if (f){
                        f = false ;
                        kid2_genes[j] = genes1[9-j] ;
                    } else{
                        kid1_genes[j] = genes2[j] ;
                    }
                }
            }
            // what to do with the last allele
            if (Math.random()>0.5){
                kid2_genes[9] = genes2[9] ;
                kid1_genes[9] = genes1[9] ;
            } else{
                kid2_genes[9] = genes1[9] ;
                kid1_genes[9] = genes2[9] ;
            }
        }
        mutant1.setValues(kid1_genes) ;
        mutant2.setValues(kid2_genes) ;
    }

//    public void crossoverPopulation_Arithmetic(Mutant mutant1,Mutant mutant2) {
//        /*
//        perform crossover
//        */
////        Mutant[] mutants = population.getMutants();
//        //function to select 2 parents
//        for (int i = 0; i < mutants.length; i=i+2){
//            arithmeticCrossover(mutants[i],mutants[i+1]) ;
//        }
//    }

    public Mutant differentialMutation(Population population, double F, int locationX){
//        Mutant mutant1 ;Mutant mutant2, Mutant mutant3
        Random r = new Random() ;
        int[] arr = new int[3] ;
        boolean shitTest ;
        do {
            for (int i = 0; i < 3; i++) {
                double x = r.nextDouble();
                double  num = Math.floor(x * Var.POPULATION_SIZE);
                arr[i] = (int) num;
            }
            shitTest = false;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < i; j++) {
                    if (arr[i] == arr[j]) {
                        shitTest = true;
                    }
                }
                if(arr[i]==locationX ){
                    shitTest = true ;
                }
            }
        } while (shitTest == true) ;
        Mutant mutant1 = population.getMutants()[arr[0]] ;
        Mutant mutant2 = population.getMutants()[arr[1]] ;
        Mutant mutant3 = population.getMutants()[arr[2]] ;
        Mutant mutantY = new Mutant(Var.NUMBER_OF_GENES);
        for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
            mutantY.getValues()[i] = mutant1.getValues()[i] + F * (mutant2.getValues()[i] - mutant3.getValues()[i]);
        }
        return mutantY;
    }
    public Mutant differentialCrossover(Mutant mutant1, Mutant mutant2, double p){
        Mutant mutantZ = new Mutant(Var.NUMBER_OF_GENES) ;
        Random r = new Random() ;
        for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
            double x = r.nextDouble();
            if (x > p) {
                mutantZ.getValues()[i] = mutant1.getValues()[i] ;
            } else {
                mutantZ.getValues()[i] = mutant2.getValues()[i] ;
            }
        }
        return mutantZ ;
    }

    public Population differentialSelection(Population populationZ, Population populationX){
        Population populationNew = new Population(Var.POPULATION_SIZE);
        for (int i=0; i< Var.POPULATION_SIZE; i++){
            if (populationX.getMutants()[i].getFitness()>populationZ.getMutants()[i].getFitness()){
                populationNew.getMutants()[i].setMutant( populationX.getMutants()[i] ) ;
            } else {
                populationNew.getMutants()[i].setMutant( populationZ.getMutants()[i] ) ;
            }
        }
        return populationNew;
    }























    private void arithmeticCrossover(Mutant mutant1, Mutant mutant2) {
        /*
        perform some arithmetic crossover
        */
        double[] genes1 = mutant2.getValues() ;
        double[] genes2 = mutant2.getValues() ;
        double[] kid1_genes = mutant1.getValues() ;
        double[] kid2_genes = mutant2.getValues() ;
        boolean f = true ;
        //

        //
        mutant1.setValues(kid1_genes) ;
        mutant2.setValues(kid2_genes) ;
    }

}
