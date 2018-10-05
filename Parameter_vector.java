import java.util.Arrays;
import java.util.Random;

public class Parameter_vector{  
    String selector;
    String recombinator;
    String mutator;
    int population_size;
    double mutation_size;
    // performance score of the parameter vector
    double score = 0.0; 
    // Constructor:
    Parameter_vector(String selector, String recombinator, String mutator, int population_size, double mutation_size){  
        this.selector=selector;  
        this.recombinator=recombinator;  
        this.mutator=mutator;
        this.population_size=population_size;
        this.mutation_size=mutation_size;
        this.score = score;
    }
    public void setVariables() {
    	Var.setSELECTOR(this.selector);
		Var.setRECOMBINATOR(this.recombinator);
		Var.setMUTATOR(this.mutator);
		Var.setPOPULATION_SIZE(this.population_size);
		Var.setMUTATION_RATE(this.mutation_size);

    } 
} 
