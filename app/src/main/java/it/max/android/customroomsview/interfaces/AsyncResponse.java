package it.max.android.customroomsview.interfaces;

import java.util.ArrayList;

import it.max.android.customroomsview.model.Stanza;

public interface AsyncResponse {
    void processFinish(ArrayList<Stanza> output);
}
