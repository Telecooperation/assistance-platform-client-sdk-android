package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import org.greenrobot.greendao.annotation.*;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "foreground_sensor".
 */
@Entity(active = true, nameInDb = "foreground_sensor")
public class DbForegroundSensor implements de.tudarmstadt.informatik.tk.assistance.sdk.interfaces.IDbSensor {

    @Id(autoincrement = true)
    @Index
    private Long id;
    private String packageName;
    private String appName;
    private String className;
    private String activityLabel;
    private String color;
    private String url;
    private Integer eventType;
    private Integer keystrokes;

    @NotNull
    private String created;

    @Index
    private Long deviceId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient DbForegroundSensorDao myDao;

    @ToOne(joinProperty = "deviceId")
    private DbDevice dbDevice;

    @Generated
    private transient Long dbDevice__resolvedKey;

    @Generated
    public DbForegroundSensor() {
    }

    public DbForegroundSensor(Long id) {
        this.id = id;
    }

    @Generated
    public DbForegroundSensor(Long id, String packageName, String appName, String className, String activityLabel, String color, String url, Integer eventType, Integer keystrokes, String created, Long deviceId) {
        this.id = id;
        this.packageName = packageName;
        this.appName = appName;
        this.className = className;
        this.activityLabel = activityLabel;
        this.color = color;
        this.url = url;
        this.eventType = eventType;
        this.keystrokes = keystrokes;
        this.created = created;
        this.deviceId = deviceId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbForegroundSensorDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getActivityLabel() {
        return activityLabel;
    }

    public void setActivityLabel(String activityLabel) {
        this.activityLabel = activityLabel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getEventType() {
        return eventType;
    }

    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }

    public Integer getKeystrokes() {
        return keystrokes;
    }

    public void setKeystrokes(Integer keystrokes) {
        this.keystrokes = keystrokes;
    }

    @NotNull
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(@NotNull String created) {
        this.created = created;
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
