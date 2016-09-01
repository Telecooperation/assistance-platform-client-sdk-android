package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.SqlUtils;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "news".
*/
public class DbNewsDao extends AbstractDao<DbNews, Long> {

    public static final String TABLENAME = "news";

    /**
     * Properties of entity DbNews.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Content = new Property(1, String.class, "content", false, "CONTENT");
        public final static Property Created = new Property(2, String.class, "created", false, "CREATED");
        public final static Property ModuleId = new Property(3, Long.class, "moduleId", false, "MODULE_ID");
        public final static Property UserId = new Property(4, Long.class, "userId", false, "USER_ID");
    }

    private DaoSession daoSession;

    private Query<DbNews> dbModule_DbNewsListQuery;
    private Query<DbNews> dbUser_DbNewsListQuery;

    public DbNewsDao(DaoConfig config) {
        super(config);
    }
    
    public DbNewsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"news\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"CONTENT\" TEXT," + // 1: content
                "\"CREATED\" TEXT NOT NULL ," + // 2: created
                "\"MODULE_ID\" INTEGER," + // 3: moduleId
                "\"USER_ID\" INTEGER);"); // 4: userId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_news__id ON news" +
                " (\"_id\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_news_MODULE_ID ON news" +
                " (\"MODULE_ID\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_news_USER_ID ON news" +
                " (\"USER_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"news\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DbNews entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindString(3, entity.getCreated());
 
        Long moduleId = entity.getModuleId();
        if (moduleId != null) {
            stmt.bindLong(4, moduleId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DbNews entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String content = entity.getContent();
        if (content != null) {
            stmt.bindString(2, content);
        }
        stmt.bindString(3, entity.getCreated());
 
        Long moduleId = entity.getModuleId();
        if (moduleId != null) {
            stmt.bindLong(4, moduleId);
        }
 
        Long userId = entity.getUserId();
        if (userId != null) {
            stmt.bindLong(5, userId);
        }
    }

    @Override
    protected final void attachEntity(DbNews entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DbNews readEntity(Cursor cursor, int offset) {
        DbNews entity = new DbNews( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // content
            cursor.getString(offset + 2), // created
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // moduleId
            cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4) // userId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DbNews entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setContent(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setCreated(cursor.getString(offset + 2));
        entity.setModuleId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setUserId(cursor.isNull(offset + 4) ? null : cursor.getLong(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DbNews entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DbNews entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DbNews entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "dbNewsList" to-many relationship of DbModule. */
    public List<DbNews> _queryDbModule_DbNewsList(Long moduleId) {
        synchronized (this) {
            if (dbModule_DbNewsListQuery == null) {
                QueryBuilder<DbNews> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ModuleId.eq(null));
                dbModule_DbNewsListQuery = queryBuilder.build();
            }
        }
        Query<DbNews> query = dbModule_DbNewsListQuery.forCurrentThread();
        query.setParameter(0, moduleId);
        return query.list();
    }

    /** Internal query to resolve the "dbNewsList" to-many relationship of DbUser. */
    public List<DbNews> _queryDbUser_DbNewsList(Long userId) {
        synchronized (this) {
            if (dbUser_DbNewsListQuery == null) {
                QueryBuilder<DbNews> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.UserId.eq(null));
                dbUser_DbNewsListQuery = queryBuilder.build();
            }
        }
        Query<DbNews> query = dbUser_DbNewsListQuery.forCurrentThread();
        query.setParameter(0, userId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDbModuleDao().getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T1", daoSession.getDbUserDao().getAllColumns());
            builder.append(" FROM news T");
            builder.append(" LEFT JOIN module T0 ON T.\"MODULE_ID\"=T0.\"_id\"");
            builder.append(" LEFT JOIN user T1 ON T.\"USER_ID\"=T1.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected DbNews loadCurrentDeep(Cursor cursor, boolean lock) {
        DbNews entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DbModule dbModule = loadCurrentOther(daoSession.getDbModuleDao(), cursor, offset);
        entity.setDbModule(dbModule);
        offset += daoSession.getDbModuleDao().getAllColumns().length;

        DbUser dbUser = loadCurrentOther(daoSession.getDbUserDao(), cursor, offset);
        entity.setDbUser(dbUser);

        return entity;    
    }

    public DbNews loadDeep(Long key) {
        assertSinglePk();
        if (key == null) {
            return null;
        }

        StringBuilder builder = new StringBuilder(getSelectDeep());
        builder.append("WHERE ");
        SqlUtils.appendColumnsEqValue(builder, "T", getPkColumns());
        String sql = builder.toString();
        
        String[] keyArray = new String[] { key.toString() };
        Cursor cursor = db.rawQuery(sql, keyArray);
        
        try {
            boolean available = cursor.moveToFirst();
            if (!available) {
                return null;
            } else if (!cursor.isLast()) {
                throw new IllegalStateException("Expected unique result, but count was " + cursor.getCount());
            }
            return loadCurrentDeep(cursor, true);
        } finally {
            cursor.close();
        }
    }
    
    /** Reads all available rows from the given cursor and returns a list of new ImageTO objects. */
    public List<DbNews> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<DbNews> list = new ArrayList<DbNews>(count);
        
        if (cursor.moveToFirst()) {
            if (identityScope != null) {
                identityScope.lock();
                identityScope.reserveRoom(count);
            }
            try {
                do {
                    list.add(loadCurrentDeep(cursor, false));
                } while (cursor.moveToNext());
            } finally {
                if (identityScope != null) {
                    identityScope.unlock();
                }
            }
        }
        return list;
    }
    
    protected List<DbNews> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<DbNews> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
