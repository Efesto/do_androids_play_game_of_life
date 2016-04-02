package efestoarts.gameoflife;

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
import efestoarts.gameoflife.view.WorldActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WorldViewTest {

    @Rule
    public ActivityTestRule<WorldActivity> mActivityRule = new ActivityTestRule<>(
            WorldActivity.class);

    @Mock
    Life mockedLife;

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        getApp().setLife(mockedLife);
    }

    @Test
    public void world_view_is_square() {
        onView(withId(R.id.world)).check(matches(new org.hamcrest.Matcher<View>() {
            @Override
            public boolean matches(Object item) {
                return ((View) item).getHeight() == ((View) item).getWidth();
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
    public void setNewStartingGeneration() {
        Generation gen = new Generation(2);
        gen.cells = new boolean[][] {
                {false, false},
                {false, true}};

        setNextGeneration(gen);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getApp().getGameOfLifePresenter().nextGeneration();
            }
        });

        onView(withId(R.id.world)).perform(EspressoHelpers.clickXY(1, 1));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, true)));

        getApp().getGameOfLifePresenter().setNewStartingGeneration();
        Generation expectedGeneration = new Generation(2);
        expectedGeneration.cells = new boolean[][]{
                {true, false},
                {false, true}};
        verify(mockedLife).setGeneration(expectedGeneration);
    }

    @Test
    public void setGeneration()
    {
        Generation gen = new Generation(2);
        gen.cells = new boolean[][] {
                {false, false},
                {false, true}};

        setNextGeneration(gen);

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getApp().getGameOfLifePresenter().nextGeneration();
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

    private App getApp() {
        return (App) InstrumentationRegistry.getTargetContext().getApplicationContext();
    }

    private WorldActivity getActivity() {
        return mActivityRule.getActivity();
    }
}
