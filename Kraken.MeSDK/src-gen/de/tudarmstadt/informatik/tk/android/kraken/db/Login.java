package de.tudarmstadt.informatik.tk.android.kraken.db;

import java.util.List;
import de.tudarmstadt.informatik.tk.android.kraken.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "login".
 */
public class Login {

    private long id;
    /** Not-null value. */
    private String token;
    private Long server_device_id;
    /** Not-null value. */
    private String last_email;
    /** Not-null value. */
    private String created;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient LoginDao myDao;

    private List<Device> deviceList;

    public Login() {
    }

    public Login(long id) {
        this.id = id;
    }

    public Login(long id, String token, Long server_device_id, String last_email, String created) {
        this.id = id;
        this.token = token;
        this.server_device_id = server_device_id;
        this.last_email = last_email;
        this.created = created;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getLoginDao() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getToken() {
        return token;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setToken(String token) {
        this.token = token;
    }

    public Long getServer_device_id() {
        return server_device_id;
    }

    public void setServer_device_id(Long server_device_id) {
        this.server_device_id = server_device_id;
    }

    /** Not-null value. */
    public String getLast_email() {
        return last_email;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setLast_email(String last_email) {
        this.last_email = last_email;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<Device> getDeviceList() {
        if (deviceList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DeviceDao targetDao = daoSession.getDeviceDao();
            List<Device> deviceListNew = targetDao._queryLogin_DeviceList(id);
            synchronized (this) {
                if(deviceList == null) {
                    deviceList = deviceListNew;
                }
            }
        }
        return deviceList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetDeviceList() {
        deviceList = null;
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