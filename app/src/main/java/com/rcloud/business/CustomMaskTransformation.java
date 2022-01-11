package com.rcloud.business;

/*import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.internal.Utils;

public class CustomMaskTransformation extends MaskTransformation{

    private static Paint paint = new Paint();
    private int maskId;

    static {
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    }
    public CustomMaskTransformation(int maskId) {
        this.maskId = maskId;
    }
    @Override
    protected Bitmap transform(@NonNull Context context,@NonNull BitmapPool pool,@NonNull Bitmap toTransform, int outWidth, int outHeight) {

        int width = toTransform.getWidth();
        int height = toTransform.getHeight();

        int w, h;

        if (width > height) {
            w = height;
            h = height;
        } else {
            w = width;
            h = width;
        }
        Bitmap bitmap = pool.get(w, h, Bitmap.Config.ARGB_8888);
        bitmap.setHasAlpha(true);
        Drawable mask = Utils.getMaskDrawable(context.getApplicationContext(), maskId);
        setCanvasBitmapDensity(toTransform, bitmap);

        Canvas canvas = new Canvas(bitmap);
        mask.setBounds(0,0,w,h);
        mask.draw(canvas);

        canvas.drawBitmap(toTransform, (float) (w-width)/2, (float) (h-height)/2, paint);
        return bitmap;
    }
    private void setCanvasBitmapDensity(Bitmap toTranform, Bitmap canvasBitmap) {
        canvasBitmap.setDensity(toTranform.getDensity());
    }

}
*/