package app.arkanoid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.WindowManager;


public class MainActivity extends ActionBarActivity {

    private Widok widok;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_prison_break);
        widok = new Widok(this);
        widok.display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        setContentView(widok);

    }
}
