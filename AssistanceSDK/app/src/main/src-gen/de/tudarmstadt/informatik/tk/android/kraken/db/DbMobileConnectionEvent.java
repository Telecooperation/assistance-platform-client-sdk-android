package de.tudarmstadt.informatik.tk.android.kraken.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "mobile_connection_event".
 */
public class DbMobileConnectionEvent implements de.tudarmstadt.informatik.tk.android.kraken.interfaces.IDbSensor {

    private Long id;
    private String carrierName;
    private String mobileCarrierCode;
    private String mobileNetworkCode;
    /** Not-null value. */
    private String created;
    private Boolean voipAvailable;

    public DbMobileConnectionEvent() {
    }

    public DbMobileConnectionEvent(Long id) {
        this.id = id;
    }

    public DbMobileConnectionEvent(Long id, String carrierName, String mobileCarrierCode, String mobileNetworkCode, String created, Boolean voipAvailable) {
        this.id = id;
        this.carrierName = carrierName;
        this.mobileCarrierCode = mobileCarrierCode;
        this.mobileNetworkCode = mobileNetworkCode;
        this.created = created;
        this.voipAvailable = voipAvailable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    public Boolean getVoipAvailable() {
        return voipAvailable;
    }

    public void setVoipAvailable(Boolean voipAvailable) {
        this.voipAvailable = voipAvailable;
    }

}