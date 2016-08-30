package com.itexico.instrumentationtesting.espresso.failurehandler;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by iTexico Developer on 8/30/2016.
 */
public class ScreenShotManager {

    private static final float HIGH_DENSITY_THRESHOLD = 2.5f;
    private static final float MEDIUM_DENSITY_THRESHOLD = 1.8f;

    private static final String TAG = ScreenShotManager.class.getSimpleName();
    private final Context mContext;

    public ScreenShotManager(final Context context){
        mContext =context;
    }

    public Uri getScreenShotURI(final View view) {
        if (!view.isDrawingCacheEnabled()) {
            view.setDrawingCacheEnabled(true);
            view.setDrawingCacheQuality(determineQualityOfDrawingCache());
        }
        Bitmap bitmap = view.getDrawingCache();
        Uri location = convertBitmapToPng(bitmap);
        return location;
    }

    private Uri convertBitmapToPng(Bitmap bitmap) {
        File screenshot = new File(getCacheDir(), "test-screenshot.png");
        try {
            FileOutputStream fos = new FileOutputStream(screenshot);
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, fos);
            fos.flush();
            fos.close();
            Log.d(TAG, "location of screenshot: " + screenshot.getAbsolutePath());
            return Uri.fromFile(screenshot);
        } catch (IOException e) {
            Log.e(TAG, "Unable to write bitmap data to file", e);
            return null;
        }
    }

    private String getCacheDir() {
        String cacheDir;
        if (Build.VERSION.SDK_INT >= 23) {
            cacheDir = mContext.getFilesDir().getAbsolutePath();
        } else {
            cacheDir = mContext.getExternalCacheDir().getAbsolutePath();
        }
        new File(cacheDir).mkdirs();
        return cacheDir;
    }


    private int determineQualityOfDrawingCache() {
        float density = mContext.getResources().getDisplayMetrics().density;
        if (density > HIGH_DENSITY_THRESHOLD) {
            return View.DRAWING_CACHE_QUALITY_HIGH;
        } else if (density > MEDIUM_DENSITY_THRESHOLD) {
            return View.DRAWING_CACHE_QUALITY_AUTO;
        } else {
            return View.DRAWING_CACHE_QUALITY_LOW;
        }
    }
}
