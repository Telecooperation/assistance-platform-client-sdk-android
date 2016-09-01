package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import org.greenrobot.greendao.annotation.*;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "mobile_connection_sensor".
 */
@Entity(active = true, nameInDb = "mobile_connection_sensor")
public class DbMobileConnectionSensor implements de.tudarmstadt.informatik.tk.assistance.sdk.interfaces.IDbSensor {

    @Id(autoincrement = true)
    @Index
    private Long id;
    private String carrierName;
    private String mobileCountryCode;
    private String mobileNetworkCode;

    @NotNull
    private String created;
    private Boolean voipAvailable;

    @Index
    private Long deviceId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient DbMobileConnectionSensorDao myDao;

    @ToOne(joinProperty = "deviceId")
    private DbDevice dbDevice;

    @Generated
    private transient Long dbDevice__resolvedKey;

    @Generated
    public DbMobileConnectionSensor() {
    }

    public DbMobileConnectionSensor(Long id) {
        this.id = id;
    }

    @Generated
    public DbMobileConnectionSensor(Long id, String carrierName, String mobileCountryCode, String mobileNetworkCode, String created, Boolean voipAvailable, Long deviceId) {
        this.id = id;
        this.carrierName = carrierName;
        this.mobileCountryCode = mobileCountryCode;
        this.mobileNetworkCode = mobileNetworkCode;
        this.created = created;
        this.voipAvailable = voipAvailable;
        this.deviceId = deviceId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbMobileConnectionSensorDao() : null;
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

    public String getMobileCountryCode() {
        return mobileCountryCode;
    }

    public void setMobileCountryCode(String mobileCountryCode) {
        this.mobileCountryCode = mobileCountryCode;
    }

    public String getMobileNetworkCode() {
        return mobileNetworkCode;
    }

    public void setMobileNetworkCode(String mobileNetworkCode) {
        this.mobileNetworkCode = mobileNetworkCode;
    }

    @NotNull
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(@NotNull String created) {
        this.created = created;
    }

    public Boolean getVoipAvailable() {
        return voipAvailable;
    }

    public void setVoipAvailable(Boolean voipAvailable) {
        this.voipAvailable = voipAvailable;
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
