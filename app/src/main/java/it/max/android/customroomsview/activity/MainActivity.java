package it.max.android.customroomsview.activity;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.io.InputStream;
import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class MainActivity extends ListActivity {
    private Context context = null;
    private Properties properties = null;

    @Override
    public boolean onCreateOptionsMenu(Menu mainMenu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, mainMenu);

        return super.onCreateOptionsMenu(mainMenu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgDomusAlberti = (ImageView)findViewById(R.id.imgDomusAlberti);
        imgDomusAlberti.setImageResource(R.drawable.logo_domus_alberti);

        ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) getFragmentManager().findFragmentById(R.id.lista_stanze_fragment);
    }
}
