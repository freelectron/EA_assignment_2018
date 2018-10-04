
public class Var {

    // Assignment specifics
    public static final int NUMBER_OF_GENES = 10;
    public static final int SEARCH_SPACE_MIN = -5;
    public static final int SEARCH_SPACE_MAX = 5;


    public static final double TAU_1 = 1 / Math.sqrt(2 * NUMBER_OF_GENES);
    public static final double TAU_2 = 1 / Math.sqrt(2 * Math.sqrt(NUMBER_OF_GENES));
    public static final double BOUNDRY = 0.1;
    public static final double BETA = (5/360)*Math.PI;

    public static final double TAU_MOMENTUM_MU = 1/4;
    public static final double TAU_MOMENTUM_SIGMA = 1/2;



    public static int POPULATION_SIZE = 10;
    public static double MUTATION_RATE = 0.01;
    public static String SELECTOR = "parentSelection_nr1only";
    public static String RECOMBINATOR = "crossoverPopulation_nPoint";
    public static String MUTATOR = "mutatePopulation_CMN";

    public static void setPOPULATION_SIZE (int p) {
        POPULATION_SIZE = p;
    }
    
    public static void setMUTATION_RATE (double r) {
        MUTATION_RATE = r;
    }

    public static void setSELECTOR (String s) {
        SELECTOR = s;
    }

    public static void setRECOMBINATOR (String s) {
        RECOMBINATOR = s;
    }

    public static void setMUTATOR (String s) {
        MUTATOR = s;
    }




}