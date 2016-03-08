package it.max.android.customroomsview.activity;

import android.app.ListActivity;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;
import java.util.Properties;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.fragments.ListaStanzeFragment;

public class MainActivity extends ListActivity {
    private Context context = null;
    private Properties properties = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgDomusAlberti = (ImageView)findViewById(R.id.imgDomusAlberti);
        imgDomusAlberti.setImageResource(R.drawable.logo_domus_alberti);

        ListaStanzeFragment listaStanzeFragment = (ListaStanzeFragment) getFragmentManager().findFragmentById(R.id.lista_stanze_fragment);
    }
}
