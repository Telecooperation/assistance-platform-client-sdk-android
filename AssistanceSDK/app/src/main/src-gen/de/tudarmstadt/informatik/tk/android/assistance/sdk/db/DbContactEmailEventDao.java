package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

import java.util.List;
import java.util.ArrayList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.SqlUtils;
import de.greenrobot.dao.internal.DaoConfig;
import de.greenrobot.dao.query.Query;
import de.greenrobot.dao.query.QueryBuilder;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbContactEmailEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "contact_email_event".
*/
public class DbContactEmailEventDao extends AbstractDao<DbContactEmailEvent, Long> {

    public static final String TABLENAME = "contact_email_event";

    /**
     * Properties of entity DbContactEmailEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property MailId = new Property(1, Long.class, "mailId", false, "MAIL_ID");
        public final static Property Address = new Property(2, String.class, "address", false, "ADDRESS");
        public final static Property Type = new Property(3, String.class, "type", false, "TYPE");
        public final static Property IsNew = new Property(4, Boolean.class, "isNew", false, "IS_NEW");
        public final static Property IsUpdated = new Property(5, Boolean.class, "isUpdated", false, "IS_UPDATED");
        public final static Property IsDeleted = new Property(6, Boolean.class, "isDeleted", false, "IS_DELETED");
        public final static Property Created = new Property(7, String.class, "created", false, "CREATED");
        public final static Property ContactId = new Property(8, Long.class, "contactId", false, "CONTACT_ID");
    };

    private DaoSession daoSession;

    private Query<DbContactEmailEvent> dbContactEvent_DbContactEmailEventListQuery;

    public DbContactEmailEventDao(DaoConfig config) {
        super(config);
    }
    
    public DbContactEmailEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"contact_email_event\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"MAIL_ID\" INTEGER," + // 1: mailId
                "\"ADDRESS\" TEXT," + // 2: address
                "\"TYPE\" TEXT," + // 3: type
                "\"IS_NEW\" INTEGER," + // 4: isNew
                "\"IS_UPDATED\" INTEGER," + // 5: isUpdated
                "\"IS_DELETED\" INTEGER," + // 6: isDeleted
                "\"CREATED\" TEXT NOT NULL ," + // 7: created
                "\"CONTACT_ID\" INTEGER);"); // 8: contactId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_contact_email_event__id ON contact_email_event" +
                " (\"_id\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_contact_email_event_CONTACT_ID ON contact_email_event" +
                " (\"CONTACT_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"contact_email_event\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DbContactEmailEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long mailId = entity.getMailId();
        if (mailId != null) {
            stmt.bindLong(2, mailId);
        }
 
        String address = entity.getAddress();
        if (address != null) {
            stmt.bindString(3, address);
        }
 
        String type = entity.getType();
        if (type != null) {
            stmt.bindString(4, type);
        }
 
        Boolean isNew = entity.getIsNew();
        if (isNew != null) {
            stmt.bindLong(5, isNew ? 1L: 0L);
        }
 
        Boolean isUpdated = entity.getIsUpdated();
        if (isUpdated != null) {
            stmt.bindLong(6, isUpdated ? 1L: 0L);
        }
 
        Boolean isDeleted = entity.getIsDeleted();
        if (isDeleted != null) {
            stmt.bindLong(7, isDeleted ? 1L: 0L);
        }
        stmt.bindString(8, entity.getCreated());
 
        Long contactId = entity.getContactId();
        if (contactId != null) {
            stmt.bindLong(9, contactId);
        }
    }

    @Override
    protected void attachEntity(DbContactEmailEvent entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DbContactEmailEvent readEntity(Cursor cursor, int offset) {
        DbContactEmailEvent entity = new DbContactEmailEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // mailId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // address
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // type
            cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0, // isNew
            cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0, // isUpdated
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // isDeleted
            cursor.getString(offset + 7), // created
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8) // contactId
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DbContactEmailEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setMailId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setAddress(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setType(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsNew(cursor.isNull(offset + 4) ? null : cursor.getShort(offset + 4) != 0);
        entity.setIsUpdated(cursor.isNull(offset + 5) ? null : cursor.getShort(offset + 5) != 0);
        entity.setIsDeleted(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setCreated(cursor.getString(offset + 7));
        entity.setContactId(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DbContactEmailEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DbContactEmailEvent entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    /** @inheritdoc */
    @Override    
    protected boolean isEntityUpdateable() {
        return true;
    }
    
    /** Internal query to resolve the "dbContactEmailEventList" to-many relationship of DbContactEvent. */
    public List<DbContactEmailEvent> _queryDbContactEvent_DbContactEmailEventList(Long contactId) {
        synchronized (this) {
            if (dbContactEvent_DbContactEmailEventListQuery == null) {
                QueryBuilder<DbContactEmailEvent> queryBuilder = queryBuilder();
                queryBuilder.where(Properties.ContactId.eq(null));
                dbContactEvent_DbContactEmailEventListQuery = queryBuilder.build();
            }
        }
        Query<DbContactEmailEvent> query = dbContactEvent_DbContactEmailEventListQuery.forCurrentThread();
        query.setParameter(0, contactId);
        return query.list();
    }

    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDbContactEventDao().getAllColumns());
            builder.append(" FROM contact_email_event T");
            builder.append(" LEFT JOIN contact_event T0 ON T.\"CONTACT_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected DbContactEmailEvent loadCurrentDeep(Cursor cursor, boolean lock) {
        DbContactEmailEvent entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DbContactEvent dbContactEvent = loadCurrentOther(daoSession.getDbContactEventDao(), cursor, offset);
        entity.setDbContactEvent(dbContactEvent);

        return entity;    
    }

    public DbContactEmailEvent loadDeep(Long key) {
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
    public List<DbContactEmailEvent> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<DbContactEmailEvent> list = new ArrayList<DbContactEmailEvent>(count);
        
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
    
    protected List<DbContactEmailEvent> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<DbContactEmailEvent> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
