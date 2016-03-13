package it.max.android.customroomsview.model;

import android.content.Context;

import java.util.ArrayList;
import java.util.HashMap;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.constants.WebServerConstants;
import it.max.android.customroomsview.utils.InternetUtils;

public class DHT11Data {
    private String[] from;
    private int[] to;
    private ArrayList<HashMap<String, Object>> data;

    public String[] getFrom() {
        return from;
    }

    public void setFrom(String[] from) {
        this.from = from;
    }

    public int[] getTo() {
        return to;
    }

    public void setTo(int[] to) {
        this.to = to;
    }

    public ArrayList<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(ArrayList<HashMap<String, Object>> data) {
        this.data = data;
    }

    public DHT11Data() {}

    public static DHT11Data creaDatiListViewStanze() {
        DHT11Data dht11Data = new DHT11Data();

        String webserverAddress = WebServerConstants.WEB_SERVER_ADDRESS_CASA;
        String webserverPort = WebServerConstants.WEB_SERVER_PORT_CASA;

        InternetUtils iu = new InternetUtils('W', webserverAddress, webserverPort);
        String rootWebServer = iu.creaURLWebServer();

        Stanza[] stanze = {
                new Stanza("Ufficio", R.drawable.num_0, iu.getTempHumid('T', iu, rootWebServer, "office"), iu.getTempHumid('H', iu, rootWebServer, "office"), R.drawable.temperature, R.drawable.humidity),
                new Stanza("Uno", R.drawable.num_1, iu.getTempHumid('T', iu, rootWebServer, "room1"), iu.getTempHumid('H', iu, rootWebServer, "room1"), R.drawable.temperature, R.drawable.humidity),
                new Stanza("Tre", R.drawable.num_3, iu.getTempHumid('T', iu, rootWebServer, "room3"), iu.getTempHumid('H', iu, rootWebServer, "room3"), R.drawable.temperature, R.drawable.humidity),
                new Stanza("Quattro", R.drawable.num_4, iu.getTempHumid('T', iu, rootWebServer, "room4"), iu.getTempHumid('H', iu, rootWebServer, "room4"), R.drawable.temperature, R.drawable.humidity),
                new Stanza("Cinque", R.drawable.num_5, iu.getTempHumid('T', iu, rootWebServer, "room5"), iu.getTempHumid('H', iu, rootWebServer, "room5"), R.drawable.temperature, R.drawable.humidity),
                new Stanza("Sei", R.drawable.num_6, iu.getTempHumid('T', iu, rootWebServer, "room6"), iu.getTempHumid('H', iu, rootWebServer, "room6"), R.drawable.temperature, R.drawable.humidity)
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

        dht11Data.setData(data);

        String[] from = { /*"nomeStanza", */"imgStanza", "temperatura","imgTemperatura","umidita","imgUmidita" };
        dht11Data.setFrom(from);

        int[] to = {/*R.id.nomeStanza, */R.id.imgStanza, R.id.temperature, R.id.imgTemperature, R.id.humidity, R.id.imgHumidity};
        dht11Data.setTo(to);

        return(dht11Data);
    }
}
