package efestoarts.gameoflife;

import android.graphics.Color;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import efestoarts.gameoflife.model.Generation;
import efestoarts.gameoflife.model.Life;
import efestoarts.gameoflife.view.MainActivity;
import efestoarts.gameoflife.view.WorldView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WorldViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Mock
    Life mockedLife;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        App application = (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
        application.setLife(mockedLife);
    }

    @Test
    public void world_view_is_square() {
        onView(withId(R.id.world)).check(matches(new org.hamcrest.Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return ((View)item).getHeight() == ((View)item).getWidth();
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {

            }

            @Override
            public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

            }

            @Override
            public void describeTo(Description description) {

            }
        }));
    }

    @Test
    public void next_generation()
    {

        setNextGeneration(new Generation(2, new boolean[][] {
                {false, false},
                {false, true}}));

        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().nextGeneration();
            }
        });

        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 1, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 1, 1, true)));
    }

    private void setNextGeneration(Generation testGeneration) {
        when(mockedLife.nextGeneration()).thenReturn(testGeneration);
    }
}

class CellMatcher implements org.hamcrest.Matcher<View> {

    private int size;
    private int x;
    private final int y;
    private final boolean isAlive;

    public CellMatcher(int size, int x, int y, boolean isAlive) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    @Override
    public boolean matches(Object item) {
        WorldView worldView = (WorldView)item;

        int cellSize = worldView.getWidth() / size;
        int cellCenterX = (cellSize * x) + (cellSize / 2);
        int cellCenterY = (cellSize * y) + (cellSize / 2);

        return worldView.worldBitmap.getPixel(cellCenterX, cellCenterY) == (isAlive ?
                Color.BLACK : Color.TRANSPARENT);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {

    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {
    }
}