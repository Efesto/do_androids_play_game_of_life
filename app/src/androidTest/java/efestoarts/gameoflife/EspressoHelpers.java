package efestoarts.gameoflife;

import android.graphics.Color;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.CoordinatesProvider;
import android.support.test.espresso.action.GeneralClickAction;
import android.support.test.espresso.action.Press;
import android.support.test.espresso.action.Tap;
import android.view.View;

import org.hamcrest.Description;

import efestoarts.gameoflife.view.WorldView;

public class EspressoHelpers {

    public static ViewAction clickXY(final int x, final int y) {
        return new GeneralClickAction(
                Tap.SINGLE,
                new CoordinatesProvider() {
                    @Override
                    public float[] calculateCoordinates(View view) {

                        final int[] screenPos = new int[2];
                        view.getLocationOnScreen(screenPos);

                        final float screenX = screenPos[0] + x;
                        final float screenY = screenPos[1] + y;

                        return new float[]{screenX, screenY};
                    }
                },
                Press.FINGER);
    }


}

class CellMatcher implements org.hamcrest.Matcher<View> {

    private int gridSize;
    private int x;
    private final int y;
    private final boolean isAlive;

    public CellMatcher(int gridSize, int x, int y, boolean isAlive) {
        this.gridSize = gridSize;
        this.x = x;
        this.y = y;
        this.isAlive = isAlive;
    }

    @Override
    public boolean matches(Object item) {
        WorldView worldView = (WorldView) item;

        int cellSize = worldView.getWidth() / gridSize;
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
        if (isAlive)
            description.appendText(String.format("Cell at %s, %s is not alive", x, y));
        else
            description.appendText(String.format("Cell at %s, %s  is not dead", x, y));
    }
}
