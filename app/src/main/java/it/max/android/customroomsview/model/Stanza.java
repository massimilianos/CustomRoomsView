package it.max.android.customroomsview.model;

import java.io.Serializable;

public class Stanza implements Serializable {
    private Integer imgStanza;
    private String  temperatura;
    private Integer imgTemperatura;
    private String  umidita;
    private Integer imgUmidita;

    public Stanza(Integer imgStanza,
                  String  temperatura,
                  String  umidita,
                  Integer imgTemperatura,
                  Integer imgUmidita) {
        super();
        this.imgStanza      = imgStanza;
        this.temperatura    = temperatura;
        this.umidita        = umidita;
        this.imgTemperatura = imgTemperatura;
        this.imgUmidita     = imgUmidita;
    }

    public Integer getImgStanza() {
        return imgStanza;
    }

    public void setImgStanza(Integer imgStanza) {
        this.imgStanza = imgStanza;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getImgTemperatura() {
        return imgTemperatura;
    }

    public void setImgTemperatura(Integer imgTemperatura) {
        this.imgTemperatura = imgTemperatura;
    }

    public String getUmidita() {
        return umidita;
    }

    public void setUmidita(String umidita) {
        this.umidita = umidita;
    }

    public Integer getImgUmidita() {
        return imgUmidita;
    }

    public void setImgUmidita(Integer imgUmidita) {
        this.imgUmidita = imgUmidita;
    }
}
