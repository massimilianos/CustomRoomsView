package it.max.android.customroomsview.properties;

public class ServerProperties {
    private String webserverAddress = null;
    private String webserverPort = null;
    private String webserverSitePath = null;

    public String getWebserverAddress() {
        return webserverAddress;
    }

    public void setWebserverAddress(String webserverAddress) {
        this.webserverAddress = webserverAddress;
    }

    public String getWebserverPort() {
        return webserverPort;
    }

    public void setWebserverPort(String webserverPort) {
        this.webserverPort = webserverPort;
    }

    public String getWebserverSitePath() {
        return webserverSitePath;
    }

    public void setWebserverSitePath(String webserverSitePath) {
        this.webserverSitePath = webserverSitePath;
    }
}
