package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "power_state_event".
 */
public class DbPowerStateEvent implements de.tudarmstadt.informatik.tk.android.assistance.sdk.interfaces.IDbSensor {

    private Long id;
    private Integer state;
    private Integer chargingStatus;
    private Float percent;
    /** Not-null value. */
    private String created;

    public DbPowerStateEvent() {
    }

    public DbPowerStateEvent(Long id) {
        this.id = id;
    }

    public DbPowerStateEvent(Long id, Integer state, Integer chargingStatus, Float percent, String created) {
        this.id = id;
        this.state = state;
        this.chargingStatus = chargingStatus;
        this.percent = percent;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getChargingStatus() {
        return chargingStatus;
    }

    public void setChargingStatus(Integer chargingStatus) {
        this.chargingStatus = chargingStatus;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

}