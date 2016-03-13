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
import it.max.android.customroomsview.model.DHT11Data;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.utils.InternetUtils;

public class ListaStanzeFragment extends ListFragment {
    private SimpleAdapter adapterListaStanzeView;

    public void createDataListaStanzeView() {
        Activity currentActivity = getActivity();

        DHT11Data dht11Data = DHT11Data.creaDatiListViewStanze();

        adapterListaStanzeView = new SimpleAdapter (
                currentActivity,
                dht11Data.getData(),
                R.layout.lista_stanze_data,
                dht11Data.getFrom(),
                dht11Data.getTo());

        setListAdapter(adapterListaStanzeView);
    }

    public void refreshListaStanzeView() {
        createDataListaStanzeView();

        adapterListaStanzeView.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        createDataListaStanzeView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_stanze_fragment, container, false);
        Context contextView = view.getContext();

        ListView listaStanzeView = ((ListView)view.findViewById(R.id.lista_stanze_view));
        Context contextListaStanzeView = listaStanzeView.getContext();

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
