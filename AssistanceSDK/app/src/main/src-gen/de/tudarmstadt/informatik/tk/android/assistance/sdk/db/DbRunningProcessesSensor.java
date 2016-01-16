package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "running_processes_sensor".
 */
public class DbRunningProcessesSensor implements de.tudarmstadt.informatik.tk.android.assistance.sdk.interfaces.IDbSensor {

    private Long id;
    private String name;
    /** Not-null value. */
    private String created;

    public DbRunningProcessesSensor() {
    }

    public DbRunningProcessesSensor(Long id) {
        this.id = id;
    }

    public DbRunningProcessesSensor(Long id, String name, String created) {
        this.id = id;
        this.name = name;
        this.created = created;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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