package efestoarts.gameoflife;

import android.graphics.Color;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import org.hamcrest.Description;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import efestoarts.gameoflife.view.MainActivity;
import efestoarts.gameoflife.view.WorldView;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@LargeTest
public class WorldViewTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

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
        mActivityRule.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mActivityRule.getActivity().nextGeneration();
            }
        });

        onView(withId(R.id.world)).check(matches(new CellMatcher(0, 0, true)));
        onView(withId(R.id.world)).check(matches(new CellMatcher(50, 50, false)));
    }
}

class CellMatcher implements org.hamcrest.Matcher<View> {

    private int x;
    private final int y;
    private final boolean isAlive;

    public CellMatcher(int x, int y, boolean isAlive) {
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    @Override
    public boolean matches(Object item) {
        WorldView worldView = (WorldView)item;

        return worldView.worldBitmap.getPixel(x, y) == (isAlive ?
                Color.BLACK : Color.TRANSPARENT);
    }

    @Override
    public void describeMismatch(Object item, Description mismatchDescription) {
        mismatchDescription.appendText("Color is " + ((WorldView)item).worldBitmap.getPixel(x, y));
    }

    @Override
    public void _dont_implement_Matcher___instead_extend_BaseMatcher_() {

    }

    @Override
    public void describeTo(Description description) {
    }
}