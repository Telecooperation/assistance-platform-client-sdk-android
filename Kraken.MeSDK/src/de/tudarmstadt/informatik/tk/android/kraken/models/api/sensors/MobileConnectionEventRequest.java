package de.tudarmstadt.informatik.tk.android.kraken.models.api.sensors;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import de.tudarmstadt.informatik.tk.android.kraken.interfaces.Sensor;

/**
 * @author Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 30.08.2015
 */
public class MobileConnectionEventRequest implements Sensor {

    private long id;

    @SerializedName("carrierName")
    @Expose
    private String carrierName;

    @SerializedName("mobileCarrierCode")
    @Expose
    private String mobileCarrierCode;

    @SerializedName("mobileNetworkCode")
    @Expose
    private String mobileNetworkCode;
    /**
     * Not-null value.
     */
    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("voipAvailable")
    @Expose
    private Boolean voipAvailable;

    private int type;

    public MobileConnectionEventRequest() {
    }

    public MobileConnectionEventRequest(long id) {
        this.id = id;
    }

    public MobileConnectionEventRequest(long id, String carrierName, String mobileCarrierCode, String mobileNetworkCode, String created, Boolean voipAvailable) {
        this.id = id;
        this.carrierName = carrierName;
        this.mobileCarrierCode = mobileCarrierCode;
        this.mobileNetworkCode = mobileNetworkCode;
        this.created = created;
        this.voipAvailable = voipAvailable;
        this.type = SensorType.MOBILE_DATA_CONNECTION_EVENT;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getMobileCarrierCode() {
        return mobileCarrierCode;
    }

    public void setMobileCarrierCode(String mobileCarrierCode) {
        this.mobileCarrierCode = mobileCarrierCode;
    }

    public String getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(String mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    /**
     * Not-null value.
     */
    public String getCreated() {
        return created;
    }

    /**
     * Not-null value; ensure this value is available before it is saved to the database.
     */
    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getVoipAvailable() {
        return voipAvailable;
    }

    public void setVoipAvailable(Boolean voipAvailable) {
        this.voipAvailable = voipAvailable;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MobileConnectionEventRequest{" +
                "id=" + id +
                ", carrierName='" + carrierName + '\'' +
                ", mobileCarrierCode='" + mobileCarrierCode + '\'' +
                ", mobileNetworkCode='" + mobileNetworkCode + '\'' +
                ", created='" + created + '\'' +
                ", voipAvailable=" + voipAvailable +
                ", type=" + type +
                '}';
    }
}