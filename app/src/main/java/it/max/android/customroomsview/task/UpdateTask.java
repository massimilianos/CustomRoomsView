package it.max.android.customroomsview.task;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.ArrayList;

import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.adapters.StanzaAdapter;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;
import it.max.android.customroomsview.interfaces.AsyncResponse;
import it.max.android.customroomsview.model.Stanza;

public class UpdateTask extends AsyncTask<Void, String, ArrayList<Stanza>> {
    private Context context;
    private ListaStanzeFragment listaStanzeFragment;

    public AsyncResponse delegate = null;

    public UpdateTask (Context context, ListaStanzeFragment listaStanzeFragment) {
        this.context = context;
        this.listaStanzeFragment = listaStanzeFragment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected ArrayList<Stanza> doInBackground(Void... params) {
        DHT11Data dht11Data = new DHT11Data();

        ArrayList<Stanza> listaStanze = dht11Data.creaDatiListaStanze(context);

        return listaStanze;
    }

    @Override
    protected void onPostExecute(ArrayList<Stanza> result) {
        super.onPostExecute(result);

        delegate.processFinish(result);
    }
}
