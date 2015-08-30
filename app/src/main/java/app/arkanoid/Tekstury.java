package app.arkanoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 12.10.14.
 */
public class Tekstury {

    private int[] tekstury = new int[1];

    public Tekstury() {

    }

    protected int[] zaladowanieTekstury(GL10 gl10, Context context, int zasob){
        Bitmap bitmap = BitmapFactory.decodeStream(context.getResources().openRawResource(zasob));
        gl10.glGenTextures(1, tekstury, 0);
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, tekstury[0]);
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, GL10.GL_NEAREST);
        gl10.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, GL10.GL_NEAREST);
        GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        return tekstury;
    }

}
