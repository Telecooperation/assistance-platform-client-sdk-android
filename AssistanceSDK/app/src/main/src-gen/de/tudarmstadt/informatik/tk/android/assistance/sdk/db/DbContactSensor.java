package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

import java.util.List;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "contact_sensor".
 */
public class DbContactSensor implements de.tudarmstadt.informatik.tk.android.assistance.sdk.interfaces.IDbUpdatableSensor {

    private Long id;
    private Long contactId;
    private Long globalContactId;
    private String displayName;
    private String givenName;
    private String familyName;
    private Integer starred;
    private Integer lastTimeContacted;
    private Integer timesContacted;
    private String note;
    private Boolean isNew;
    private Boolean isUpdated;
    private Boolean isDeleted;
    /** Not-null value. */
    private String created;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DbContactSensorDao myDao;

    private List<DbContactNumberSensor> dbContactNumberSensorList;
    private List<DbContactEmailSensor> dbContactEmailSensorList;

    public DbContactSensor() {
    }

    public DbContactSensor(Long id) {
        this.id = id;
    }

    public DbContactSensor(Long id, Long contactId, Long globalContactId, String displayName, String givenName, String familyName, Integer starred, Integer lastTimeContacted, Integer timesContacted, String note, Boolean isNew, Boolean isUpdated, Boolean isDeleted, String created) {
        this.id = id;
        this.contactId = contactId;
        this.globalContactId = globalContactId;
        this.displayName = displayName;
        this.givenName = givenName;
        this.familyName = familyName;
        this.starred = starred;
        this.lastTimeContacted = lastTimeContacted;
        this.timesContacted = timesContacted;
        this.note = note;
        this.isNew = isNew;
        this.isUpdated = isUpdated;
        this.isDeleted = isDeleted;
        this.created = created;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbContactSensorDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getGlobalContactId() {
        return globalContactId;
    }

    public void setGlobalContactId(Long globalContactId) {
        this.globalContactId = globalContactId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public Integer getStarred() {
        return starred;
    }

    public void setStarred(Integer starred) {
        this.starred = starred;
    }

    public Integer getLastTimeContacted() {
        return lastTimeContacted;
    }

    public void setLastTimeContacted(Integer lastTimeContacted) {
        this.lastTimeContacted = lastTimeContacted;
    }

    public Integer getTimesContacted() {
        return timesContacted;
    }

    public void setTimesContacted(Integer timesContacted) {
        this.timesContacted = timesContacted;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<DbContactNumberSensor> getDbContactNumberSensorList() {
        if (dbContactNumberSensorList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbContactNumberSensorDao targetDao = daoSession.getDbContactNumberSensorDao();
            List<DbContactNumberSensor> dbContactNumberSensorListNew = targetDao._queryDbContactSensor_DbContactNumberSensorList(id);
            synchronized (this) {
                if(dbContactNumberSensorList == null) {
                    dbContactNumberSensorList = dbContactNumberSensorListNew;
                }
            }
        }
        return dbContactNumberSensorList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetDbContactNumberSensorList() {
        dbContactNumberSensorList = null;
    }

    /** To-many relationship, resolved on first access (and after reset). Changes to to-many relations are not persisted, make changes to the target entity. */
    public List<DbContactEmailSensor> getDbContactEmailSensorList() {
        if (dbContactEmailSensorList == null) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbContactEmailSensorDao targetDao = daoSession.getDbContactEmailSensorDao();
            List<DbContactEmailSensor> dbContactEmailSensorListNew = targetDao._queryDbContactSensor_DbContactEmailSensorList(id);
            synchronized (this) {
                if(dbContactEmailSensorList == null) {
                    dbContactEmailSensorList = dbContactEmailSensorListNew;
                }
            }
        }
        return dbContactEmailSensorList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    public synchronized void resetDbContactEmailSensorList() {
        dbContactEmailSensorList = null;
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