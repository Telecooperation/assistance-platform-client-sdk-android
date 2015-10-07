package de.tudarmstadt.informatik.tk.android.kraken.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "motion_activity_event".
 */
public class DbMotionActivityEvent implements de.tudarmstadt.informatik.tk.android.kraken.interfaces.IDbSensor {

    private Long id;
    private Integer walking;
    private Integer running;
    private Integer cycling;
    private Integer driving;
    private Integer stationary;
    private Integer unknown;
    /** Not-null value. */
    private String created;
    private Integer onFoot;
    private Integer tilting;

    public DbMotionActivityEvent() {
    }

    public DbMotionActivityEvent(Long id) {
        this.id = id;
    }

    public DbMotionActivityEvent(Long id, Integer walking, Integer running, Integer cycling, Integer driving, Integer stationary, Integer unknown, String created, Integer onFoot, Integer tilting) {
        this.id = id;
        this.walking = walking;
        this.running = running;
        this.cycling = cycling;
        this.driving = driving;
        this.stationary = stationary;
        this.unknown = unknown;
        this.created = created;
        this.onFoot = onFoot;
        this.tilting = tilting;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWalking() {
        return walking;
    }

    public void setWalking(Integer walking) {
        this.walking = walking;
    }

    public Integer getRunning() {
        return running;
    }

    public void setRunning(Integer running) {
        this.running = running;
    }

    public Integer getCycling() {
        return cycling;
    }

    public void setCycling(Integer cycling) {
        this.cycling = cycling;
    }

    public Integer getDriving() {
        return driving;
    }

    public void setDriving(Integer driving) {
        this.driving = driving;
    }

    public Integer getStationary() {
        return stationary;
    }

    public void setStationary(Integer stationary) {
        this.stationary = stationary;
    }

    public Integer getUnknown() {
        return unknown;
    }

    public void setUnknown(Integer unknown) {
        this.unknown = unknown;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getOnFoot() {
        return onFoot;
    }

    public void setOnFoot(Integer onFoot) {
        this.onFoot = onFoot;
    }

    public Integer getTilting() {
        return tilting;
    }

    public void setTilting(Integer tilting) {
        this.tilting = tilting;
    }

}
