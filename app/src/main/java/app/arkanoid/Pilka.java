package app.arkanoid;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 12.10.14.
 */
public class Pilka {

    public float pozycjaY = 0f;
    public float pozycjaX = 0f;

    private FloatBuffer buforWierzcholkow;
    private FloatBuffer buforTekstury;
    private ByteBuffer buforIndeksow;

    public Pilka(){

        pozycjaY = 0.1f;
        pozycjaX = 0.57865f;

        float[] wierzcholki = {
                0.0f, 0.0f, 0.0f,
                0.05f, 0.0f, 0.0f,
                0.05f, 0.05f, 0.0f,
                0.0f, 0.05f, 0.0f,
        };

        float[] tekstura = {
                0.0f, 0.0f,
                1.0f, 0.0f,
                1.0f, 1.0f,
                0.0f, 1.0f,
        };

        byte[] indeksow = {
                0, 1, 2,
                0, 2, 3
        };

        ByteBuffer byteBufferW = ByteBuffer.allocateDirect(wierzcholki.length * 4);
        byteBufferW.order(ByteOrder.nativeOrder());
        buforWierzcholkow = byteBufferW.asFloatBuffer();
        buforWierzcholkow.put(wierzcholki).position(0);

        ByteBuffer byteBufferT = ByteBuffer.allocateDirect(tekstura.length * 4);
        byteBufferT.order(ByteOrder.nativeOrder());
        buforTekstury = byteBufferT.asFloatBuffer();
        buforTekstury.put(tekstura).position(0);

        buforIndeksow = ByteBuffer.allocateDirect(indeksow.length);
        buforIndeksow.order(ByteOrder.nativeOrder());
        buforIndeksow.put(indeksow).position(0);
    }

    public void rysuj(GL10 gl10, int[] tekstura){
        gl10.glBindTexture(GL10.GL_TEXTURE_2D, tekstura[0]);
        //gl10.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[0]);
        gl10.glVertexPointer(3, GL10.GL_FLOAT, 0, buforWierzcholkow);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl10.glTexCoordPointer(2, GL10.GL_FLOAT, 0, buforTekstury);
        gl10.glDrawElements(GL10.GL_TRIANGLES, 6, GL10.GL_UNSIGNED_BYTE, buforIndeksow);
    }

}
