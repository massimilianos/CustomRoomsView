package it.max.android.customroomsview.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.adapters.SpecialAdapter;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.DHT11;

public class ListaStanzeFragment extends ListFragment {
    private ListView listaStanzeView;
    private SpecialAdapter adapterListaStanzeView;

    public SpecialAdapter createDataListaStanzeView() {
        Activity currentActivity = getActivity();

        DHT11Data dht11Data = new DHT11Data();

        DHT11 dht11 = dht11Data.creaDatiListViewStanze();

        return(adapterListaStanzeView = new SpecialAdapter( currentActivity,
                                                            dht11.getData(),
                                                            R.layout.lista_stanze_data,
                                                            dht11.getFrom(),
                                                            dht11.getTo()));
    }

    public void refreshListaStanzeView() {
//        listaStanzeView.removeAllViews();

        this.adapterListaStanzeView = createDataListaStanzeView();
        this.adapterListaStanzeView.notifyDataSetChanged();
//        ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) getFragmentManager().findFragmentById(R.id.lista_stanze_fragment);
//        ((SpecialAdapter)listaStanzeFragment.getListView().getAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        adapterListaStanzeView = createDataListaStanzeView();
        setListAdapter(adapterListaStanzeView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lista_stanze_fragment, container, false);

        listaStanzeView = (ListView)view.findViewById(R.id.lista_stanze_view);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
