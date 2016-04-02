package efestoarts.gameoflife.model;

public class Life {

    private Generation generation;

    public Generation nextGeneration()
    {
        //TODO: calcoli su generation;
        return generation;
    }

    public void setGeneration(Generation generation) {

        this.generation = generation;
    }
}
