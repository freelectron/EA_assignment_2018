//import java.util.Arrays;

import java.util.Arrays;
import java.util.Random;

public class EvolutionAlgorithm {

    public Population population;
    public Population populationBefore;
    public DE dE;


    public EvolutionAlgorithm(Population population){

        this.population = population;
        this.dE = new DE();
    }

    public void evolveBefore() {

        population.sortByFitness();

        Population populationNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){

            double usedF = dE.mutateF(population.getMutants()[i].getF());
            Mutant tempMutant = dE.differentialMutation(population, usedF, i) ;
            tempMutant.setF(usedF);

            populationNew.getMutants()[i].setMutant(tempMutant);
        }

        Population populationNewNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){

            double usedCR = dE.mutateCR( population.getMutants()[i].getCR() );
            Mutant tempMutant = dE.differentialCrossover(population.getMutants()[i], populationNew.getMutants()[i], usedCR);
            tempMutant.setCR(usedCR);
            tempMutant.setF(populationNew.getMutants()[i].getF());

            populationNewNew.getMutants()[i].setMutant(tempMutant);
        }

        this.populationBefore = populationNewNew ;
    }

    public void evolveBeforeCurrentToBest() {

        population.sortByFitness();

        Population populationNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){

            double usedF = dE.mutateF(population.getMutants()[i].getF());

            // double usedF = dE.randomF();
            Mutant tempMutant = dE.differentialMutationCurrentToBest(population, usedF, i) ;
            tempMutant.setF(usedF);

            populationNew.getMutants()[i].setMutant(tempMutant);
        }

        Population populationNewNew = new Population(Var.POPULATION_SIZE) ;
        for (int i = 0; i<Var.POPULATION_SIZE; i++){

            double usedCR = dE.mutateCR( population.getMutants()[i].getCR() );
            Mutant tempMutant = dE.differentialCrossover(population.getMutants()[i], populationNew.getMutants()[i], usedCR);
            tempMutant.setCR(usedCR);
            tempMutant.setF(populationNew.getMutants()[i].getF());

            populationNewNew.getMutants()[i].setMutant(tempMutant);
        }

        this.populationBefore = populationNewNew ;
    }

    public void evolveAfter() {
        this.population = dE.differentialSelectionCR5(populationBefore,population);
        this.population = dE.replaceShitty(population, Var.KILL);
        this.population = dE.replaceBest(population, Var.KILL_BEST);
    }
}