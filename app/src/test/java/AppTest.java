import org.junit.Test;

import efestoarts.gameoflife.App;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

import static org.junit.Assert.assertSame;

public class AppTest {
    @Test
    public void getGameOfLifePresenter_singleton() {
        App app = new App();

        GameOfLifePresenter presenter = app.getGameOfLifePresenter();
        GameOfLifePresenter anotherPresenter = app.getGameOfLifePresenter();

        assertSame(presenter, anotherPresenter);
    }
}
