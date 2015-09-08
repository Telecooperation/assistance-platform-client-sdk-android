package de.tudarmstadt.informatik.tk.android.kraken.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "wifi_connection_event".
 */
public class WifiConnectionEvent implements de.tudarmstadt.informatik.tk.android.kraken.interfaces.IDbSensor {

    private long id;
    private String ssid;
    private String bssid;
    /** Not-null value. */
    private String created;
    private Integer channel;
    private Integer frequency;
    private Integer linkSpeed;
    private Integer signalStrength;
    private Integer networkId;

    public WifiConnectionEvent() {
    }

    public WifiConnectionEvent(long id) {
        this.id = id;
    }

    public WifiConnectionEvent(long id, String ssid, String bssid, String created, Integer channel, Integer frequency, Integer linkSpeed, Integer signalStrength, Integer networkId) {
        this.id = id;
        this.ssid = ssid;
        this.bssid = bssid;
        this.created = created;
        this.channel = channel;
        this.frequency = frequency;
        this.linkSpeed = linkSpeed;
        this.signalStrength = signalStrength;
        this.networkId = networkId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getBssid() {
        return bssid;
    }

    public void setBssid(String bssid) {
        this.bssid = bssid;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getLinkSpeed() {
        return linkSpeed;
    }

    public void setLinkSpeed(Integer linkSpeed) {
        this.linkSpeed = linkSpeed;
    }

    public Integer getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(Integer signalStrength) {
        this.signalStrength = signalStrength;
    }

    public Integer getNetworkId() {
        return networkId;
    }

    public void setNetworkId(Integer networkId) {
        this.networkId = networkId;
    }

}