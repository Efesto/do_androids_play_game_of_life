package efestoarts.gameoflife;

import android.app.Application;

import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class App extends Application{
    private Life life;
    private GameOfLifePresenter gameOfLifePresenter;

    private Life getLife()
    {
        if(life == null)
        {
            life = new Life();
        }

        return life;
    }

    public GameOfLifePresenter getGameOfLifePresenter()
    {
        if (gameOfLifePresenter == null)
            gameOfLifePresenter = new GameOfLifePresenter(getLife());

        return gameOfLifePresenter;
    }

    public void setGameOfLifePresenter(GameOfLifePresenter gameOfLifePresenter) {
        this.gameOfLifePresenter = gameOfLifePresenter;
    }
}
