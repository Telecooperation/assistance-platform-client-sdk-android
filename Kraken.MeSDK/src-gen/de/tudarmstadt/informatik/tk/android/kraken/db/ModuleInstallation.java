package de.tudarmstadt.informatik.tk.android.kraken.db;

import de.tudarmstadt.informatik.tk.android.kraken.db.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "module_installation".
 */
public class ModuleInstallation {

    private long id;
    /** Not-null value. */
    private String created;
    private long module_id;
    private long user_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient ModuleInstallationDao myDao;

    private Module module;
    private Long module__resolvedKey;

    private User user;
    private Long user__resolvedKey;


    public ModuleInstallation() {
    }

    public ModuleInstallation(long id) {
        this.id = id;
    }

    public ModuleInstallation(long id, String created, long module_id, long user_id) {
        this.id = id;
        this.created = created;
        this.module_id = module_id;
        this.user_id = user_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getModuleInstallationDao() : null;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /** Not-null value. */
    public String getCreated() {
        return created;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setCreated(String created) {
        this.created = created;
    }

    public long getModule_id() {
        return module_id;
    }

    public void setModule_id(long module_id) {
        this.module_id = module_id;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    /** To-one relationship, resolved on first access. */
    public Module getModule() {
        long __key = this.module_id;
        if (module__resolvedKey == null || !module__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ModuleDao targetDao = daoSession.getModuleDao();
            Module moduleNew = targetDao.load(__key);
            synchronized (this) {
                module = moduleNew;
            	module__resolvedKey = __key;
            }
        }
        return module;
    }

    public void setModule(Module module) {
        if (module == null) {
            throw new DaoException("To-one property 'module_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.module = module;
            module_id = module.getId();
            module__resolvedKey = module_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public User getUser() {
        long __key = this.user_id;
        if (user__resolvedKey == null || !user__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UserDao targetDao = daoSession.getUserDao();
            User userNew = targetDao.load(__key);
            synchronized (this) {
                user = userNew;
            	user__resolvedKey = __key;
            }
        }
        return user;
    }

    public void setUser(User user) {
        if (user == null) {
            throw new DaoException("To-one property 'user_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.user = user;
            user_id = user.getId();
            user__resolvedKey = user_id;
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