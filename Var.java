
public class Var {



    public static int POPULATION_SIZE;
    public static double F;
    public static double CR;


    public static final double F1 = 0.04;
    public static final double F2 = 0.32;



    public Var() {
        POPULATION_SIZE = 100;
        F = 0.5;
        CR = 0.5;
//        SEARCH_SPACE_MIN = -5.0;
//        SEARCH_SPACE_MAX = 5.0;
        SEARCH_SPACE_MIN = -100;
        SEARCH_SPACE_MAX = 100 ;
    }

    public void setPopulationSize (int populationSize) {
        this.POPULATION_SIZE = populationSize;
    }

    public void setF (double F) {
        this.F = F;
    }

    public void setCR (double CR) {
        this.CR = CR;
    }

    public void setSEARCH_SPACE_MIN (double SEARCH_SPACE_MIN) {
        this.SEARCH_SPACE_MIN = SEARCH_SPACE_MIN;
    }

    public void setSEARCH_SPACE_MAX (double SEARCH_SPACE_MAX) {
        this.SEARCH_SPACE_MAX = SEARCH_SPACE_MAX;
    }

    public static final double SIGMA_CR = 0.1;
    public static final double SIGMA_F = 0.1;
    public static final int KILL = 0;

    public static final int KILL_BEST = 0;




    // Assignment specifics
    public static final int NUMBER_OF_GENES = 10;
    public static double SEARCH_SPACE_MIN;
    public static double SEARCH_SPACE_MAX;




    public static final double MUTATION_RAE = 0.25;

    public static final double TAU_1 = 1 / Math.sqrt(2 * NUMBER_OF_GENES);
    public static final double TAU_2 = 1 / Math.sqrt(2 * Math.sqrt(NUMBER_OF_GENES));
    public static final double BOUNDRY = 0.0001;
    public static final double BETA = (5/360)*Math.PI;





}