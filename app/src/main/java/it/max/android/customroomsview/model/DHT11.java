package it.max.android.customroomsview.model;

import java.util.ArrayList;
import java.util.HashMap;

public class DHT11 {
    private String[] from;
    private int[] to;
    private ArrayList<HashMap<String, Object>> data;

    public String[] getFrom() {
        return from;
    }

    public void setFrom(String[] from) {
        this.from = from;
    }

    public int[] getTo() {
        return to;
    }

    public void setTo(int[] to) {
        this.to = to;
    }

    public ArrayList<HashMap<String, Object>> getData() {
        return data;
    }

    public void setData(ArrayList<HashMap<String, Object>> data) {
        this.data = data;
    }

    public DHT11() {}
}
