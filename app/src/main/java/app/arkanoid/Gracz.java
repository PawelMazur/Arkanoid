package app.arkanoid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 12.10.14.
 */
public class Gracz {

    private FloatBuffer buforWierzcholka;
    private FloatBuffer buforTekstury;
    private ByteBuffer buforIndeksow;
    private float x = 0.1f;
    private float y = 0.1f;

    public Gracz(float x, float y){

        this.x = x;
        this.y = y;

        float[] tablicaWierzcholkow = {
                -x,  y,
                -x, -y,
                x, -y,
                x,  y,
        };

        float[] tablicaTekstur = {
                0.0f, 0.0f,
                0.0f, 1.0f,
                1.0f, 1.0f,
                1.0f, 0.0f,
        };

        byte[] tablicaIndeksow = {
                0, 1, 2,
                0, 2, 3,
        };



        ByteBuffer byteBufferW = ByteBuffer.allocateDirect(tablicaWierzcholkow.length * 4);
        byteBufferW.order(ByteOrder.nativeOrder());
        buforWierzcholka = byteBufferW.asFloatBuffer();
        buforWierzcholka.put(tablicaWierzcholkow).position(0);

        ByteBuffer byteBufferT = ByteBuffer.allocateDirect(tablicaTekstur.length * 4);
        byteBufferT.order(ByteOrder.nativeOrder());
        buforTekstury = byteBufferT.asFloatBuffer();
        buforTekstury.put(tablicaTekstur).position(0);

        buforIndeksow = ByteBuffer.allocateDirect(tablicaIndeksow.length);
        buforIndeksow.order(ByteOrder.nativeOrder());
        buforIndeksow.put(tablicaIndeksow).position(0);

    }

    protected void rysuj(GL10 gl10, int[] tekstura){
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, tekstura[0]);
        gl10.glVertexPointer(2, GL10.GL_FLOAT, 0, buforWierzcholka);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, buforTekstury);
        gl10.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, buforIndeksow);
    }

}
