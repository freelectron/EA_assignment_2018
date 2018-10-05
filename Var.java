
public class Var {



    public Var() {
        BOUNDRY = 0.1;
    }

    public void update(int iterations, double fitness) {
        if (iterations > 1000 && iterations < 2000) {
            BOUNDRY = 0.01;
        } else if (iterations > 2000 && iterations < 5000) {
            BOUNDRY = 0.001;
        } else if (iterations > 5000 && iterations < 10000) {
            BOUNDRY = 0.0001;
        }
    }

    // Assignment specifics
    public static final int NUMBER_OF_GENES = 10;
    public static final int SEARCH_SPACE_MIN = -5;
    public static final int SEARCH_SPACE_MAX = 5;


    public static final int POPULATION_SIZE = 20;


    public static final double MUTATION_RATE = 0.25;

    public static final double TAU_1 = 1 / Math.sqrt(2 * NUMBER_OF_GENES);
    public static final double TAU_2 = 1 / Math.sqrt(2 * Math.sqrt(NUMBER_OF_GENES));
    public static double BOUNDRY;
    public static final double BETA = (5/360)*Math.PI;

    public static final double TAU_MOMENTUM_MU = 0.5;
    public static final double TAU_MOMENTUM_SIGMA = 0.5;

    public static final double CRAZY_ADD = 0;





}