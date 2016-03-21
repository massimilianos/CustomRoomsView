package it.max.android.customroomsview.task;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class UpdateTask extends AsyncTask<Void, Void, Void> {
    private Context mCon;

    public UpdateTask(Context con) {
        mCon = con;
    }

    @Override
    protected Void doInBackground(Void... nope) {
        try {
            FragmentManager fragmentManager = ((MainActivity) mCon).getFragmentManager();

            ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) fragmentManager.findFragmentById(R.id.lista_stanze_fragment);
            listaStanzeFragment.refreshListaStanzeView();

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void nope) {
        Toast.makeText(mCon, "Aggiornamento completato.", Toast.LENGTH_LONG).show();

        ((MainActivity) mCon).resetUpdating();
    }
}
