package app.arkanoid;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Pawel on 12.10.14.
 */
public class RendererGL implements GLSurfaceView.Renderer {


    private String ZNACZNIK = " RendererGL : ";
    private Context context;
    private Gracz gracz = new Gracz(0.1f, 0.02f);
    private Pilka pilka = new Pilka();
    private Cegla cegla;
    private Sciana sciana;
    private Wynik wynik = new Wynik();
    private Wynik wynik2 = new Wynik();

    private Tekstury tekstury = new Tekstury();
    private Tekstury tekstury1 = new Tekstury();
    private Tekstury tekstury2 = new Tekstury();
    private Tekstury teksturyW = new Tekstury();
    private Tekstury tekstury_Pilka_2 = new Tekstury();

    private Tekstury teks_zero= new Tekstury();
    private Tekstury teks_jeden = new Tekstury();
    private Tekstury teks_dwa = new Tekstury();
    private Tekstury teks_trzy = new Tekstury();
    private Tekstury teks_cztery = new Tekstury();
    private Tekstury teks_piec = new Tekstury();
    private Tekstury teks_szesc = new Tekstury();
    private Tekstury teks_siedem = new Tekstury();
    private Tekstury teks_osiem = new Tekstury();
    private Tekstury teks_dziewiec = new Tekstury();



    protected final int GRACZ_W_LEWO = 1;
    protected final int GRACZ_W_PRAWO = 2;
    protected int gracz_w_akcji = 0;
    protected static float SZYBKOSC_GRACZA = 0.01f;
    protected static float aktualna_pozycja_X = 0.2f;
    protected static float aktualna_pozycja_Y = 0.1f;


    private float przesunieciePilkiX = 0.01f;
    private float przesunieciePilkiY = 0.01f;

    private int numer = 1  ;
    private int ile = 0;
    private int zbitych = 0;
    private int trybRundy = 2;
    private int iloscKlockow = 11;

    private int[] teksturaKlockow = new int[1];
    private int[] teksturaPilki = new int[1];
    private int[] teksturaPlayera = new int[1];
    private int[] teksturaWynikow = new int[1];
    private int[] teksturaPilki2 = new int[1];

    private int[] tekstura_zero = new int[1];
    private int[] tekstura_jeden = new int[1];
    private int[] tekstura_dwa = new int[1];
    private int[] tekstura_trzy = new int[1];
    private int[] tekstura_cztery = new int[1];
    private int[] tekstura_piec = new int[1];
    private int[] tekstura_szesc = new int[1];
    private int[] tekstura_siedem = new int[1];
    private int[] tekstura_osiem = new int[1];
    private int[] tekstura_dziewiec = new int[1];

