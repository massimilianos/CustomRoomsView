package it.max.android.customroomsview.utils;

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

import it.max.android.customroomsview.properties.ServerProperties;

public class InternetUtils {
    private ServerProperties serverProperties;

    public InternetUtils(String address, String port) {
        serverProperties = new ServerProperties();
            serverProperties.setWebserverAddress(address);
            serverProperties.setWebserverPort(port);
    }

    public String creaURLServer() {
        String URLServer = "http://" + serverProperties.getWebserverAddress()
                              + ":"  + serverProperties.getWebserverPort();

        return(URLServer);
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
}
