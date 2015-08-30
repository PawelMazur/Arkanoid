package app.arkanoid;

import android.util.Log;

/**
 * Created by Pawel on 12.10.14.
 */
public class Wiersz {

    public Cegla[] cegla;

    private int numer = 0;
    private String ZNACZNIK = "Wiesz : ";
    public int nextMap;
    private int numerKolumny;
    private int iloscKlockow;

    public Wiersz() {
    }

    public Wiersz(int numerWiersza, int nextMap, int iloscKlockow){

        this.nextMap = nextMap;
        numer = numerWiersza ;
        numerKolumny = iloscKlockow;
        cegla = new Cegla[numerKolumny];

        Mapy mapy = new Mapy(cegla);

        Log.d(ZNACZNIK, "next Map : " + nextMap);
        switch (nextMap){

            case 0:
                mapy.normalnaMapa(numerKolumny);
                break;
            case 1:
                mapy.duzaMapa(numerKolumny);
                break;
            case 2:
                mapy.szachownicaMapaZOdstepami(numerKolumny);
                break;
            case 3:
                mapy.szachownicaMapa(numerKolumny);
                break;
            case 4:
                mapy.szachownicaDuzaMapa(numerKolumny);
                break;
            case 5:
                mapy.zTrojkatamiMapa(numerKolumny);
                break;

        }
    }
}
