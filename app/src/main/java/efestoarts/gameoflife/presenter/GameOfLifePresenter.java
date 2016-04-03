package efestoarts.gameoflife.presenter;

import android.os.AsyncTask;

import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.view.WorldActivity;

public class GameOfLifePresenter {

    public static final int SIMULATION_REFRESH_TIME = 100;
    private WorldActivity activity;
    private Life life;
    private boolean runSimulation;

    public GameOfLifePresenter(Life life) {
        this.life = life;
    }

    public void resume(WorldActivity activity) {
        this.activity = activity;
    }

    public void showNextGenerationOnView() {
        activity.setGeneration(life.nextGeneration());
    }

    public void startStopSimulation() {
        if (runSimulation) {
            runSimulation = false;
        } else {
            runSimulation = true;
            new AsyncTask<Void, Void, Void>() {
                @Override
                protected Void doInBackground(Void... params) {
                    while (runSimulation) {
                        try {
                            Thread.sleep(SIMULATION_REFRESH_TIME);
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
