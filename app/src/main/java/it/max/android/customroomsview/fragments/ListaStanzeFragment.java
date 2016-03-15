package it.max.android.customroomsview.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.DHT11;

public class ListaStanzeFragment extends ListFragment {
    private SimpleAdapter adapterListaStanzeView;

    public void createDataListaStanzeView() {
        Activity currentActivity = getActivity();

        DHT11Data dht11Data = new DHT11Data();

        DHT11 dht11 = dht11Data.creaDatiListViewStanze();

        adapterListaStanzeView = new SimpleAdapter (
                currentActivity,
                dht11.getData(),
                R.layout.lista_stanze_data,
                dht11.getFrom(),
                dht11.getTo());

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
