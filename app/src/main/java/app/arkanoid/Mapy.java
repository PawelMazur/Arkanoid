package app.arkanoid;

/**
 * Created by Pawel on 15.10.14.
 */
public class Mapy  {

    private Cegla cegla[];
    protected int nextMap;
    //private String ZNACZNIK = "MAPA";

    public Mapy(Cegla[] cegla) {
        this.cegla = cegla;

    }


    protected void normalnaMapa(int numerKolumny){
        for (int i = 0; i < numerKolumny; i++ ){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.9f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX = 0.1f + (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = 0.1f + (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.6f;
            }
        }
    }

    protected void duzaMapa(int numerKolumny){
        for (int i = 0 ; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.9f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX = 0.1f + (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = 0.1f + (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.6f;
            }

        }

    }

    protected void szachownicaMapaZOdstepami(int numerKolumny){
        for (int i = 0 ; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.9f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX =  0.1f + (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = 0.1f + (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.6f;
            }

        }

    }

    protected void szachownicaMapa(int numerKolumny){
        for (int i = 0 ; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX =  (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.75f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.65f;
            }

        }

    }

    protected void szachownicaDuzaMapa(int numerKolumny){
        for (int i = 0 ; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX =  (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.75f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.65f;
            }
        }
    }

    protected void zTrojkatamiMapa(int numerKolumny){
        for (int i = 0 ; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 1 ){
                cegla[i].pozycjaX = 0.5f + i * 2f / 10;
                cegla[i].pozycjaY = 0.9f;
            }
            if (i >= 1 && i < 3){
                cegla[i].pozycjaX = 0.2f + i * 2f / 10f;
                cegla[i].pozycjaY = 0.85f;
            }
            if (i >= 3 && i < 7){
                cegla[i].pozycjaX = 0.3f + (i % 3) * 2f / 10f;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 7 && i < 12){
                cegla[i].pozycjaX = 0.2f + (i % 7) * 2f / 10f;
                cegla[i].pozycjaY = 0.75f;
            }
            /*
            if (i >= 12 && i < 17 ){
                cegla[i].pozycjaX = 0.1f + (i % 12) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }*/
        }
    }

    protected void probnaMapa(int numerKolumny){
        for (int i = 0; i < numerKolumny ; i++){
            cegla[i] = new Cegla(i);
            if (i < 5 ){
                cegla[i].pozycjaX = 0.1f + i * 2f / 10;
                cegla[i].pozycjaY = 0.9f;
            }
            if (i >= 5 && i < 10){
                cegla[i].pozycjaX = 0.1f + (i % 5) * 2f / 10f;
                cegla[i].pozycjaY = 0.8f;
            }
            if (i >= 10 && i < 15){
                cegla[i].pozycjaX = 0.1f + (i % 10) * 2f / 10f;
                cegla[i].pozycjaY = 0.7f;
            }
            if (i >= 15 && i < 20){
                cegla[i].pozycjaX = 0.1f + (i % 15) * 2f / 10f;
                cegla[i].pozycjaY = 0.6f;
            }
            //*/
        }
    }




}
