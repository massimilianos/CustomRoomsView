package it.max.android.customroomsview.task;

import android.app.FragmentManager;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.activity.MainActivity;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class UpdateTask extends AsyncTask<Void, Void, Void> {
    private Context context;

    public UpdateTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... nope) {
        try {
            FragmentManager fragmentManager = ((MainActivity) context).getFragmentManager();

            ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) fragmentManager.findFragmentById(R.id.lista_stanze_fragment);
            listaStanzeFragment.refreshListaStanzeView();

            //TODO: Togliere in PRODUZIONE :P
            //Thread.sleep(2000);

            return null;
        } catch (Exception e) {
            Log.e("doInBackground", e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(Void nope) {
        Toast.makeText(context, "Aggiornamento completato.", Toast.LENGTH_SHORT).show();

        ((MainActivity) context).setRefreshActionButtonState(false);
//        ((MainActivity) context).resetUpdating();
    }
}
