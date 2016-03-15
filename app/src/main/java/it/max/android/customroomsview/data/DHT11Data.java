package it.max.android.customroomsview.data;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.constants.JSONConstants;
import it.max.android.customroomsview.constants.RestConstants;
import it.max.android.customroomsview.constants.ServerConnectionConstants;
import it.max.android.customroomsview.constants.StanzeConstants;
import it.max.android.customroomsview.model.DHT11;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.utils.InternetUtils;

public class DHT11Data {
    private static String serverAddress = ServerConnectionConstants.SERVER_ADDRESS_CASA;
    private static String serverPort = ServerConnectionConstants.SERVER_PORT_CASA;

    private static InternetUtils iu = new InternetUtils(serverAddress, serverPort);
    private static String rootServer = iu.creaURLServer();

    public DHT11Data() {}

    public DHT11 creaDatiListViewStanze() {
        DHT11 dht11 = new DHT11();

        RelayData relayData = new RelayData();

        Integer numeroStanze = StanzeConstants.NUMERO_STANZE;

        Integer[] statoStanze = new Integer[numeroStanze];
        for(int indice = 0; indice < numeroStanze; indice++) {
            Integer stato = relayData.getRelayState(iu, rootServer, getRandomNumberInRange(1, 4));

            statoStanze[indice] = R.drawable.num_1_green;
        }

        Stanza[] stanze = {
            new Stanza( R.drawable.num_0,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity),
            new Stanza( R.drawable.num_1_green,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity),
            new Stanza( R.drawable.num_3_green,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity),
            new Stanza( R.drawable.num_4_green,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity),
            new Stanza( R.drawable.num_5_green,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity),
            new Stanza( R.drawable.num_6_green,
                        getTempHumid('T', iu, rootServer),
                        getTempHumid('H', iu, rootServer),
                        R.drawable.temperature, R.drawable.humidity)
        };

        ArrayList<Stanza> listaStanze = new ArrayList<Stanza>();

        for (int i = 0; i < stanze.length; ++i) {
            listaStanze.add(stanze[i]);
        }

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();

        for(int i = 0; i < listaStanze.size(); i++) {
            Stanza s = listaStanze.get(i);

            HashMap<String, Object> stanzaMap = new HashMap<String, Object>();

//          stanzaMap.put("nomeStanza", s.getNomeStanza());
            stanzaMap.put("imgStanza", s.getImgStanza());
            stanzaMap.put("temperatura", s.getTemperatura() + "°");
            stanzaMap.put("imgTemperatura", s.getImgTemperatura());
            stanzaMap.put("umidita", s.getUmidita() + "%");
            stanzaMap.put("imgUmidita", s.getImgUmidita());
            data.add(stanzaMap);
        }

        dht11.setData(data);

        String[] from = { /*"nomeStanza", */"imgStanza", "temperatura","imgTemperatura","umidita","imgUmidita" };
        dht11.setFrom(from);

        int[] to = {/*R.id.nomeStanza, */R.id.imgStanza, R.id.temperature, R.id.imgTemperature, R.id.humidity, R.id.imgHumidity};
        dht11.setTo(to);

        return(dht11);
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

        if (type == 'T') {
            iu.getRestResponse(rootServer + RestConstants.TEMPERATURE_READ);
            response = iu.getRestResponse(rootServer + RestConstants.TEMPERATURE);
        } else if (type == 'H') {
            iu.getRestResponse(rootServer + RestConstants.HUMIDITY_READ);
            response = iu.getRestResponse(rootServer + RestConstants.HUMIDITY);
        }

        try {
            JSONObject reader = new JSONObject(response);

            if (type == 'T') {
                result = reader.getString(JSONConstants.TEMPERATURE);
            } else if (type == 'H') {
                result = reader.getString(JSONConstants.HUMIDITY);
            }

            if (result == null || result.equals("0")) {
                result = JSONConstants.EMPTY_OR_NULL_DATA;
            }
        } catch(JSONException je) {
            System.out.println(je.getMessage());
            result = JSONConstants.EMPTY_OR_NULL_DATA;
        }

        return (result);
    }
}
