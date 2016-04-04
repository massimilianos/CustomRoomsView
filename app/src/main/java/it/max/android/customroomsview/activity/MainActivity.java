package it.max.android.customroomsview.activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;
import it.max.android.customroomsview.task.UpdateTask;

public class MainActivity extends FragmentActivity {
    private static Context context;

    private Properties properties = null;

    private Menu mymenu;
    private MenuItem menuItem;

    private Thread thread;

    public static Context getLastSetContext() {
        return context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mainMenu) {
        getMenuInflater().inflate(R.menu.main_menu, mainMenu);

        mymenu = mainMenu;

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        context = getApplicationContext();

        switch(itemId) {
            case R.id.action_refresh:
                Toast.makeText(context, "Aggiornamento in corso...", Toast.LENGTH_SHORT).show();

                setRefreshActionButtonState(true);

                thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            synchronized(this){
                                wait(3000);
                            }
                        }
                        catch(InterruptedException ex){
                        }

                        FragmentManager fragmentManager = ((FragmentActivity)MainActivity.getLastSetContext()).getFragmentManager();

                        ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) fragmentManager.findFragmentById(R.id.lista_stanze_fragment);
                        listaStanzeFragment.refreshListaStanzeView();

                        setRefreshActionButtonState(false);
                    }
                };

                thread.start();
            break;
            case R.id.menu_options:
                Toast.makeText(context, "Apertura opzioni...", Toast.LENGTH_SHORT).show();
            break;
            default:
                Toast.makeText(context, "Default Action", Toast.LENGTH_SHORT).show();
            break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setRefreshActionButtonState(final boolean refreshing) {
        if (mymenu != null) {
            final MenuItem refreshItem = mymenu.findItem(R.id.action_refresh);
            if (refreshItem != null) {
                if (refreshing) {
                    refreshItem.setActionView(R.layout.actionbar_indeterminate_progress);
                } else {
                    refreshItem.setActionView(null);
                }
            }
        }
    }

    public void resetUpdating() {
        MenuItem m = mymenu.findItem(R.id.action_refresh);
        if(m.getActionView() != null) {
            m.getActionView().clearAnimation();
            m.setActionView(null);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageView imgDomusAlberti = (ImageView)findViewById(R.id.imgDomusAlberti);
        imgDomusAlberti.setImageResource(R.drawable.logo_domus_alberti);
    }
}
