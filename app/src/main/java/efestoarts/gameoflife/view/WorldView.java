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

        for(int yIndex = 0; yIndex < generation.getSize(); yIndex++) {
            for(int xIndex = 0; xIndex < generation.getSize(); xIndex++) {
                if(generation.getCells()[yIndex][xIndex])
                    drawLivingCell(canvas, xIndex, yIndex, generation.getSize());
            }

        }

        invalidate();
    }

    private void drawLivingCell(Canvas canvas, int x, int y, int gridSize) {
        int cellSize = getWidth()/ gridSize;
        canvas.drawRect(
                x * cellSize,
                y * cellSize,
                cellSize + x * cellSize,
                cellSize + y * cellSize, livingCellPaint);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (worldBitmap != null)
            canvas.drawBitmap(worldBitmap, 0, 0, null);
    }
}
