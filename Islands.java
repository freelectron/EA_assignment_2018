public class Islands {
    /*
    initializing
     */
    int n_islands;
    private Population[] populations;
    private EvolutionAlgorithm[] evolutionAlgorithms;

    /*
    constructor
     */
    public Islands(int n_islands){
        this.n_islands = n_islands;
        populations = new Population[n_islands];
        evolutionAlgorithms = new EvolutionAlgorithm[n_islands];
        for (int i = 0; i < n_islands; i++){
            populations[i] = new Population(Var.POPULATION_SIZE);
            evolutionAlgorithms[i] = new EvolutionAlgorithm(populations[i]);
        }
    }

    public void evolve(){
        for (int i = 0; i < n_islands; i++){
            evolutionAlgorithms[i].evolve();
        }
    }

    public Population[] getPopulations(){
        return populations;
    }

}
