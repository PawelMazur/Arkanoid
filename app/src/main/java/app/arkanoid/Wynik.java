package app.arkanoid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 19.10.14.
 */
public class Wynik {

    private FloatBuffer buforWierzcholkow;
    private FloatBuffer buforTekstury;
    private ByteBuffer buforIndeksow;

    public Wynik() {

        float[] tablicaWierzcholkow = {
                0.0f, 0.0f, 0.0f,
                0.1f, 0.0f, 0.0f,
                0.1f, 0.1f, 0.0f,
                0.0f, 0.1f, 0.0f,
        };

        float[] tablicaTekstury = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };

        byte[] tablicaIndeksow = {
                0, 1, 2,
                0, 2, 3,
        };

        ByteBuffer byteBufferW = ByteBuffer.allocateDirect(tablicaWierzcholkow.length * 4);
        byteBufferW.order(ByteOrder.nativeOrder());
        buforWierzcholkow = byteBufferW.asFloatBuffer();
        buforWierzcholkow.put(tablicaWierzcholkow).position(0);

        ByteBuffer byteBufferT = ByteBuffer.allocateDirect(tablicaTekstury.length * 4);
        byteBufferT.order(ByteOrder.nativeOrder());
        buforTekstury = byteBufferT.asFloatBuffer();
        buforTekstury.put(tablicaTekstury).position(0);

        buforIndeksow = ByteBuffer.allocateDirect(tablicaIndeksow.length);
        buforIndeksow.order(ByteOrder.nativeOrder());
        buforIndeksow.put(tablicaIndeksow).position(0);


    }

    protected void rysuj(GL10 gl10, int[] tekstura){
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, tekstura[0]);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, buforWierzcholkow);
        gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, buforTekstury);
        gl10.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, buforIndeksow);
    }
}
