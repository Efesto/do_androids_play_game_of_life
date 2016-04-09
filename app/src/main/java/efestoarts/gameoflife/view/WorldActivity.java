package efestoarts.gameoflife.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import javax.inject.Inject;

import efestoarts.gameoflife.App;
import efestoarts.gameoflife.R;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class WorldActivity extends AppCompatActivity {

    private WorldView world;
    private Button startButton;
    boolean buttonClickStartsSimulation = true;
    @Inject public GameOfLifePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);
        ((App) getApplication()).getAppComponent().inject(this);

        world = (WorldView) findViewById(R.id.world);
        startButton = (Button) findViewById(R.id.start_button);

        presenter.resume(this);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.startStopSimulation();

                if (buttonClickStartsSimulation)
                    startButton.setText(R.string.button_stop_label);
                else
                    startButton.setText(R.string.button_start_label);

                buttonClickStartsSimulation = !buttonClickStartsSimulation;
            }
        });

        world.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                presenter.onGlobalLayout();
            }
        });
    }

    public void setGeneration(Generation generation) {
        world.setGeneration(generation);
        world.refreshView();
    }
}
