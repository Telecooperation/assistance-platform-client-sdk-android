package de.tudarmstadt.informatik.tk.kraken.android.sdk.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SENSOR_CALL_LOG.
 */
public class SensorCallLog implements de.tudarmstadt.informatik.tk.kraken.android.sdk.interfaces.IDbUpdatableSensor {

    private Long id;
    private Long callId;
    private Integer type;
    private String name;
    private String number;
    private Long date;
    private Long duration;
    private Long timestamp;
    private Boolean isNew;
    private Boolean isUpdated;
    private Boolean isDeleted;

    public SensorCallLog() {
    }

    public SensorCallLog(Long id) {
        this.id = id;
    }

    public SensorCallLog(Long id, Long callId, Integer type, String name, String number, Long date, Long duration, Long timestamp, Boolean isNew, Boolean isUpdated, Boolean isDeleted) {
        this.id = id;
        this.callId = callId;
        this.type = type;
        this.name = name;
        this.number = number;
        this.date = date;
        this.duration = duration;
        this.timestamp = timestamp;
        this.isNew = isNew;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCallId() {
        return callId;
    }

    public void setCallId(Long callId) {
        this.callId = callId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getIsNew() {
        return isNew;
    }

    public void setIsNew(Boolean isNew) {
        this.isNew = isNew;
    }

    public Boolean getIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(Boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

}
