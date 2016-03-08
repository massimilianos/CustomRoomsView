package it.max.android.customroomsview.utils;

import android.os.StrictMode;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import it.max.android.customroomsview.constants.ArduinoServerConstants;
import it.max.android.customroomsview.constants.RestConstants;
import it.max.android.customroomsview.properties.ArduinoServerProperties;
import it.max.android.customroomsview.properties.WebServerProperties;

public class InternetUtils {
    private WebServerProperties webServerProperties;
    private ArduinoServerProperties arduinoServerProperties;

    public InternetUtils(char type, String address, String port) {
        if (type == 'W') {
            webServerProperties = new WebServerProperties();
            webServerProperties.setWebserverAddress(address);
            webServerProperties.setWebserverPort(port);
        } else if (type == 'A') {
            arduinoServerProperties = new ArduinoServerProperties();
            arduinoServerProperties.setArduinoAddress(address);
            arduinoServerProperties.setArduinoPort(port);
        }
    }

    public String creaURLWebServer() {
        String URLWebServer = "http://" + webServerProperties.getWebserverAddress()
                              + ":"     + webServerProperties.getWebserverPort();

        return(URLWebServer);
    }

    public String creaURLArduinoServer() {
        String URLArduinoServer = "http://" + arduinoServerProperties.getArduinoAddress()
                                  + ":"     + arduinoServerProperties.getArduinoPort()
                                  + "/index.htm?";

        return(URLArduinoServer);
    }

    private String readResponse (InputStream response) {
        BufferedReader r = new BufferedReader(new InputStreamReader(response));
        StringBuilder total = new StringBuilder();
        String line;

        try {
            while ((line = r.readLine()) != null) {
                total.append(line);
            }
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("READRESPONSE: 'ERRORE'");
        }

        return(total.toString());
    }

    public String getResponse (String url) {
        String response = new String();
        try {
            HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.connect();
            InputStream is = con.getInputStream();
            response = this.readResponse(is);
            con.disconnect();
        } catch(Exception e) {
            e.printStackTrace();
            System.out.println("GETRESPONSE: 'ERRORE'");
        }

        return(response);
    }

    public String getRestResponse(String url) {
        HttpClient httpclient = new DefaultHttpClient();
        String result = null;
        HttpGet httpget = new HttpGet(url);
        HttpResponse response = null;
        InputStream instream = null;

        try {
            response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                instream = entity.getContent();
                result = convertStreamToString(instream);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (instream != null) {
                try {
                    instream.close();
                } catch (Exception exc) {

                }
            }
        }

        return result;
    }

    public String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }

        return sb.toString();
    }

    public Float getTempHumid(char type, InternetUtils iu, String rootServer, String room) {
        Float result = null;
        String response = null;

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        if (type == 'T') {
            response = iu.getRestResponse(rootServer + RestConstants.TEMPERATURE);
        } else if (type == 'H') {
            response = iu.getRestResponse(rootServer + RestConstants.HUMIDITY);
        }

        result = (response != null && !response.isEmpty()) ? Float.parseFloat(response) : null;

        return (result);
    }
}
