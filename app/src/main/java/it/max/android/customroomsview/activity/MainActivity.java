package it.max.android.customroomsview.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.model.Stanza;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Stanza[] stanze = {
                new Stanza("Ufficio", R.drawable.num_0, 22, 33, R.drawable.temperature, R.drawable.humidity),
                new Stanza("Uno", R.drawable.num_1, 21, 34, R.drawable.temperature, R.drawable.humidity),
                new Stanza("Tre", R.drawable.num_3, 20, 35, R.drawable.temperature, R.drawable.humidity),
                new Stanza("Quattro", R.drawable.num_4, 23, 32, R.drawable.temperature, R.drawable.humidity),
                new Stanza("Cinque", R.drawable.num_5, 24, 31, R.drawable.temperature, R.drawable.humidity),
                new Stanza("Sei", R.drawable.num_6, 19, 30, R.drawable.temperature, R.drawable.humidity)
        };
        final ArrayList<Stanza> listaStanze = new ArrayList<Stanza>();
        for (int i = 0; i < stanze.length; ++i) {
            listaStanze.add(stanze[i]);
        }

        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();

        for(int i = 0; i < listaStanze.size(); i++) {
            Stanza s = listaStanze.get(i);

            HashMap<String,Object> stanzaMap = new HashMap<String, Object>();

//            stanzaMap.put("nomeStanza", s.getNomeStanza());
            stanzaMap.put("imgStanza", s.getImgStanza());
            stanzaMap.put("temperatura", s.getTemperatura() + "°");
            stanzaMap.put("imgTemperatura", s.getImgTemperatura());
            stanzaMap.put("umidita", s.getUmidita() + "%");
            stanzaMap.put("imgUmidita", s.getImgUmidita());
            data.add(stanzaMap);
        }

        String[] from = { /*"nomeStanza", */"imgStanza", "temperatura","imgTemperatura","umidita","imgUmidita" };
        int[] to = {/*R.id.nomeStanza, */R.id.imgStanza, R.id.temperature, R.id.imgTemperature, R.id.humidity, R.id.imgHumidity};

        SimpleAdapter adapter=new SimpleAdapter (
                this,
                data,
                R.layout.listastanze,
                from,
                to);

        ((ListView)findViewById(R.id.stanze)).setAdapter(adapter);
    }
}
