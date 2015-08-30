package app.arkanoid;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.MotionEvent;

/**
 * Created by Pawel on 12.10.14.
 */
public class Widok extends GLSurfaceView {

    private RendererGL rendererGL;
    protected Display display;

    public Widok(Context context) {
        super(context);
        rendererGL = new RendererGL(context);
        setRenderer(rendererGL);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float y = event.getY();
        float x = event.getX();

        float rozmiar = display.getHeight() / 2;
        if (y > rozmiar){
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
                    if(x < display.getWidth() / 2){
                        rendererGL.gracz_w_akcji = rendererGL.GRACZ_W_LEWO;
                    }else  {
                        rendererGL.gracz_w_akcji = rendererGL.GRACZ_W_PRAWO;
                    }
                break;
                case MotionEvent.ACTION_UP:
                    //rendererGL.gracz_w_akcji = 0;
            }
        }

        return super.onTouchEvent(event);
    }


}
