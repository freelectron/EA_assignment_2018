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
//        Mutant kid1 = new Mutant(10) ;
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

    public void crossoverPopulation_Arithmetic(Population population) {
        /*
        perform crossover
        */
        Mutant[] mutants = population.getMutants();
        //function to select 2 parents
        for (int i = 0; i < mutants.length; i=i+2){
            arithmeticCrossover(mutants[i],mutants[i+1]) ;
        }
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
