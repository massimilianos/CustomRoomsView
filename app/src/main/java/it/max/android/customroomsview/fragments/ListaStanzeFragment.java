package it.max.android.customroomsview.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.adapters.StanzaAdapter;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.interfaces.AsyncResponse;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.task.UpdateTask;

public class ListaStanzeFragment extends ListFragment implements AsyncResponse {
    private ArrayList<Stanza> listaStanze;

    private StanzaAdapter stanzaAdapter = null;

    public void refreshListaStanzeView() {
        MainActivity.setRefreshActionButtonState(true);
//        Toast.makeText(getActivity(), "Aggiornamento in corso...", Toast.LENGTH_SHORT).show();

//        stanzaAdapter.clear();

        UpdateTask asyncTask = new UpdateTask(getActivity(), this);
        asyncTask.delegate = this;
        asyncTask.execute();

        stanzaAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DHT11Data dht11Data = new DHT11Data();
        listaStanze = dht11Data.creaDatiListaStanze(this.getActivity());

        stanzaAdapter = new StanzaAdapter(getActivity(), listaStanze);
        setListAdapter(stanzaAdapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setDivider(null);
    }

    @Override
    public void processFinish(ArrayList<Stanza> output) {
        StanzaAdapter stanzaAdapter = new StanzaAdapter(this.getActivity(), output);

        setListAdapter(stanzaAdapter);

        MainActivity.setRefreshActionButtonState(false);
//        Toast.makeText(this.getActivity(), "Aggiornamento completato.", Toast.LENGTH_SHORT).show();
    }
}
