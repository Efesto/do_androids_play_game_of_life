package efestoarts.gameoflife.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import efestoarts.gameoflife.R;
import efestoarts.gameoflife.model.Generation;

public class MainActivity extends AppCompatActivity {

    private WorldView world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        world = (WorldView)findViewById(R.id.world);
    }

    public void nextGeneration() {
        world.setGeneration(new Generation());
    }
}