    public RendererGL(Context context) {
        this.context = context;

    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {
        dodanieStalych(gl10);
        dodanieTekstur(gl10);
        teksturaWynikow = teksturyW.zaladowanieTekstury(gl10, context, R.drawable.zero);
        //player = new Player(0.1f, 0.02f);
        teksturaPilki2 = tekstury_Pilka_2.zaladowanieTekstury(gl10, context, R.drawable.pilka2);
        teksturaPilki = tekstury1.zaladowanieTekstury(gl10, context, R.drawable.pilka);
        //pilka = new Pilka();
        teksturaPlayera = tekstury2.zaladowanieTekstury(gl10, context, R.drawable.drewno);

        //cegla = new Cegla(2);
        teksturaKlockow = tekstury.zaladowanieTekstury(gl10, context, R.drawable.kamien);
        //cegla.zaladowanieTekstury(gl10, context, R.drawable.kamien);
        sciana = new Sciana(numer, trybRundy, iloscKlockow);
        zaladowanieTeksturDlaKonstruktora(gl10);
    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int szerokosc, int wysokosc) {
        gl10.glViewport(0, 0, szerokosc, wysokosc);
        gl10.glMatrixMode(GL10.GL_PROJECTION);
        gl10.glLoadIdentity();
        gl10.glOrthof(0f, 1f, 0f, 1f, -1f, 1f);

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        gl10.glClear(GL10.GL_DEPTH_BUFFER_BIT | GL10.GL_COLOR_BUFFER_BIT);

        dodanieWynikow(gl10);
        dodanieWynikow2(gl10);
        movePlayer(gl10);
        tworzenieSciany(gl10);
        moveBall(gl10);
        wykrywanieKoliziiMapy();
        wykrywanieKoliziiWCeglach();
        //wykrywanieKoliziiPaletka();
        wykrywanieKoliziiPaletka2(aktualna_pozycja_X, aktualna_pozycja_Y);
        //tworzenieSciany(gl10);
        //tworzenieCegly(gl10);
    }



    private void dodanieTekstur(GL10 gl10){
        gl10.glEnable(GL10.GL_TEXTURE_2D);
        gl10.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
    }

    private void dodanieStalych(GL10 gl10){
        gl10.glEnable(GL10.GL_DEPTH_TEST);
        gl10.glEnableClientState(GL10.GL_VERTEX_ARRAY);
    }

    private void movePlayer(GL10 gl10){
        gl10.glPushMatrix();
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        if(gracz_w_akcji == GRACZ_W_LEWO && aktualna_pozycja_X > 0.1){
            aktualna_pozycja_X = aktualna_pozycja_X - SZYBKOSC_GRACZA;
        }else if(gracz_w_akcji == GRACZ_W_PRAWO && aktualna_pozycja_X < 0.9){
            aktualna_pozycja_X = aktualna_pozycja_X + SZYBKOSC_GRACZA;
        }
        //gl10.glTranslatef(BANK_POSITION, -1, -2);
        gl10.glTranslatef(aktualna_pozycja_X, aktualna_pozycja_Y, 0);
        gl10.glMatrixMode(GL10.GL_TEXTURE);
        gl10.glLoadIdentity();
        //player.tekstury.zaladowanieTekstury(gl10, context, R.drawable.drewno);
        gracz.rysuj(gl10, teksturaPlayera);
        gl10.glPopMatrix();
        gl10.glLoadIdentity();

    }

    private void moveBall(GL10 gl10){

        gl10.glPushMatrix();
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        pilka.pozycjaX += przesunieciePilkiX;
        pilka.pozycjaY += przesunieciePilkiY;
        gl10.glTranslatef(pilka.pozycjaX, pilka.pozycjaY, 0);
        //gl10.glTranslatef(0, 0, 0);
        gl10.glMatrixMode(GL10.GL_TEXTURE);
        gl10.glLoadIdentity();
        //pilka.tekstury.zaladowanieTekstury(gl10, context, R.drawable.pilka);
        if (zbitych < 10){
            pilka.rysuj(gl10, teksturaPilki);
        }else {
            pilka.rysuj(gl10, teksturaPilki2);
        }
        gl10.glPopMatrix();
        gl10.glLoadIdentity();

    }

    private void tworzenieCegly(GL10 gl10){

        gl10.glPushMatrix();
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        gl10.glTranslatef(0, 0, 0);
        gl10.glMatrixMode(GL10.GL_TEXTURE);
        //cegla.tekstury.zaladowanieTekstury(gl10, context, R.drawable.kamien);
        //cegla.rysuj(gl10);
        gl10.glLoadIdentity();
        gl10.glPopMatrix();
        gl10.glLoadIdentity();
    }

    private void tworzenieSciany(GL10 gl10){

        for(int i = 0; i < sciana.rows.length; i++ ){
            for(int j = 0; j < sciana.rows[i].cegla.length; j++){
                if(!sciana.rows[i].cegla[j].jestZniszczone){
                    //Log.d(ZNACZNIK, "Numer : "+ sciana.rows[i].cegla[j].typCegly);

                    switch (sciana.rows[i].cegla[j].typCegly){

                        default:
                            gl10.glPushMatrix();
                            gl10.glMatrixMode(GL10.GL_MODELVIEW);
                            gl10.glLoadIdentity();
                            gl10.glTranslatef(sciana.rows[i].cegla[j].pozycjaX, sciana.rows[i].cegla[j].pozycjaY, 0);
                            gl10.glMatrixMode(GL10.GL_TEXTURE);
                            gl10.glLoadIdentity();
                            //cegla.tekstury.zaladowanieTekstury(gl10, context, R.drawable.kamien);
                            //cegla.rysuj(gl10);
                            //sciana.rows[i].cegla[j].zaladowanieTekstury(gl10, context, R.drawable.kamien);
                            sciana.rows[i].cegla[j].rysuj(gl10, teksturaKlockow);
                            gl10.glPopMatrix();
                            gl10.glLoadIdentity();
                        break;

                    }
                } else {
                    czyWszystkieCeglyZostalyZbite();
                }
            }
        }
    }

    private void wykrywanieKoliziiMapy(){
        if ((pilka.pozycjaX + 0.05 > 1 )||(
            pilka.pozycjaX < 0)){
            przesunieciePilkiX = przesunieciePilkiX * -1;
        }
        if ((pilka.pozycjaY + 0.05 > 1) //||
                /*pilka.pozycjaY < 0*/){
            przesunieciePilkiY = przesunieciePilkiY * -1;
        }
    }

    private void wykrywanieKoliziiWCeglach(){
        for (int i = 0; i < sciana.rows.length ; i++ ){
            for (int j = 0; j < sciana.rows[i].cegla.length; j++){
                if (!sciana.rows[i].cegla[j].jestZniszczone){
                    if (((sciana.rows[i].cegla[j].pozycjaX - 0.05 < pilka.pozycjaX) &&
                    (pilka.pozycjaX < sciana.rows[i].cegla[j].pozycjaX + 0.1/*szerokosc*/ )) &&
                    ((sciana.rows[i].cegla[j].pozycjaY - 0.05  < pilka.pozycjaY) &&
                    (pilka.pozycjaY < sciana.rows[i].cegla[j].pozycjaY + 0.05/*wysokosc*/ ))) {
                        przesunieciePilkiY = przesunieciePilkiY * -1f;
                           sciana.rows[i].cegla[j].jestZniszczone = true;
                           zbitych = zbitych + 1;
                           czyWszystkieCeglyZostalyZbite();
                           Log.d(ZNACZNIK, " Zbitych jest : " + zbitych);
                        if ((sciana.rows[i].cegla[j].pozycjaY - 0.05  < pilka.pozycjaY) &&
                            (pilka.pozycjaY < sciana.rows[i].cegla[j].pozycjaY + 0.1 )){
                        przesunieciePilkiX = przesunieciePilkiX * -1f;
                       }
                    }
                }
            }
        }
    }

    private void wykrywanieKoliziiPaletka(){
        if (((aktualna_pozycja_X - 0.15 < pilka.pozycjaX) &&
            (pilka.pozycjaX < aktualna_pozycja_X + 0.10))&&
            ((aktualna_pozycja_Y - 0.01 < pilka.pozycjaY) &&
            (pilka.pozycjaY < aktualna_pozycja_Y + 0.01))){
                przesunieciePilkiY = przesunieciePilkiY * -1f;

        }

    }

    private void wykrywanieKoliziiPaletka2(float BANK_POSITION_X, float BANK_POSITION_Y){
        if (((BANK_POSITION_X - 0.15 < pilka.pozycjaX) &&
                (pilka.pozycjaX < BANK_POSITION_X + 0.10))&&
                ((BANK_POSITION_Y - 0.01 < pilka.pozycjaY) &&
                        (pilka.pozycjaY < BANK_POSITION_Y + 0.01))){
            przesunieciePilkiY = przesunieciePilkiY * -1f;

        }

    }


    private void czyWszystkieCeglyZostalyZbite(){
        if (zbitych == iloscKlockow){
            trybRundy = trybRundy + 1;
            if (trybRundy < 7){
                zmianaMapy(trybRundy);
                Log.d(ZNACZNIK, " Tryb rundy : " + trybRundy);
            }else {
                trybRundy = 0;
            }
        }
    }

    private void zmianaMapy(int trybRundy){

        pilka.pozycjaY = 0.5f;
        pilka.pozycjaX = 0.4f;

        switch (trybRundy){

            case 0:
                iloscKlockow = 15;
                sciana = new Sciana(numer, trybRundy, iloscKlockow);
                zbitych = 0;
                break;
            case 1:
                iloscKlockow = 20;
                sciana = new Sciana(numer, trybRundy, iloscKlockow);
                zbitych = 0;
                break;
            case 2:
                iloscKlockow = 20;
                sciana = new Sciana(numer, trybRundy, iloscKlockow);
                zbitych = 0;
                break;
            case 3:
                iloscKlockow = 15;
                sciana = new Sciana(numer, trybRundy, iloscKlockow);
                zbitych = 0;
                break;
            case 4:
                iloscKlockow = 20;
                sciana = new Sciana(numer, trybRundy, iloscKlockow);
                zbitych = 0;
                break;
            case 5:
                iloscKlockow = 11;
                sciana = new Sciana(numer, ile, iloscKlockow);
                zbitych = 0;
                break;
            default:
                iloscKlockow = 20;
                sciana = new Sciana(numer, ile, iloscKlockow);
                zbitych = 0;
                break;


        }
    }

    private void dodanieWynikow(GL10 gl10){

        gl10.glPushMatrix();
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        gl10.glRotatef(0, 1, 0, 0);
        gl10.glTranslatef(0.9f, 0.9f, 0);
        gl10.glMatrixMode(GL10.GL_TEXTURE);
        gl10.glLoadIdentity();
        //wynik.rysuj(gl10, teksturaWynikow);
        int temp = zbitych % 10;
        wybranieWyniku(wynik,temp, gl10);
        gl10.glPopMatrix();
        gl10.glLoadIdentity();

    }

    private void dodanieWynikow2(GL10 gl10){

        gl10.glPushMatrix();
        gl10.glMatrixMode(GL10.GL_MODELVIEW);
        gl10.glLoadIdentity();
        gl10.glRotatef(0, 1, 0, 0);
        gl10.glTranslatef(0.8f, 0.9f, 0);
        gl10.glMatrixMode(GL10.GL_TEXTURE);
        gl10.glLoadIdentity();
        //wynik.rysuj(gl10, teksturaWynikow);
        int temp = zbitych / 10;
        wybranieWyniku(wynik2, temp, gl10);
        gl10.glPopMatrix();
        gl10.glLoadIdentity();

    }

    private void zaladowanieTeksturDlaKonstruktora(GL10 gl10){
        tekstura_zero = teks_zero.zaladowanieTekstury(gl10, context, R.drawable.zero);
        tekstura_jeden = teks_jeden.zaladowanieTekstury(gl10, context, R.drawable.jeden);
        tekstura_dwa = teks_dwa.zaladowanieTekstury(gl10, context, R.drawable.dwa);
        tekstura_trzy = teks_trzy.zaladowanieTekstury(gl10, context, R.drawable.trzy);
        tekstura_cztery = teks_cztery.zaladowanieTekstury(gl10, context, R.drawable.cztery);
        tekstura_piec = teks_piec.zaladowanieTekstury(gl10, context, R.drawable.piec);
        tekstura_szesc = teks_szesc.zaladowanieTekstury(gl10, context, R.drawable.szesc);
        tekstura_siedem = teks_siedem.zaladowanieTekstury(gl10, context, R.drawable.siedem);
        tekstura_osiem = teks_osiem.zaladowanieTekstury(gl10, context, R.drawable.osiem);
        tekstura_dziewiec = teks_dziewiec.zaladowanieTekstury(gl10, context, R.drawable.dziewiec);
    }

    private void wybranieWyniku(Wynik wynik, int zbitych, GL10 gl10){
        switch (zbitych){
            case 0:
                wynik.rysuj(gl10, tekstura_zero);
                break;
            case 1:
                wynik.rysuj(gl10, tekstura_jeden);
                break;
            case 2:
                wynik.rysuj(gl10, tekstura_dwa);
                break;
            case 3:
                wynik.rysuj(gl10, tekstura_trzy);
                break;
            case 4:
                wynik.rysuj(gl10, tekstura_cztery);
                break;
            case 5:
                wynik.rysuj(gl10, tekstura_piec);
                break;
            case 6:
                wynik.rysuj(gl10, tekstura_szesc);
                break;
            case 7:
                wynik.rysuj(gl10, tekstura_siedem);
                break;
            case 8:
                wynik.rysuj(gl10, tekstura_osiem);
                break;
            case 9:
                wynik.rysuj(gl10, tekstura_dziewiec);
                break;

        }
    }

}
