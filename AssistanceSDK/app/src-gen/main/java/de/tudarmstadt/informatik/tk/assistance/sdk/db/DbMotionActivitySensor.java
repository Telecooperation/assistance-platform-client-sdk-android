package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import org.greenrobot.greendao.annotation.*;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.

/**
 * Entity mapped to table "motion_activity_sensor".
 */
@Entity(active = true, nameInDb = "motion_activity_sensor")
public class DbMotionActivitySensor implements de.tudarmstadt.informatik.tk.assistance.sdk.interfaces.IDbSensor {

    @Id(autoincrement = true)
    @Index
    private Long id;
    private Integer walking;
    private Integer running;
    private Integer cycling;
    private Integer driving;
    private Integer stationary;
    private Integer unknown;

    @NotNull
    private String created;
    private Integer onFoot;
    private Integer tilting;

    @Index
    private Long deviceId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient DbMotionActivitySensorDao myDao;

    @ToOne(joinProperty = "deviceId")
    private DbDevice dbDevice;

    @Generated
    private transient Long dbDevice__resolvedKey;

    @Generated
    public DbMotionActivitySensor() {
    }

    public DbMotionActivitySensor(Long id) {
        this.id = id;
    }

    @Generated
    public DbMotionActivitySensor(Long id, Integer walking, Integer running, Integer cycling, Integer driving, Integer stationary, Integer unknown, String created, Integer onFoot, Integer tilting, Long deviceId) {
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
        this.deviceId = deviceId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbMotionActivitySensorDao() : null;
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

    @NotNull
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(@NotNull String created) {
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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public DbDevice getDbDevice() {
        Long __key = this.deviceId;
        if (dbDevice__resolvedKey == null || !dbDevice__resolvedKey.equals(__key)) {
            __throwIfDetached();
            DbDeviceDao targetDao = daoSession.getDbDeviceDao();
            DbDevice dbDeviceNew = targetDao.load(__key);
            synchronized (this) {
                dbDevice = dbDeviceNew;
            	dbDevice__resolvedKey = __key;
            }
        }
        return dbDevice;
    }

    @Generated
    public void setDbDevice(DbDevice dbDevice) {
        synchronized (this) {
            this.dbDevice = dbDevice;
            deviceId = dbDevice == null ? null : dbDevice.getId();
            dbDevice__resolvedKey = deviceId;
        }
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void delete() {
        __throwIfDetached();
        myDao.delete(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void update() {
        __throwIfDetached();
        myDao.update(this);
    }

    /**
    * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
    * Entity must attached to an entity context.
    */
    @Generated
    public void refresh() {
        __throwIfDetached();
        myDao.refresh(this);
    }

    @Generated
    private void __throwIfDetached() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
    }

}
