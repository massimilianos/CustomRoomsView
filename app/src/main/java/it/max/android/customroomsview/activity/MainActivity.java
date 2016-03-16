package it.max.android.customroomsview.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;

import android.os.Bundle;

import android.view.Menu;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class MainActivity extends ListActivity {
    private Context context = null;
    private Properties properties = null;

    private MenuItem menuItem;

    @Override
    public boolean onCreateOptionsMenu(Menu mainMenu) {
        getMenuInflater().inflate(R.menu.main_menu, mainMenu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        Context context = getApplicationContext();

        switch(itemId) {
            case R.id.action_refresh:
                Toast.makeText(context, "Aggiornamento in corso...", Toast.LENGTH_LONG).show();

                ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) getFragmentManager()
                                                                                .findFragmentById(R.id.lista_stanze_fragment);
                listaStanzeFragment.refreshListaStanzeView();

                Toast.makeText(context, "Aggiornamento completato.", Toast.LENGTH_LONG).show();
            break;
            case R.id.menu_options:
                Toast.makeText(context, "Apertura opzioni...", Toast.LENGTH_LONG).show();
            break;
            default:
                Toast.makeText(context, "Default Action", Toast.LENGTH_LONG).show();
            break;
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageView imgDomusAlberti = (ImageView)findViewById(R.id.imgDomusAlberti);
        imgDomusAlberti.setImageResource(R.drawable.logo_domus_alberti);
    }
}
