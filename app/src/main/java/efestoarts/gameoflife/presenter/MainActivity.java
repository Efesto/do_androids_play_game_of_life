package efestoarts.gameoflife.presenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import efestoarts.gameoflife.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setText(String text)
    {
        ((TextView)findViewById(R.id.test_text)).setText(text);
    }
}
