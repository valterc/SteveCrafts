package com.vcutils.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

import com.vcutils.R;

public class RepeatingImageView extends View {

    private float drawablePadding = 0;
    private Drawable drawable;
    private float drawableCount = 1;

    public RepeatingImageView(Context context) {
        super(context);
        init(null, 0);
    }

    public RepeatingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public RepeatingImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.RepeatingImageView, defStyle, 0);

        // Use getDimensionPixelSize or getDimensionPixelOffset when dealing with
        // values that should fall on pixel boundaries.
        drawablePadding = a.getDimension(R.styleable.RepeatingImageView_drawablePadding, drawablePadding);
        drawableCount = a.getFloat(R.styleable.RepeatingImageView_drawableCount, drawableCount);

        if (a.hasValue(R.styleable.RepeatingImageView_drawable)) {
            drawable = a.getDrawable(R.styleable.RepeatingImageView_drawable);
            if (drawable != null) {
                drawable.setCallback(this);
            }
        }

        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int count = (int) (drawableCount + .5);
        setMeasuredDimension((int) (count * (drawablePadding + (drawable != null ? drawable.getIntrinsicWidth() : 1))), View.MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int dCount = (int) (drawableCount);

        if (drawable != null) {
            for (int i = 0; i < dCount; i++) {
                drawable.setBounds((int) (drawable.getIntrinsicWidth() + drawablePadding) * i, 0, (int) (drawable.getIntrinsicWidth() + drawablePadding) * i + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.draw(canvas);
            }

            if (dCount < (int) (drawableCount + .5)) {

                drawable.setColorFilter(Color.BLACK, PorterDuff.Mode.MULTIPLY);
                drawable.setBounds((int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount, 0, (int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.draw(canvas);
                drawable.setColorFilter(null);

                canvas.clipRect((int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount, 0, (int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount + drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight());
                drawable.setBounds((int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount, 0, (int) (drawable.getIntrinsicWidth() + drawablePadding) * dCount + drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                drawable.draw(canvas);
            }
        }
    }


    public float getDrawablePadding() {
        return drawablePadding;
    }

    public void setDrawablePadding(float drawablePadding) {
        this.drawablePadding = drawablePadding;
    }

    public Drawable getDrawable() {
        return drawable;
    }

    public void setDrawable(Drawable drawable) {
        this.drawable = drawable;
    }

    public float getDrawableCount() {
        return drawableCount;
    }

    public void setDrawableCount(float drawableCount) {
        this.drawableCount = Math.max(drawableCount, 1);
    }

}
