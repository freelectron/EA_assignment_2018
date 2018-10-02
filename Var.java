
public class Var {

    // Assignment specifics
    public static final int NUMBER_OF_GENES = 10;
    public static final int SEARCH_SPACE_MIN = -5;
    public static final int SEARCH_SPACE_MAX = 5;


    public static final int POPULATION_SIZE = 10;


    public static final double MUTATION_RATE = 0.25;

    public static final double TAU_1 = 1 / Math.sqrt(2 * NUMBER_OF_GENES);
    public static final double TAU_2 = 1 / Math.sqrt(2 * Math.sqrt(NUMBER_OF_GENES));
    public static final double BOUNDRY = 0.1;
    public static final double BETA = (5/360)*Math.PI;

    public static final double TAU_MOMENTUM_MU = 1/4;
    public static final double TAU_MOMENTUM_SIGMA = 1/2;




}