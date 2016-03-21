package it.max.android.customroomsview.fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.DHT11;

public class ListaStanzeFragment extends ListFragment {
    private ListView listaStanzeView;
    private LinearLayout layoutListaStanzeView;
    private SimpleAdapter adapterListaStanzeView;

    public void createDataListaStanzeView() {
        Activity currentActivity = getActivity();

        DHT11Data dht11Data = new DHT11Data();

        DHT11 dht11 = dht11Data.creaDatiListViewStanze();

//        adapterListaStanzeView = null;
        adapterListaStanzeView = new SimpleAdapter (currentActivity,
                                                    dht11.getData(),
                                                    R.layout.lista_stanze_data,
                                                    dht11.getFrom(),
                                                    dht11.getTo());

//        setListAdapter(adapterListaStanzeView);
    }

    public void refreshListaStanzeView() {
        createDataListaStanzeView();

//        layoutListaStanzeView.removeAllViews();
//        layoutListaStanzeView.addView(listaStanzeView);

        setListAdapter(adapterListaStanzeView);
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
        setListAdapter(adapterListaStanzeView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_stanze_fragment, container, false);
        Context contextView = view.getContext();

        listaStanzeView = ((ListView)view.findViewById(R.id.lista_stanze_view));
        Context contextListaStanzeView = listaStanzeView.getContext();

        layoutListaStanzeView = (LinearLayout) listaStanzeView.findViewById(R.id.layout_lista_stanze_view);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
