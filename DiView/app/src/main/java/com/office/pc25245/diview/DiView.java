package com.office.pc25245.diview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by pc252 on 2019/4/15.
 */

public class DiView extends View {
    private Paint paint;
    private Paint mPaint;
    private int ScreenWidth = 0;
    private int ScreenHeight = 0;
    private float smallCircle = 22;
    private float bigCircle = 40;
    private int distance = 40;
    private float francation = 0.0f;
    private float leftX = 0, leftY = 0;
    private float leftX1 = 0, leftY1 = 0;
    private float rightX = 0, rightY = 0;
    private float rightX1 = 0, rightY1 = 0;
    private float controlLeftX = 0, controlLeftY = 0;
    private float controlRightX = 0, controlRightY = 0;
    private float CirleVaberate = 20;
    private DrawFilter drawFilter;

    public DiView(Context context) {
        super(context);
        init();
    }

    public DiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DiView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.parseColor("#FF0000"));
        drawFilter = new PaintFlagsDrawFilter(Paint.ANTI_ALIAS_FLAG, Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(drawFilter);
        canvas.translate(ScreenWidth / 2, ScreenHeight / 2);
        float bigCircle = this.bigCircle - francation * distance / CirleVaberate;
        float smallCircle = 0;
        if (francation * distance > 5) {
            smallCircle = this.smallCircle - francation * distance / CirleVaberate;
        }
        Path path = new Path();
        leftX = -bigCircle;
        leftX1 = -smallCircle;
        leftY1 = francation * distance;
        rightX = bigCircle;
        rightX1 = smallCircle;
        rightY1 = francation * distance;
        controlLeftX = -smallCircle;
        controlRightX = smallCircle;
        controlLeftY = controlRightY = francation * distance / 2;
        path.moveTo(leftX, leftY);
        path.quadTo(controlLeftX, controlLeftY, leftX1, leftY1);
        path.lineTo(rightX1, rightY1);
        path.quadTo(controlRightX, controlRightY, rightX, rightY);
        canvas.drawPath(path, paint);
        canvas.drawCircle(0, 0, bigCircle, paint);
        canvas.drawCircle(0, francation * distance, smallCircle, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                float TranslateXer = Math.abs(event.getX() - ScreenWidth / 2);
                float TranslateYer = Math.abs(event.getY() - ScreenHeight / 2);
                francation = (float) Math.sqrt(Math.pow(TranslateXer, 2) + Math.pow(TranslateYer, 2)) / distance;
                postInvalidate();
                break;
            case MotionEvent.ACTION_UP:
                francation = 0;
                postInvalidate();
                break;
        }
        return /*super.onTouchEvent(event)*/ true;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        ScreenWidth = w;
        ScreenHeight = h;
    }
}
