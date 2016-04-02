package efestoarts.gameoflife.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import efestoarts.gameoflife.model.Generation;

public class WorldView extends RelativeLayout {

    public Bitmap worldBitmap;
    private final Paint livingCellPaint;
    private Generation generation;

    public WorldView(Context context, AttributeSet attrs) {
        super(context, attrs);
        livingCellPaint = new Paint();
        livingCellPaint.setColor(Color.BLACK);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    int cellIndexX = (int) event.getX() / getCellSize();
                    int cellIndexY = (int) event.getY() / getCellSize();

                    generation.cells[cellIndexY][cellIndexX] = !generation.cells[cellIndexY][cellIndexX];
                    refreshView();
                }

                return true;
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    public void refreshView() {
        worldBitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(worldBitmap);

        for(int yIndex = 0; yIndex < generation.getSize(); yIndex++) {
            for(int xIndex = 0; xIndex < generation.getSize(); xIndex++) {
                if(generation.cells[yIndex][xIndex])
                    drawLivingCell(canvas, xIndex, yIndex);
            }

        }

        invalidate();
    }

    private void drawLivingCell(Canvas canvas, int x, int y) {
        int cellSize = getCellSize();
        canvas.drawRect(
                x * cellSize,
                y * cellSize,
                cellSize + x * cellSize,
                cellSize + y * cellSize, livingCellPaint);
    }

    private int getCellSize() {
        return getWidth()/ generation.getSize();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (worldBitmap != null)
            canvas.drawBitmap(worldBitmap, 0, 0, null);
    }

    public Generation getGeneration() {
        return generation;
    }

    public void setGeneration(Generation generation) {
        this.generation = generation;
    }

}
