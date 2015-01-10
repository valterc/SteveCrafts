package com.vcutils.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.webkit.URLUtil;
import android.widget.ImageView;

import com.vcutils.tasks.AsyncTasks;
import com.vcutils.tasks.download.DownloadImageAsyncTask;
import com.vcutils.tasks.download.DownloadImageListener;
import com.vcutils.tasks.download.DownloadImageRequest;

/**
 * Created by Valter on 29/05/2014.
 */
public class WebImageView extends ImageView {

    private String imageUrl;
    private int errorImageResourceId;

    public WebImageView(Context context) {
        super(context);
    }

    public WebImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setImageSource(String source) {
        if (imageUrl != null && imageUrl.equals(source))
            return;

        if (URLUtil.isNetworkUrl(source)) {
            imageUrl = source;
            setImageDrawable(null);

/*
            new DownloadImageAsyncTask().execute(new DownloadImageRequest(source, new DownloadImageListener() {
                @Override
                public void OnDownloadComplete(String imageUrl, boolean error, Bitmap bitmap) {
                    if (error){
                        setImageResource(errorImageResourceId);
                    } else {
                        if (imageUrl.equals(WebImageView.this.imageUrl)) {
                            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{ getDrawable() == null ? new ColorDrawable(0x00FFFFFF) : getDrawable(), new BitmapDrawable(getResources(), bitmap)});
                            setImageDrawable(transitionDrawable);
                            transitionDrawable.startTransition(500);
                        }
                    }
                }
            }));
*/
            AsyncTasks.safeExecuteOnExecutor(new DownloadImageAsyncTask(), new DownloadImageRequest(source, new DownloadImageListener() {
                @Override
                public void OnDownloadComplete(String imageUrl, boolean error, Bitmap bitmap) {
                    if (error) {
                        setImageResource(errorImageResourceId);
                    } else {
                        if (imageUrl.equals(WebImageView.this.imageUrl)) {
                            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{getDrawable() == null ? new ColorDrawable(0x00FFFFFF) : getDrawable(), new BitmapDrawable(getResources(), bitmap)});
                            setImageDrawable(transitionDrawable);
                            transitionDrawable.startTransition(500);
                        }
                    }
                }
            }));

        } else {
            super.setImageURI(Uri.parse(source));
        }
    }

    public void setErrorImage(int resourceId) {
        errorImageResourceId = resourceId;
    }


}
