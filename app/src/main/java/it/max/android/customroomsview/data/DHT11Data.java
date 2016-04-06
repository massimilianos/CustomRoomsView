package it.max.android.customroomsview.data;

import android.content.Context;
import android.os.StrictMode;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.constants.JSONConstants;
import it.max.android.customroomsview.constants.RestConstants;
import it.max.android.customroomsview.constants.ServerConnectionConstants;
import it.max.android.customroomsview.constants.StanzeConstants;
import it.max.android.customroomsview.model.DHT11;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.utils.InternetUtils;

public class DHT11Data {
    private static String serverAddress = ServerConnectionConstants.SERVER_ADDRESS;
    private static String serverPort = ServerConnectionConstants.SERVER_PORT;

    private static InternetUtils iu = new InternetUtils(serverAddress, serverPort);
    private static String rootServer = iu.creaURLServer();

    public DHT11Data() {}

    public ArrayList<Stanza> creaDatiListaStanze(Context context) {
        RelayData relayData = new RelayData();

        // AGGIUNGO UNA STANZA CHE E' L'UFFICIO
        Integer numeroStanze = StanzeConstants.NUMERO_STANZE + 1;

        // INIZIALIZZO L'ARRAY CHE CONTERRA' I DATI DELLE STANZE
        Stanza[] stanze = new Stanza[numeroStanze];

        // INSERISCO LA STANZA "UFFICIO"
        stanze[0] = new Stanza( R.drawable.num_0,
                getTempHumid('T', iu, rootServer),
                getTempHumid('H', iu, rootServer),
                R.drawable.temperature, R.drawable.humidity);

        // INIZIO DALLA STANZA 1 PERCHE' LA STANZA "0" E' L'UFFICIO
        for(int indice = 1; indice < numeroStanze; indice++) {
            Integer stato = relayData.getRelayState(iu, rootServer, getRandomNumberInRange(1, 4));

            Integer idImgNumeroStanza = null;
            switch(indice) {
                case 1:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_1_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_1_red;
                    }
                break;

                case 2:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_2_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_2_red;
                    }
                break;

                case 3:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_3_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_3_red;
                    }
                break;

                case 4:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_4_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_4_red;
                    }
                break;

                case 5:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_5_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_5_red;
                    }
                break;

                case 6:
                    if (stato == 0) {
                        idImgNumeroStanza = R.drawable.num_6_green;
                    } else if (stato == 1) {
                        idImgNumeroStanza = R.drawable.num_6_red;
                    }
                break;
            }

            // PER ORA ELIMINO LA STANZA "2" PERCHE' NON ESISTE ESSENDO LA SALA COLAZIONE
            if (indice != StanzeConstants.SALA_COLAZIONE) {
                stanze[indice] = new Stanza(idImgNumeroStanza,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature,
                        R.drawable.humidity);
            }
        }

        ArrayList<Stanza> listaStanze = new ArrayList<Stanza>();

        for (int indice = 0; indice < stanze.length; ++indice) {
            // PER ORA ELIMINO LA STANZA "2" PERCHE' NON ESISTE ESSENDO LA SALA COLAZIONE
            if (indice != StanzeConstants.SALA_COLAZIONE) {
                listaStanze.add(stanze[indice]);
            }
        }

        return(listaStanze);
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    private static String getTempHumid(char type, InternetUtils iu, String rootServer) {
        String result = null;
        String response = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            if (type == 'T') {
    //            iu.getRestResponse(rootServer + RestConstants.TEMPERATURE_READ);
                response = iu.getRestResponse(rootServer + RestConstants.TEMPERATURE);
            } else if (type == 'H') {
    //            iu.getRestResponse(rootServer + RestConstants.HUMIDITY_READ);
                response = iu.getRestResponse(rootServer + RestConstants.HUMIDITY);
            }

            JSONObject reader = new JSONObject(response);

            if (type == 'T') {
                result = reader.getString(JSONConstants.TEMPERATURE);
            } else if (type == 'H') {
                result = reader.getString(JSONConstants.HUMIDITY);
            }

            if (result == null || result.equals("0")) {
                result = JSONConstants.EMPTY_OR_NULL_DATA;
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
            result = JSONConstants.EMPTY_OR_NULL_DATA;
        }

        return (result);
    }
}
