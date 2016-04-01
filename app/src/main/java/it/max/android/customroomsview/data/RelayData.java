package it.max.android.customroomsview.data;

import android.os.StrictMode;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import it.max.android.customroomsview.R;
import it.max.android.customroomsview.constants.JSONConstants;
import it.max.android.customroomsview.constants.RestConstants;
import it.max.android.customroomsview.constants.ServerConnectionConstants;
import it.max.android.customroomsview.model.DHT11;
import it.max.android.customroomsview.model.Stanza;
import it.max.android.customroomsview.utils.InternetUtils;

public class RelayData {
    private static String serverAddress = ServerConnectionConstants.SERVER_ADDRESS;
    private static String serverPort = ServerConnectionConstants.SERVER_PORT;

    private static InternetUtils iu = new InternetUtils(serverAddress, serverPort);
    private static String rootServer = iu.creaURLServer();

    public RelayData() {}

    public Integer getRelayState(InternetUtils iu, String rootServer, Integer relayNumber) {
        String  url = null;
        Integer result = null;
        String  response = null;
        String  relayState = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            JSONObject reader = null;

            relayState = "relay" + relayNumber + "State";
            url = rootServer + "/" + relayState;
            response = iu.getRestResponse(url);
            reader = new JSONObject(response);
            result = Integer.valueOf(reader.getString(relayState));
/*
            switch(relayNumber) {
                case 1:
                    response = iu.getRestResponse(rootServer + RestConstants.RELAY1_STATE);
                    reader = new JSONObject(response);
                    result = Integer.valueOf(reader.getString(JSONConstants.RELAY1_STATE));
                break;

                case 3:
                    response = iu.getRestResponse(rootServer + RestConstants.RELAY3_STATE);
                    reader = new JSONObject(response);
                    result = Integer.valueOf(reader.getString(JSONConstants.RELAY3_STATE));
                break;

                case 4:
                    response = iu.getRestResponse(rootServer + RestConstants.RELAY4_STATE);
                    reader = new JSONObject(response);
                    result = Integer.valueOf(reader.getString(JSONConstants.RELAY4_STATE));
                break;

                case 5:
                    response = iu.getRestResponse(rootServer + RestConstants.RELAY3_STATE);
                    reader = new JSONObject(response);
                    result = Integer.valueOf(reader.getString(JSONConstants.RELAY3_STATE));
                break;

                case 6:
                    response = iu.getRestResponse(rootServer + RestConstants.RELAY1_STATE);
                    reader = new JSONObject(response);
                    result = Integer.valueOf(reader.getString(JSONConstants.RELAY1_STATE));
                break;
            }
*/
        } catch(Exception e) {
            System.out.println(e.getMessage());
            result = null;
        }

        return (result);
    }
}
