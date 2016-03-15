package it.max.android.customroomsview.model;

import java.io.Serializable;

public class Stanza implements Serializable {
    private int    imgStanza;
    private String temperatura;
    private int    imgTemperatura;
    private String umidita;
    private int    imgUmidita;

    public Stanza(int    imgStanza,
                  String temperatura,
                  String umidita,
                  int    imgTemperatura,
                  int    imgUmidita) {
        super();
        this.imgStanza = imgStanza;
        this.temperatura = temperatura;
        this.umidita = umidita;
        this.imgTemperatura = imgTemperatura;
        this.imgUmidita = imgUmidita;
    }

    public int getImgStanza() {
        return imgStanza;
    }

    public void setImgStanza(int imgStanza) {
        this.imgStanza = imgStanza;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public int getImgTemperatura() {
        return imgTemperatura;
    }

    public void setImgTemperatura(int imgTemperatura) {
        this.imgTemperatura = imgTemperatura;
    }

    public String getUmidita() {
        return umidita;
    }

    public void setUmidita(String umidita) {
        this.umidita = umidita;
    }

    public int getImgUmidita() {
        return imgUmidita;
    }

    public void setImgUmidita(int imgUmidita) {
        this.imgUmidita = imgUmidita;
    }
}
