package it.max.android.customroomsview.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class SpecialAdapter extends SimpleAdapter {
    Context context;
    ArrayList<HashMap<String, Object>> items;
    int resource;
    String[] from;
    int[] to;

    public SpecialAdapter(Context context, ArrayList<HashMap<String, Object>> items, int resource, String[] from, int[] to) {
        super(context, items, resource, from, to);

        this.context = context;
        this.items = items;
        this.resource = resource;
        this.from = from;
        this.to = to;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
