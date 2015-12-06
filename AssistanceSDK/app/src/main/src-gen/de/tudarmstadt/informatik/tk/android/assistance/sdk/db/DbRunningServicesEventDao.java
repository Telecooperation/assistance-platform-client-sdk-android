package de.tudarmstadt.informatik.tk.android.assistance.sdk.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbRunningServicesEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "running_services_event".
*/
public class DbRunningServicesEventDao extends AbstractDao<DbRunningServicesEvent, Long> {

    public static final String TABLENAME = "running_services_event";

    /**
     * Properties of entity DbRunningServicesEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property PackageName = new Property(1, String.class, "packageName", false, "PACKAGE_NAME");
        public final static Property ClassName = new Property(2, String.class, "className", false, "CLASS_NAME");
        public final static Property Created = new Property(3, String.class, "created", false, "CREATED");
    };


    public DbRunningServicesEventDao(DaoConfig config) {
        super(config);
    }
    
    public DbRunningServicesEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"running_services_event\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"PACKAGE_NAME\" TEXT," + // 1: packageName
                "\"CLASS_NAME\" TEXT," + // 2: className
                "\"CREATED\" TEXT NOT NULL );"); // 3: created
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_running_services_event__id ON running_services_event" +
                " (\"_id\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"running_services_event\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DbRunningServicesEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String packageName = entity.getPackageName();
        if (packageName != null) {
            stmt.bindString(2, packageName);
        }
 
        String className = entity.getClassName();
        if (className != null) {
            stmt.bindString(3, className);
        }
        stmt.bindString(4, entity.getCreated());
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DbRunningServicesEvent readEntity(Cursor cursor, int offset) {
        DbRunningServicesEvent entity = new DbRunningServicesEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // packageName
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // className
            cursor.getString(offset + 3) // created
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DbRunningServicesEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPackageName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setClassName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCreated(cursor.getString(offset + 3));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DbRunningServicesEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DbRunningServicesEvent entity) {
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
    
}
