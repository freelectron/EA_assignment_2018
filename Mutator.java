import java.util.Random;

public class Mutator {


    public void mutatePopulation_UMN(Population population) {

        Mutant[] mutants = population.getMutants();
        for (int i = 0; i < mutants.length; i++) {
            mutateMutant(mutants[i]);
        }
    }

    public void mutatePopulation_CMN(Population population) {

        Mutant[] mutants = population.getMutants();
        for (int i = 0; i < mutants.length; i++) {
            mutateMutantCorrelated(mutants[i]);
        }
    }

    public void mutatePopulation_Momentum(Population population) {

        Mutant[] mutants = population.getMutants();
        for (int i = 0; i < mutants.length; i++) {
            mutateMutantMomentum(mutants[i]);
        }
    }

    private void mutateMutantMomentum(Mutant mutant) {
               /*
        initialize
        */
        Random r = new Random() ;
        double[] genes = mutant.getValues();
        double[] standard_deviations = mutant.getSDs();
        double[] momentum = mutant.getMomentum();

        /*
        constants
         */
        double tau_1 = Var.TAU_1;
        double tau_2 = Var.TAU_2;
        double boundry = Var.BOUNDRY;
        for (int i = 0; i < genes.length; i++) {
            /*
            mutate sigma's
             */
            momentum[i] = Var.TAU_MOMENTUM_MU * momentum[i] + r.nextGaussian() * Var.TAU_MOMENTUM_SIGMA*standard_deviations[i];
            /*
            mutate genes
            */
            genes[i] = genes[i] + standard_deviations[i] * r.nextGaussian() + momentum[i];
        }
        /*
        save the new genes and standard_deviations
        */
        mutant.setValues(genes);
        mutant.setSDs(standard_deviations);
    }


    private void mutateMutant(Mutant mutant) {
        /*
        initialize
        */
        Random r = new Random() ;
        double[] genes = mutant.getValues();
        double[] standard_deviations = mutant.getSDs();

        /*
        constants
         */
        double tau_1 = Var.TAU_1;
        double tau_2 = Var.TAU_2;
        double boundry = Var.BOUNDRY;
        for (int i = 0; i < genes.length; i++) {
            /*
            mutate sigma's
             */
            standard_deviations[i] = standard_deviations[i] * Math.exp(tau_1 * r.nextGaussian() + tau_2 * r.nextGaussian());

            if (standard_deviations[i] < boundry) {
                standard_deviations[i] = boundry;
            }
            /*
            mutate genes
            */
            genes[i] = genes[i] + standard_deviations[i] * r.nextGaussian();
        }
        /*
        save the new genes and standard_deviations
        */
        mutant.setValues(genes);
        mutant.setSDs(standard_deviations);
    }

    private void mutateMutantCorrelated(Mutant mutant){
        /*
        initialize
         */
        Random r = new Random();
        double[] genes = mutant.getValues();
        double[] standard_deviations = mutant.getSDs();
        double[][] correlations = mutant.getCorrelations();
        double[] means = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        /*
        constants
         */
        double tau_1 = Var.TAU_1;
        double tau_2 = Var.TAU_2;


        double boundry = Var.BOUNDRY;
        double beta = Var.BETA;
        double[][] covariance_matrix = new double[genes.length][genes.length];

        for (int i = 0; i < genes.length; i++) {
            /*
            mutate sigma's
             */
            standard_deviations[i] = standard_deviations[i] * Math.exp(tau_1 * r.nextGaussian() + tau_2 * r.nextGaussian());

            if (standard_deviations[i] < boundry) {
                standard_deviations[i] = boundry;
            }

        }

        /*
        mutate alpha's
        */
        for (int i=0; i<genes.length; i++) {
            for (int j=0; j<genes.length; j++) {
                correlations[i][j] = correlations[i][j] + beta * r.nextGaussian();
            }
        }

        for (int i=0; i<genes.length; i++) {
            for (int j=0; j<genes.length; j++) {
                if (i == j){

                } else {
                    correlations[i][j] = correlations[j][i];
                    correlations[i][j] = ((double) Math.round(correlations[i][j] *10000))/10000;
                }

            }
        }


        // Covariance matrix building
        for (int i=0; i<genes.length; i++) {

            for (int j = 0; j < genes.length; j++) {
                if(i==j){
                    covariance_matrix[i][j] = standard_deviations[i] * standard_deviations[i];
                }
                else{
                    covariance_matrix[i][j] = 0.5 * (Math.pow(standard_deviations[i], 2) - Math.pow(standard_deviations[j], 2)) * Math.tan(2 * correlations[i][j]);
                    // covariance_matrix[i][j] = ((double) Math.round(covariance_matrix[i][j] *1000000))/1000000;

                }

            }
        }

//        for (int i=0; i<genes.length; i++) {
//            for (int j=0; j<genes.length; j++) {
//                covariance_matrix[i][j] = covariance_matrix[j][i];
//                // covariance_matrix[i][j] = ((double) Math.round(covariance_matrix[i][j] *10000))/10000;
//
//            }
//        }

        /*
        mutate genes
        */

        double[][] L = Cholesky.cholesky(covariance_matrix);
        double[] z = new double[genes.length];

        for (int i=0; i<genes.length; i++) {
            z[i] = r.nextGaussian();
        }

        double[] mutationAddition = new double[genes.length];

        for (int i=0; i<genes.length; i++) {
            mutationAddition[i] = 0;

            for (int j=0; j<genes.length; j++) {
                mutationAddition[i] += L[j][i]*z[j];
            }
        }

//        MultivariateNormalDistribution trekking = new MultivariateNormalDistribution(means, covariance_matrix);
//        double[] addition = trekking.sample();

//        Array2DRowRealMatrix realMatrix = new Array2DRowRealMatrix(covariance_matrix);
//        CholeskyDecomposition llt = new CholeskyDecomposition(realMatrix);
//        RealMatrix L_trans = llt.getLT();
//        System.out.println(L_trans);

        for(int i = 0; i < genes.length; i++){

            genes[i] = genes[i] + mutationAddition[i];
            //        if (genes[i] < Var.SEARCH_SPACE_MIN){
            //            genes[i] = Var.SEARCH_SPACE_MIN;
            //        }
            //        if (genes[i] > Var.SEARCH_SPACE_MAX){
            //            genes[i] = Var.SEARCH_SPACE_MAX;
            //        }
        }




        /*
        save the new genes and standard_deviations
         */
        mutant.setValues(genes);
        mutant.setSDs(standard_deviations);

    }


//    public void mutatePopulation_stupid() {
//        population.sortByFitness();
//
//        Population mutatedPopulation = new Population(Var.POPULATION_SIZE);
//
//
//        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
//
//            for (int j = 0; j < Var.NUMBER_OF_GENES; j++) {
//                mutatedPopulation.getMutants()[i].getValues()[j] = population.getMutants()[0].getValues()[j] + Var.MUTATION_RATE * (Math.random() - 0.5);
//
//            }
//
//        }
//        population = mutatedPopulation;
//    }

}
