package efestoarts.gameoflife;

import android.app.Application;

import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class App extends Application{
    private static final int DEFAULT_GRID_SIZE = 20;
    private GameOfLifePresenter gameOfLifePresenter;

    public GameOfLifePresenter getGameOfLifePresenter()
    {
        if (gameOfLifePresenter == null)
            gameOfLifePresenter = new GameOfLifePresenter(new Life(new Generation(DEFAULT_GRID_SIZE)));

        return gameOfLifePresenter;
    }

    public void setGameOfLifePresenter(GameOfLifePresenter gameOfLifePresenter) {
        this.gameOfLifePresenter = gameOfLifePresenter;
    }
}
