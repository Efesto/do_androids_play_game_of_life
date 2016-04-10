package efestoarts.gameoflife;

import org.junit.Test;

import efestoarts.gameoflife.model.Life;

import static org.junit.Assert.assertEquals;

public class AppModuleTest {

    @Test
    public void testProvidesLife() throws Exception {
        Life life = new AppModule().providesLife();
        assertEquals(20, life.currentGeneration.getSize());
    }
}