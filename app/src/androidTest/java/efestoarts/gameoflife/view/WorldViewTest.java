package efestoarts.gameoflife.view;

import android.content.Intent;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import efestoarts.gameoflife.CellMatcher;
import efestoarts.gameoflife.DaggerMockedAppComponent;
import efestoarts.gameoflife.EspressoHelpers;
import efestoarts.gameoflife.R;
import efestoarts.gameoflife.model.Generation;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WorldViewTest {

    @Rule
    public ActivityTestRule<WorldActivity> mActivityRule = new ActivityTestRule<>(
            WorldActivity.class, false, false);

    @Before
    public void setup()
    {
        mActivityRule.launchActivity(new Intent());
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
    public void start_stop_button() {
        DaggerMockedAppComponent.create().inject(getActivity());

        onView(withId(R.id.start_button)).check(matches(withText("Start!")));
        onView(withId(R.id.start_button)).perform(ViewActions.click());
        onView(withId(R.id.start_button)).check(matches(withText("Stop!")));
        onView(withId(R.id.start_button)).perform(ViewActions.click());
        onView(withId(R.id.start_button)).check(matches(withText("Start!")));

        verify(getActivity().presenter, times(2)).startStopSimulation();
    }

    @Test
    public void edit_generation_with_touch() {
        final Generation gen = new Generation(2);
        gen.cells = new boolean[][] {
                {false, false},
                {false, true}};

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().setGeneration(gen);
            }
        });

        onView(withId(R.id.world)).perform(EspressoHelpers.clickXY(1, 1));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, true)));

        Generation expectedGeneration = new Generation(2);
        expectedGeneration.cells = new boolean[][]{
                {true, false},
                {false, true}};
        assertEquals(expectedGeneration, gen);
    }

    @Test
    public void setGeneration()
    {
        final Generation gen = new Generation(2);
        gen.cells = new boolean[][] {
                {false, false},
                {false, true}};

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getActivity().setGeneration(gen);
            }
        });

        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 0, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 1, 0, false)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(2, 1, 1, true)));
    }
    private WorldActivity getActivity() {
        return mActivityRule.getActivity();
    }
}
