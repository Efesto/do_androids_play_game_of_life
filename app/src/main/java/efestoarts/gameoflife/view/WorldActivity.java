package efestoarts.gameoflife.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import efestoarts.gameoflife.App;
import efestoarts.gameoflife.R;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class WorldActivity extends AppCompatActivity {

    private WorldView world;
    private Button startButton;
    boolean buttonMeanStart = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_world);

        world = (WorldView) findViewById(R.id.world);
        startButton = (Button) findViewById(R.id.start_button);

        final GameOfLifePresenter presenter = ((App) getApplication()).getGameOfLifePresenter();

        presenter.resume(this);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onStartStopButtonClick();

                if (buttonMeanStart)
                    startButton.setText(R.string.button_stop_label);
                else
                    startButton.setText(R.string.button_start_label);

                buttonMeanStart = !buttonMeanStart;
            }
        });

        world.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                presenter.onGlobalLayout();
            }
        });
    }

    public Generation getGeneration() {
        return world.getGeneration();
    }

    public void setGeneration(Generation generation) {
        world.setGeneration(generation);
        world.refreshView();
    }
}
