package app.arkanoid;

/**
 * Created by Pawel on 12.10.14.
 */
public class Sciana {

    public Wiersz[] rows;
    private String ZNACZNIK = " Sciana : ";

    public Sciana(int ile, int numerMapy, int iloscKlockow ){

        rows = new Wiersz[ile];
        for(int i = 0 ; i < ile  ; i++){
            rows[i] = new Wiersz(i, numerMapy, iloscKlockow);
        }
    }
}
