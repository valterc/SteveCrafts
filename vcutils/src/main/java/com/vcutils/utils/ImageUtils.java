package com.vcutils.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImageUtils {

    public static void saveBitmap(Context c, Bitmap image, String name, String path) throws Exception {

        File f = null;

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            f = new File(android.os.Environment.getExternalStorageDirectory() + path);
            f.mkdir();
        } else {
            f = new File(android.os.Environment.getDataDirectory() + path);
        }

        File file = new File(f, name);
        FileOutputStream os = new FileOutputStream(file, false);
        image.compress(CompressFormat.JPEG, 100, os);
        os.flush();
        os.close();

        // MediaStore.Images.Media.insertImage(c.getContentResolver(),
        // file.getAbsolutePath(), file.getName(), file.getName());
        MediaScannerConnection.scanFile(c, new String[]{file.getAbsolutePath()}, null, null);
        // MediaScannerConnection scanner = new MediaScannerConnection(c, null);
        // scanner.scanFile(filePath, null);

    }

    public static String getBitmapSize(Bitmap b) {

        if (b == null)
            return "0KB";

        int size = b.getByteCount();

        if (size / (1024 * 1024) < 1)
            return (size / 1024) + "KB";
        else
            return (size / (1024 * 1024)) + "MB";
    }

    public static byte[] bitmapToByteArray(Bitmap b) {
        if (b == null)
            return null;

        ByteArrayOutputStream outputStream = null;
        byte[] bytes = null;

        try {
            outputStream = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            bytes = outputStream.toByteArray();

        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    //ignored
                }
                outputStream = null;
            }
        }

        return bytes;
    }

    public static Bitmap byteArrayToBitmap(byte[] bytes) {
        if (bytes == null)
            return null;

        ByteArrayInputStream inputStream = null;
        Bitmap bitmap = null;

        try {
            inputStream = new ByteArrayInputStream(bytes);
            bitmap = BitmapFactory.decodeStream(inputStream);

        } catch (Error | Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
                inputStream = null;
            }
        }

        return bitmap;
    }

    public static Bitmap getRoundBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawCircle(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getWidth() / 2, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;
    }

    public static Bitmap fromBase64String(String base64) {

        if (base64 == null) {
            return null;
        }

        byte[] imageBytes = Base64.decode(base64.getBytes(), Base64.DEFAULT);
        return byteArrayToBitmap(imageBytes);
    }

}
