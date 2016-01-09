package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "module_allowed_capabilities".
 */
public class DbModuleAllowedCapabilities {

    private Long id;
    /** Not-null value. */
    private String type;
    private boolean isAllowed;
    /** Not-null value. */
    private String created;
    private Long userId;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient DbModuleAllowedCapabilitiesDao myDao;

    private DbUser dbUser;
    private Long dbUser__resolvedKey;


    public DbModuleAllowedCapabilities() {
    }

    public DbModuleAllowedCapabilities(Long id) {
        this.id = id;
    }

    public DbModuleAllowedCapabilities(Long id, String type, boolean isAllowed, String created, Long userId) {
        this.id = id;
        this.type = type;
        this.isAllowed = isAllowed;
        this.created = created;
        this.userId = userId;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getDbModuleAllowedCapabilitiesDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getType() {
        return type;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setType(String type) {
        this.type = type;
    }

    public boolean getIsAllowed() {
        return isAllowed;
    }

    public void setIsAllowed(boolean isAllowed) {
        this.isAllowed = isAllowed;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /** To-one relationship, resolved on first access. */
    public DbUser getDbUser() {
        Long __key = this.userId;
        if (dbUser__resolvedKey == null || !dbUser__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            DbUserDao targetDao = daoSession.getDbUserDao();
            DbUser dbUserNew = targetDao.load(__key);
            synchronized (this) {
                dbUser = dbUserNew;
            	dbUser__resolvedKey = __key;
            }
        }
        return dbUser;
    }

    public void setDbUser(DbUser dbUser) {
        synchronized (this) {
            this.dbUser = dbUser;
            userId = dbUser == null ? null : dbUser.getId();
            dbUser__resolvedKey = userId;
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
