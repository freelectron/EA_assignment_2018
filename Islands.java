import java.util.ArrayList;

public class Islands {
    /*
    initializing
     */
    int n_islands;
    //private Population[] populations;
    private ArrayList populations = new ArrayList();
    private EvolutionAlgorithm[] evolutionAlgorithms;

    /*
    constructor
     */
    public Islands(int n_islands){
        this.n_islands = n_islands;
        evolutionAlgorithms = new EvolutionAlgorithm[n_islands];
        for (int i = 0; i < n_islands; i++){
            populations.add(new Population(Var.POPULATION_SIZE));
            evolutionAlgorithms[i] = new EvolutionAlgorithm(populations.get(i));
        }
    }

    public void evolve(){
        for (int i = 0; i < n_islands; i++){
            evolutionAlgorithms[i].evolve();
        }
    }

    public void removeIsland(int islandNr){
        populations.remove();
    }

    public void addIsland(){

    }

    public Population[] getPopulations(){
        return populations;
    }

}
