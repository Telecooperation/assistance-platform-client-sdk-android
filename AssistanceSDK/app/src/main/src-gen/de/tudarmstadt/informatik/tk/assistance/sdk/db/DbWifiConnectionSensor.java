package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "wifi_connection_sensor".
 */
public class DbWifiConnectionSensor implements de.tudarmstadt.informatik.tk.assistance.sdk.interfaces.IDbSensor {

    private Long id;
    private String ssid;
    private String bssid;
    /** Not-null value. */
    private String created;
    private Integer channel;
    private Integer frequency;
    private Integer linkSpeed;
    private Integer signalStrength;
    private Integer networkId;
    private Long deviceId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DbWifiConnectionSensorDao myDao;

    private DbDevice dbDevice;
    private Long dbDevice__resolvedKey;


    public DbWifiConnectionSensor() {
    }

    public DbWifiConnectionSensor(Long id) {
        this.id = id;
    }

    public DbWifiConnectionSensor(Long id, String ssid, String bssid, String created, Integer channel, Integer frequency, Integer linkSpeed, Integer signalStrength, Integer networkId, Long deviceId) {
        this.id = id;
        this.ssid = ssid;
        this.bssid = bssid;
        this.created = created;
        this.channel = channel;
        this.frequency = frequency;
        this.linkSpeed = linkSpeed;
        this.signalStrength = signalStrength;
        this.networkId = networkId;
        this.deviceId = deviceId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbWifiConnectionSensorDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    /** To-one relationship, resolved on first access. */
    public DbDevice getDbDevice() {
        Long __key = this.deviceId;
        if (dbDevice__resolvedKey == null || !dbDevice__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbDeviceDao targetDao = daoSession.getDbDeviceDao();
            DbDevice dbDeviceNew = targetDao.load(__key);
            synchronized (this) {
                dbDevice = dbDeviceNew;
            	dbDevice__resolvedKey = __key;
            }
        }
        return dbDevice;
    }

    public void setDbDevice(DbDevice dbDevice) {
        synchronized (this) {
            this.dbDevice = dbDevice;
            deviceId = dbDevice == null ? null : dbDevice.getId();
            dbDevice__resolvedKey = deviceId;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}