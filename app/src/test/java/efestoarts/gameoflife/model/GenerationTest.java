package efestoarts.gameoflife.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class GenerationTest {
    @Test
    public void equals()
    {
        Generation first = new Generation(4);
        Generation second = new Generation(4);
        assertEquals(first, second);
    }

    @Test
    public void not_equals()
    {
        Generation first = new Generation(4);
        first.cells[0][0] = true;
        Generation second = new Generation(4);

        assertNotEquals(first, second);

        first = new Generation(4);
        second = new Generation(84);


        assertNotEquals(first, second);

    }
}