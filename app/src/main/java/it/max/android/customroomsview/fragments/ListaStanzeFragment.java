package it.max.android.customroomsview.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.constants.WebServerConstants;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.utils.InternetUtils;

public class ListaStanzeFragment extends ListFragment {
    private Context contextListaStanzeView;
    private ArrayList<HashMap<String, Object>> data;
    private String[] from = { /*"nomeStanza", */"imgStanza", "temperatura","imgTemperatura","umidita","imgUmidita" };
    private int[] to = {/*R.id.nomeStanza, */R.id.imgStanza, R.id.temperature, R.id.imgTemperature, R.id.humidity, R.id.imgHumidity};
    private SimpleAdapter adapterListaStanzeView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        Activity currentActivity = getActivity();

        adapterListaStanzeView = new SimpleAdapter (
                currentActivity,
                data,
                R.layout.lista_stanze_data,
                from,
                to);

        setListAdapter(adapterListaStanzeView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_stanze_fragment, container, false);
        Context contextView = view.getContext();

        ListView listaStanzeView = ((ListView)view.findViewById(R.id.lista_stanze_view));
        contextListaStanzeView = listaStanzeView.getContext();

        Properties properties = null;
        ArrayList<Stanza> listaStanze = new ArrayList<Stanza>();

        try {
            Resources resources = this.getResources();
            AssetManager assetManager = resources.getAssets();

            InputStream inputStream = assetManager.open("domusalberti.properties");
            properties = new Properties();
            properties.load(inputStream);
        } catch(Exception e) {
            Toast.makeText(contextView, "ERRORE LETTURA FILE PROPERTIES (MAIN ACTIVITY)!!!", Toast.LENGTH_SHORT).show();
            System.exit(-1);
        }

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

        for (int i = 0; i < stanze.length; ++i) {
            listaStanze.add(stanze[i]);
        }

        data = new ArrayList<HashMap<String,Object>>();

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

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
