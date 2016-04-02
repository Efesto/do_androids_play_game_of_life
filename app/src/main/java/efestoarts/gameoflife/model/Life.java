package efestoarts.gameoflife.model;

import java.util.ArrayList;

public class Life {
    public Generation nextGeneration()
    {
        //TODO: grid size constant to extract
        return new Generation(20, new boolean[][]{});
    }
}
