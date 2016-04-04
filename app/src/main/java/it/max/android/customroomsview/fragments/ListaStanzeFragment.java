package it.max.android.customroomsview.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;

import java.util.Iterator;
import java.util.List;

import it.max.android.customroomsview.adapters.StanzaAdapter;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.Stanza;

public class ListaStanzeFragment extends ListFragment {
    private List<Stanza> listaStanze;
    private StanzaAdapter stanzaAdapter;

    public void refreshListaStanzeView() {
        DHT11Data dht11Data = new DHT11Data();

        stanzaAdapter.clear();

        Integer indice = 0;
        listaStanze = dht11Data.creaDatiListaStanze();
        Iterator<Stanza> itrStanze = listaStanze.iterator();
        while (itrStanze.hasNext()) {
            Stanza stanza = itrStanze.next();
            stanzaAdapter.add(stanza);
            indice++;
        }

        stanzaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DHT11Data dht11Data = new DHT11Data();
        listaStanze = dht11Data.creaDatiListaStanze();

        stanzaAdapter = new StanzaAdapter(getActivity(), listaStanze);
        setListAdapter(stanzaAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);
    }
}
