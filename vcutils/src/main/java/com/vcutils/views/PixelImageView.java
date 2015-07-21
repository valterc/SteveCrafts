package com.vcutils.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Created by Valter on 23/05/2014.
 */
public class PixelImageView extends ImageView {

    private int realWidth;
    private int realHeight;
    private int lastImageResource;
    private Bitmap lastImageBitmap;
    private boolean useFilter;

    public PixelImageView(Context context) {
        super(context);
        lastImageResource = -1;

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewTreeObserver vto = getViewTreeObserver();
                vto.removeOnPreDrawListener(this);
                realWidth = getMeasuredHeight();
                realHeight = getMeasuredWidth();

                if (lastImageResource != -1) {
                    setImageResource(lastImageResource);
                }

                return true;
            }
        });
    }

    public PixelImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        lastImageResource = -1;

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewTreeObserver vto = getViewTreeObserver();
                vto.removeOnPreDrawListener(this);
                realWidth = getMeasuredHeight();
                realHeight = getMeasuredWidth();

                if (lastImageResource != -1) {
                    setImageResource(lastImageResource);
                } else if (lastImageBitmap != null) {
                    setImageBitmap(lastImageBitmap);
                }

                return true;
            }
        });
    }

    public PixelImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        lastImageResource = -1;

        ViewTreeObserver vto = getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                ViewTreeObserver vto = getViewTreeObserver();
                vto.removeOnPreDrawListener(this);
                realWidth = getMeasuredHeight();
                realHeight = getMeasuredWidth();

                if (lastImageResource != -1) {
                    setImageResource(lastImageResource);
                } else if (lastImageBitmap != null) {
                    setImageBitmap(lastImageBitmap);
                }

                return true;
            }
        });
    }

    @Override
    public void setImageResource(int resId) {

        lastImageResource = resId;
        lastImageBitmap = null;

        if (useFilter || realHeight <= 0 || realWidth <= 0) {
            super.setImageResource(resId);
            return;
        }

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;

        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), resId, options);

        if (bmp == null) {
            super.setImageBitmap(null);
            return;
        }

        float scaleWidth = realWidth / (float) bmp.getWidth();
        float scaleHeight = realHeight / (float) bmp.getHeight();

        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;

        float finalWidth = bmp.getWidth() * scale;
        float finalHeight = bmp.getHeight() * scale;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bmp, (int) finalWidth, (int) finalHeight, false);
        bmp.recycle();
        bmp = null;

        super.setImageBitmap(scaledBitmap);
    }

    @Override
    public void setImageBitmap(Bitmap bitmap) {

        if (bitmap == null) {
            super.setImageBitmap(null);
            return;
        }

        lastImageResource = -1;
        lastImageBitmap = bitmap;

        if (useFilter || realHeight <= 0 || realWidth <= 0) {
            super.setImageBitmap(bitmap);
            return;
        }

        float scaleWidth = realWidth / (float) bitmap.getWidth();
        float scaleHeight = realHeight / (float) bitmap.getHeight();

        float scale = scaleWidth > scaleHeight ? scaleWidth : scaleHeight;

        float finalWidth = bitmap.getWidth() * scale;
        float finalHeight = bitmap.getHeight() * scale;

        Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, (int) finalWidth, (int) finalHeight, false);
        super.setImageBitmap(scaledBitmap);
    }


    public boolean isUseFilter() {
        return useFilter;
    }

    public void setUseFilter(boolean useFilter) {
        this.useFilter = useFilter;
    }
}
