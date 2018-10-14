import java.util.Random;

public class DE {

    double totalCR;
    double totalF;
    double weight;
    double nrImproved;
    int times;


    public Mutant differentialMutation(Population population, double F, int locationX) {

        boolean outside;
        Mutant mutantY = new Mutant(Var.NUMBER_OF_GENES);

        do {
            outside = false;
            Random r = new Random();
            int[] arr = new int[3];
            boolean shitTest;
            do {
                for (int i = 0; i < 3; i++) {
                    double x = r.nextDouble();
                    double num = Math.floor(x * Var.POPULATION_SIZE);
                    arr[i] = (int) num;
                }
                shitTest = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < i; j++) {
                        if (arr[i] == arr[j]) {
                            shitTest = true;
                        }
                    }
                    if (arr[i] == locationX) {
                        shitTest = true;
                    }
                }
            } while (shitTest == true);

            Mutant mutant1 = population.getMutants()[arr[0]];
            Mutant mutant2 = population.getMutants()[arr[1]];
            Mutant mutant3 = population.getMutants()[arr[2]];
            for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
                mutantY.getGenes()[i] = mutant1.getGenes()[i] + F * (mutant2.getGenes()[i] - mutant3.getGenes()[i]);
                if (Math.abs(mutantY.getGenes()[i]) > 5) {
                    outside = true;
                }

            }
        } while (outside == true);

        return mutantY;
    }

    public Mutant differentialMutationCurrentToBest(Population population, double F, int locationX) {

        boolean outside;
        Mutant mutantY = new Mutant(Var.NUMBER_OF_GENES);

        do {
            outside = false;
            Random r = new Random();
            int[] arr = new int[3];
            boolean shitTest;
            do {
                for (int i = 0; i < 3; i++) {
                    double x = r.nextDouble();
                    double num = Math.floor(x * Var.POPULATION_SIZE);
                    arr[i] = (int) num;
                }
                shitTest = false;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < i; j++) {
                        if (arr[i] == arr[j]) {
                            shitTest = true;
                        }
                    }
                    if (arr[i] == locationX) {
                        shitTest = true;
                    }
                }
            } while (shitTest == true);

            Mutant mutant1 = population.getMutants()[arr[0]];
            Mutant mutant2 = population.getMutants()[arr[1]];
            Mutant mutant3 = population.getMutants()[arr[2]];
            Mutant mutantBest = population.getMutants()[0];
            for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
                mutantY.getGenes()[i] = mutant1.getGenes()[i] + Var.F1 * (mutantBest.getGenes()[i] - mutant1.getGenes()[1])
                        + Var.F2 * (mutant2.getGenes()[i] - mutant3.getGenes()[i]);
                if (Math.abs(mutantY.getGenes()[i]) > 5) {
                    outside = true;
                }

            }
        } while (outside == true);

        return mutantY;
    }

    public Mutant differentialCrossover(Mutant mutant1, Mutant mutant2, double p) {
        Mutant mutantZ = new Mutant(Var.NUMBER_OF_GENES);
        Random r = new Random();
        for (int i = 0; i < Var.NUMBER_OF_GENES; i++) {
            double x = r.nextDouble();
            if (x > p) {
                mutantZ.getGenes()[i] = mutant1.getGenes()[i];
            } else {
                mutantZ.getGenes()[i] = mutant2.getGenes()[i];
            }
        }
        return mutantZ;
    }

    public Population differentialSelection(Population populationZ, Population populationOld) {
        Population populationNew = new Population(Var.POPULATION_SIZE);

        double totalCR = 0;
        double totalF = 0;

        double weight = 0;
        int nrImproved = 0;

        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            if (populationOld.getMutants()[i].getFitness() > populationZ.getMutants()[i].getFitness()) {
                populationNew.getMutants()[i].setMutant(populationOld.getMutants()[i]);
            } else {
                populationNew.getMutants()[i].setMutant(populationZ.getMutants()[i]);
                double fitnessGain = (populationZ.getMutants()[i].getFitness() - populationOld.getMutants()[i].getFitness());
                totalCR += populationZ.getMutants()[i].getCR() * fitnessGain;
                totalF += populationZ.getMutants()[i].getF();
                nrImproved += 1;
                weight += (populationZ.getMutants()[i].getFitness() - populationOld.getMutants()[i].getFitness());
            }
        }

        double newCR;
        double newF;
        if (nrImproved > 0 && weight != 0) {
            newCR = totalCR / weight;
            newF = totalF / nrImproved;
        } else {
            newCR = populationOld.getMutants()[0].getCR();
            newF = populationOld.getMutants()[0].getF();
        }

        newCR = 0.8*populationOld.getMutants()[0].getCR() + 0.2*newCR;
        newF = 0.3*populationOld.getMutants()[0].getF() + 0.7*newF;

        populationNew.setCR(newCR);
        populationNew.setF(newF);

        return populationNew;
    }

    public Population differentialSelectionCR5(Population populationZ, Population populationOld) {
        Population populationNew = new Population(Var.POPULATION_SIZE);

        if (times == 0) {
            totalCR = 0;
            totalF = 0;
            weight = 0;
            nrImproved = 0;
        }

        times++;

        for (int i = 0; i < Var.POPULATION_SIZE; i++) {
            if (populationOld.getMutants()[i].getFitness() > populationZ.getMutants()[i].getFitness()) {
                populationNew.getMutants()[i].setMutant(populationOld.getMutants()[i]);
            } else {
                populationNew.getMutants()[i].setMutant(populationZ.getMutants()[i]);
                double fitnessGain = (populationZ.getMutants()[i].getFitness() - populationOld.getMutants()[i].getFitness());
                totalCR += populationZ.getMutants()[i].getCR();
                totalF += populationZ.getMutants()[i].getF();
                nrImproved += 1;
                weight += fitnessGain;
            }
        }

        double newCR;
        double newF;
        if (times == 5) {
            if (nrImproved > 0 && weight != 0) {
                newCR = totalCR / nrImproved;
                newF = totalF / nrImproved;
            } else {
                newCR = populationOld.getMutants()[0].getCR();
                newF = populationOld.getMutants()[0].getF();
            }
            times = 0;
        } else {
            newCR = populationOld.getMutants()[0].getCR();
            newF = populationOld.getMutants()[0].getF();
        }

        // newCR = 0.2*populationOld.getMutants()[0].getCR() + 0.8*newCR;
//        newF = 0.3*populationOld.getMutants()[0].getF() + 0.7*newF;

        populationNew.setCR(newCR);
        populationNew.setF(newF);

        return populationNew;
    }

    public double mutateCR(double oldCR) {
        Random r = new Random();
        double newCR;
        do {
            newCR = oldCR + Var.SIGMA_CR * r.nextGaussian();
        } while (newCR < 0 || newCR > 1);
        return newCR;
    }

    public double mutateF(double oldF) {
        Random r = new Random();
        double newF;
        do {
            newF = oldF + Var.SIGMA_F * r.nextGaussian();
        } while (newF < 0 || newF > 2);
        return newF;
    }

    public Population replaceShitty(Population population, int n) {
        population.sortByFitness();
        double CR = population.getMutants()[0].getCR();
        double F = population.getMutants()[0].getF();
        for (int i = 0; i<n; i++) {
            Mutant tempMutant = new Mutant(Var.NUMBER_OF_GENES);
            tempMutant.setCR(CR);
            tempMutant.setF(F);
            population.getMutants()[Var.POPULATION_SIZE-1-i].setMutant(tempMutant);
        }
        return population;
    }

    public Population replaceBest(Population population, int top) {

        if (top != 0) {
            population.sortByFitness();
            double CR = population.getMutants()[0].getCR();
            double F = population.getMutants()[0].getF();
            Random r = new Random();
            Mutant tempMutant = new Mutant(Var.NUMBER_OF_GENES);
            tempMutant.setCR(CR);
            tempMutant.setF(F);
            population.getMutants()[r.nextInt(top)].setMutant(tempMutant);
        }
        return population;
    }
}
