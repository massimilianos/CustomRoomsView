package it.max.android.customroomsview.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.data.DHT11Data;
import it.max.android.customroomsview.model.Stanza;

public class StanzaAdapter extends ArrayAdapter<Stanza> {
    List<Stanza> listaStanze;

    public StanzaAdapter(Context context, List<Stanza> listaStanze) {
        super(context, R.layout.lista_stanze_data, listaStanze);

        this.listaStanze = listaStanze;
    }

    public void setListaStanze(List<Stanza> listaStanze) {
        this.listaStanze = listaStanze;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        StanzaHolder stanzaHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.lista_stanze_data, parent, false);

            stanzaHolder = new StanzaHolder();
            stanzaHolder.imgStanza = (ImageView) convertView.findViewById(R.id.imgStanza);
            stanzaHolder.temperatura = (TextView) convertView.findViewById(R.id.temperature);
            stanzaHolder.imgTemperatura = (ImageView) convertView.findViewById(R.id.imgTemperature);
            stanzaHolder.umidita = (TextView) convertView.findViewById(R.id.humidity);
            stanzaHolder.imgUmidita = (ImageView) convertView.findViewById(R.id.imgHumidity);

            convertView.setTag(stanzaHolder);
        } else {
            stanzaHolder = (StanzaHolder)convertView.getTag();
        }

        Stanza stanza = getItem(position);

        if (stanza != null) {
            stanzaHolder.imgStanza.setImageResource(stanza.getImgStanza());
            stanzaHolder.temperatura.setText("" + stanza.getTemperatura() + "°");
            stanzaHolder.imgTemperatura.setImageResource(stanza.getImgTemperatura());
            stanzaHolder.umidita.setText("" + stanza.getUmidita() + "%");
            stanzaHolder.imgUmidita.setImageResource(stanza.getImgUmidita());
        }

        DHT11Data dht11Data = new DHT11Data();
        listaStanze = dht11Data.creaDatiListaStanze();

        notifyDataSetChanged();

        return convertView;
    }

    private static class StanzaHolder {
        public ImageView imgStanza;
        public TextView temperatura;
        public ImageView imgTemperatura;
        public TextView umidita;
        public ImageView imgUmidita;
    }
}
