package efestoarts.gameoflife.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LifeTest {


//    Any live cell with fewer than two live neighbours dies, as if caused by under-population.
//    Any live cell with two or three live neighbours lives on to the next generation.
//    Any live cell with more than three live neighbours dies, as if by over-population.
//    Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

    @Test
    public void death_by_under_population() {
        Generation generation = new Generation(3);
        //cell with one neighbour
        generation.cells[0][0] = true;
        generation.cells[0][1] = true;
        //cell alone
        generation.cells[2][2] = true;

        Generation expectedNextGeneration = new Generation(3);
        expectedNextGeneration.cells[1][1] = true;

        assertNextGenerationIs(generation, expectedNextGeneration);
    }

    @Test
    public void death_by_over_population() {
        Generation generation = new Generation(3);
        //cell with one neighbour
        generation.cells[0][0] = true;
        generation.cells[0][1] = true;
        generation.cells[0][2] = true;
        generation.cells[1][1] = true;
        generation.cells[1][0] = true;
        generation.cells[2][0] = true;
        generation.cells[2][2] = true;

        Generation expectedNextGeneration = new Generation(3);
        expectedNextGeneration.cells[0][0] = true;
        expectedNextGeneration.cells[0][2] = true;
        expectedNextGeneration.cells[2][0] = true;

        assertNextGenerationIs(generation, expectedNextGeneration);
    }

    @Test
    public void life_by_reproduction() {
        Generation generation = new Generation(3);
        //cell with one neighbour
        generation.cells[0][1] = true;
        generation.cells[1][1] = true;
        generation.cells[1][0] = true;

        Generation expectedNextGeneration = new Generation(3);
        expectedNextGeneration.cells[0][0] = true;
        expectedNextGeneration.cells[0][1] = true;
        expectedNextGeneration.cells[1][0] = true;
        expectedNextGeneration.cells[1][1] = true;

        assertNextGenerationIs(generation, expectedNextGeneration);
    }

    @Test
    public void survival() {
        Generation generation = new Generation(3);
        generation.cells[0][0] = true;
        generation.cells[0][1] = true;
        generation.cells[0][2] = true;

        Generation expectedGeneration = new Generation(3);
        expectedGeneration.cells[0][1] = true;
        expectedGeneration.cells[1][1] = true;

        assertNextGenerationIs(generation, expectedGeneration);
    }


    private void assertNextGenerationIs(Generation actualGeneration, Generation nextGeneration) {
        Life life = new Life(actualGeneration);

        Generation actualNextGeneration = life.nextGeneration();

        assertEquals(
                "Expected:\n" + stringFormat(nextGeneration) + "\nbut was\n" + stringFormat( actualNextGeneration),
                nextGeneration,  actualNextGeneration);
    }

    private String stringFormat(Generation generation) {
        String output = "";
        for (int indexY = 0; indexY < generation.getSize(); indexY++) {
            for (int indexX = 0; indexX < generation.getSize(); indexX++) {
                output = output + (generation.cells[indexY][indexX] ? "*" : "-");
            }
            output += "\n";
        }
        return output;
    }
}
