package efestoarts.gameoflife;

import android.app.Application;

import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class App extends Application{
    private Life life;
    private GameOfLifePresenter gameOfLifePresenter;

    public Life getLife()
    {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }

    public GameOfLifePresenter getGameOfLifePresenter()
    {
        if (gameOfLifePresenter == null)
            gameOfLifePresenter = new GameOfLifePresenter(this);

        return gameOfLifePresenter;
    }
}
