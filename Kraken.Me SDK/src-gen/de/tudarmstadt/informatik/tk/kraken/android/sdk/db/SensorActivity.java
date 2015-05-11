package de.tudarmstadt.informatik.tk.kraken.android.sdk.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table SENSOR_ACTIVITY.
 */
public class SensorActivity implements de.tudarmstadt.informatik.tk.kraken.android.sdk.interfaces.IDbSensor {

    private Long id;
    private Integer type;
    private Integer confidence;
    private Integer ranking;
    private Long timestamp;

    public SensorActivity() {
    }

    public SensorActivity(Long id) {
        this.id = id;
    }

    public SensorActivity(Long id, Integer type, Integer confidence, Integer ranking, Long timestamp) {
        this.id = id;
        this.type = type;
        this.confidence = confidence;
        this.ranking = ranking;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getConfidence() {
        return confidence;
    }

    public void setConfidence(Integer confidence) {
        this.confidence = confidence;
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

}
