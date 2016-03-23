package it.max.android.customroomsview.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.ListActivity;
import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;

import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;
import it.max.android.customroomsview.task.UpdateTask;

public class MainActivity extends Activity {
    private Context context = null;
    private Properties properties = null;

    private Menu mymenu;
    private MenuItem menuItem;

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
                Toast.makeText(context, "Aggiornamento in corso...", Toast.LENGTH_LONG).show();
/*
                LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                ImageView iv = (ImageView)inflater.inflate(R.layout.iv_refresh, null);

                Animation rotation = AnimationUtils.loadAnimation(this, R.anim.rotate_refresh);
                rotation.setRepeatCount(Animation.INFINITE);

                iv.startAnimation(rotation);

                item.setActionView(iv);
*/
                new UpdateTask(this).execute();
            break;
            case R.id.menu_options:
                Toast.makeText(context, "Apertura opzioni...", Toast.LENGTH_LONG).show();
            break;
            default:
                Toast.makeText(context, "Default Action", Toast.LENGTH_LONG).show();
            break;
        }

        return super.onOptionsItemSelected(item);
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

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_HOME | ActionBar.DISPLAY_SHOW_TITLE | ActionBar.DISPLAY_SHOW_CUSTOM);

        ImageView imgDomusAlberti = (ImageView)findViewById(R.id.imgDomusAlberti);
        imgDomusAlberti.setImageResource(R.drawable.logo_domus_alberti);
    }
}
