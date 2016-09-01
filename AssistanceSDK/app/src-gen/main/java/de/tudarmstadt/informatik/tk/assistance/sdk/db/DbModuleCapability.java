package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import org.greenrobot.greendao.annotation.*;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DaoSession;
import org.greenrobot.greendao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit.
/**
 * Entity mapped to table "module_capability".
 */
@Entity(active = true, nameInDb = "module_capability")
public class DbModuleCapability {

    @Id(autoincrement = true)
    @Index
    private Long id;

    @NotNull
    @Index
    private String type;
    private Double collectionInterval;
    private Double updateInterval;
    private Integer accuracy;
    private String permissions;
    private boolean required;

    @Index
    private boolean active;

    @NotNull
    private String created;

    @Index
    private Long moduleId;

    /** Used to resolve relations */
    @Generated
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    @Generated
    private transient DbModuleCapabilityDao myDao;

    @ToOne(joinProperty = "moduleId")
    private DbModule dbModule;

    @Generated
    private transient Long dbModule__resolvedKey;

    @Generated
    public DbModuleCapability() {
    }

    public DbModuleCapability(Long id) {
        this.id = id;
    }

    @Generated
    public DbModuleCapability(Long id, String type, Double collectionInterval, Double updateInterval, Integer accuracy, String permissions, boolean required, boolean active, String created, Long moduleId) {
        this.id = id;
        this.type = type;
        this.collectionInterval = collectionInterval;
        this.updateInterval = updateInterval;
        this.accuracy = accuracy;
        this.permissions = permissions;
        this.required = required;
        this.active = active;
        this.created = created;
        this.moduleId = moduleId;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbModuleCapabilityDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    public String getType() {
        return type;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setType(@NotNull String type) {
        this.type = type;
    }

    public Double getCollectionInterval() {
        return collectionInterval;
    }

    public void setCollectionInterval(Double collectionInterval) {
        this.collectionInterval = collectionInterval;
    }

    public Double getUpdateInterval() {
        return updateInterval;
    }

    public void setUpdateInterval(Double updateInterval) {
        this.updateInterval = updateInterval;
    }

    public Integer getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(Integer accuracy) {
        this.accuracy = accuracy;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    public boolean getRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @NotNull
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(@NotNull String created) {
        this.created = created;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    /** To-one relationship, resolved on first access. */
    @Generated
    public DbModule getDbModule() {
        Long __key = this.moduleId;
        if (dbModule__resolvedKey == null || !dbModule__resolvedKey.equals(__key)) {
            __throwIfDetached();
            DbModuleDao targetDao = daoSession.getDbModuleDao();
            DbModule dbModuleNew = targetDao.load(__key);
            synchronized (this) {
                dbModule = dbModuleNew;
            	dbModule__resolvedKey = __key;
            }
        }
        return dbModule;
    }

    @Generated
    public void setDbModule(DbModule dbModule) {
        synchronized (this) {
            this.dbModule = dbModule;
            moduleId = dbModule == null ? null : dbModule.getId();
            dbModule__resolvedKey = moduleId;
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
