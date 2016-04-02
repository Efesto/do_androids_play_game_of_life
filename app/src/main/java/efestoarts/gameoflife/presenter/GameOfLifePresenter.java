package efestoarts.gameoflife.presenter;

import android.os.AsyncTask;

import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.view.WorldActivity;

public class GameOfLifePresenter {

    private WorldActivity activity;
    private Life life;
    private boolean keepRunning;

    public GameOfLifePresenter(Life life) {
        this.life = life;
    }

    public void resume(WorldActivity activity) {
        this.activity = activity;
    }

    public void showNextGenerationOnView() {
        activity.setGeneration(life.nextGeneration());
    }

    public void onStartStopButtonClick() {
        if (keepRunning) {
            keepRunning = false;
        } else {
            keepRunning = true;
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    while (keepRunning) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                showNextGenerationOnView();
                            }
                        });
                    }
                    return null;
                }
            }.execute();
        }
    }

    public void onGlobalLayout() {
        showNextGenerationOnView();
    }
}
