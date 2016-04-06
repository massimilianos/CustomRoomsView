package it.max.android.customroomsview.activity;

import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;

import android.os.Bundle;

import android.support.v4.app.FragmentActivity;
import android.view.Menu;

import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class MainActivity extends FragmentActivity {
    private Context context;

    private static Menu mymenu;

    public Context getMainActivityContext() {
        return this.context;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu mainMenu) {
        getMenuInflater().inflate(R.menu.main_menu, mainMenu);

        mymenu = mainMenu;

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            case R.id.action_refresh:
                FragmentManager fragmentManager = this.getFragmentManager();

                ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) fragmentManager.findFragmentById(R.id.lista_stanze_fragment);
                listaStanzeFragment.refreshListaStanzeView();
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

    public static void setRefreshActionButtonState(final boolean refreshing) {
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
