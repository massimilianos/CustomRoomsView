package it.max.android.customroomsview.task;

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
            Thread.sleep(4000);

            return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void nope) {
        Toast.makeText(mCon, "Aggiornamento completato.", Toast.LENGTH_LONG).show();

        ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) getFragmentManager()
                .findFragmentById(R.id.lista_stanze_fragment);
        listaStanzeFragment.refreshListaStanzeView();

        ((MainActivity) mCon).resetUpdating();
    }
}
