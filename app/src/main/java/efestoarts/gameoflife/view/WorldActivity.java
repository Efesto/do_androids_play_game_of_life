package efestoarts.gameoflife.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;

import efestoarts.gameoflife.App;
import efestoarts.gameoflife.R;
import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.presenter.GameOfLifePresenter;

public class WorldActivity extends AppCompatActivity {

    private WorldView world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        world = (WorldView)findViewById(R.id.world);

        final GameOfLifePresenter presenter = ((App) getApplication()).getGameOfLifePresenter();

        presenter.resume(this);
        world.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                presenter.onGlobalLayout();
            }
        });
    }

    public Generation getGeneration()
    {
        return world.getGeneration();
    }

    public void setGeneration(Generation generation)
    {
        world.setGeneration(generation);
    }
}
