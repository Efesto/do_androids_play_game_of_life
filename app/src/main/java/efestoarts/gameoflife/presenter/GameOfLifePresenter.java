package efestoarts.gameoflife.presenter;

import efestoarts.gameoflife.App;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.view.WorldActivity;

public class GameOfLifePresenter {

    private WorldActivity activity;
    private App application;

    public GameOfLifePresenter(App application) {
        this.application = application;
    }

    public void resume(WorldActivity activity) {
        this.activity = activity;
    }

    public void onGlobalLayout()
    {
        activity.setGeneration(new Generation(20));
    }

    public void nextGeneration() {
        activity.setGeneration(getLife().nextGeneration());
    }

    public void setNewStartingGeneration() {
        getLife().setStartingGeneration(activity.getGeneration());
    }

    private Life getLife() {
        return application.getLife();
    }
}
