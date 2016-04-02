package efestoarts.gameoflife.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import efestoarts.gameoflife.model.Generation;

public class WorldView extends RelativeLayout {

    public static final int GRID_SIZE = 20;
    public Bitmap worldBitmap;
    private final Paint livingCellPaint;

    public WorldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        livingCellPaint = new Paint();
        livingCellPaint.setColor(Color.BLACK);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void setGeneration(Generation generation) {
        worldBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(worldBitmap);
        //TODO: iterate through generation

        drawLivingCell(canvas, 0, 0);
        invalidate();
    }

    private void drawLivingCell(Canvas canvas, int x, int y) {
        canvas.drawRect(x, y, getWidth()/ GRID_SIZE, getHeight()/GRID_SIZE, livingCellPaint);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (worldBitmap != null)
            canvas.drawBitmap(worldBitmap, 0, 0, null);
    }
}
