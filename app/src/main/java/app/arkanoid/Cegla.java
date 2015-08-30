package app.arkanoid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 12.10.14.
 */
public class Cegla {

    protected Tekstury tekstury;
    public float pozycjaY = 0f;
    public float pozycjaX = 0f;
    //public float pozycjaY = 0.7f;
    //public float pozycjaX = 0.5f;
    public float posT = 0f;
    public boolean jestZniszczone = false;
    public int typCegly = 0;

    private FloatBuffer buforWierzcholkow;
    private FloatBuffer buforTekstur;
    private ByteBuffer buforIndeksow;
    private int[] tekstura = new int[1];

    public Cegla(int type){


        tekstury = new Tekstury();

        float[] wierzcholki = {
                0.0f, 0.0f, 0.0f,
                0.1f, 0.0f, 0.0f,
                0.1f, 0.05f, 0.0f,
                0.0f, 0.05f, 0.0f,
        };

        float[] tekstury = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };

        byte[] indeksy = {
                0, 1, 2,
                0, 2, 3,
        };

        typCegly = type;

        ByteBuffer byteBufferW = ByteBuffer.allocateDirect(wierzcholki.length * 4);
        byteBufferW.order(ByteOrder.nativeOrder());
        buforWierzcholkow = byteBufferW.asFloatBuffer();
        buforWierzcholkow.put(wierzcholki).position(0);

        ByteBuffer byteBufferT = ByteBuffer.allocateDirect(tekstury.length * 4);
        byteBufferT.order(ByteOrder.nativeOrder());
        buforTekstur = byteBufferT.asFloatBuffer();
        buforTekstur.put(tekstury).position(0);


        buforIndeksow = ByteBuffer.allocateDirect(indeksy.length);
        buforIndeksow.order(ByteOrder.nativeOrder());
        buforIndeksow.put(indeksy).position(0);


    }


    public void rysuj(GL10 gl10, int[] tekstura){
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, tekstura[0]);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, buforWierzcholkow);
        gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, buforTekstur);
        gl10.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, buforIndeksow);
    }

}
