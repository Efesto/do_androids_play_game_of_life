package efestoarts.gameoflife;

import android.app.Application;

import efestoarts.gameoflife.model.Life;

public class App extends Application{
    private Life life;

    public Life getLife()
    {
        return life;
    }

    public void setLife(Life life) {
        this.life = life;
    }
}
