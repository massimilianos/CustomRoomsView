package it.max.android.customroomsview.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.adapters.StanzaAdapter;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.DHT11;
import it.max.android.customroomsview.model.Stanza;

public class ListaStanzeFragment extends ListFragment {
    private List<Stanza> listaStanze;
    private StanzaAdapter stanzaAdapter;

    public StanzaAdapter createDataListaStanzeView() {
        DHT11Data dht11Data = new DHT11Data();
        stanzaAdapter = new StanzaAdapter(getActivity(), dht11Data.creaDatiListaStanze());

        return(stanzaAdapter);
    }

    public void refreshListaStanzeView(Context context) {
        DHT11Data dht11Data = new DHT11Data();
        stanzaAdapter = new StanzaAdapter(getActivity(), dht11Data.creaDatiListaStanze());
        stanzaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DHT11Data dht11Data = new DHT11Data();
        listaStanze = dht11Data.creaDatiListaStanze();

        setListAdapter(new StanzaAdapter(getActivity(), listaStanze));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);
    }
}
