package de.tudarmstadt.informatik.tk.android.kraken.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.tudarmstadt.informatik.tk.android.kraken.db.DbMotionActivityEvent;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "motion_activity_event".
*/
public class DbMotionActivityEventDao extends AbstractDao<DbMotionActivityEvent, Long> {

    public static final String TABLENAME = "motion_activity_event";

    /**
     * Properties of entity DbMotionActivityEvent.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Walking = new Property(1, Integer.class, "walking", false, "WALKING");
        public final static Property Running = new Property(2, Integer.class, "running", false, "RUNNING");
        public final static Property Cycling = new Property(3, Integer.class, "cycling", false, "CYCLING");
        public final static Property Driving = new Property(4, Integer.class, "driving", false, "DRIVING");
        public final static Property Stationary = new Property(5, Integer.class, "stationary", false, "STATIONARY");
        public final static Property Unknown = new Property(6, Integer.class, "unknown", false, "UNKNOWN");
        public final static Property Created = new Property(7, String.class, "created", false, "CREATED");
        public final static Property OnFoot = new Property(8, Integer.class, "onFoot", false, "ON_FOOT");
        public final static Property Tilting = new Property(9, Integer.class, "tilting", false, "TILTING");
    };


    public DbMotionActivityEventDao(DaoConfig config) {
        super(config);
    }
    
    public DbMotionActivityEventDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"motion_activity_event\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"WALKING\" INTEGER," + // 1: walking
                "\"RUNNING\" INTEGER," + // 2: running
                "\"CYCLING\" INTEGER," + // 3: cycling
                "\"DRIVING\" INTEGER," + // 4: driving
                "\"STATIONARY\" INTEGER," + // 5: stationary
                "\"UNKNOWN\" INTEGER," + // 6: unknown
                "\"CREATED\" TEXT NOT NULL ," + // 7: created
                "\"ON_FOOT\" INTEGER," + // 8: onFoot
                "\"TILTING\" INTEGER);"); // 9: tilting
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_motion_activity_event__id ON motion_activity_event" +
                " (\"_id\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"motion_activity_event\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DbMotionActivityEvent entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer walking = entity.getWalking();
        if (walking != null) {
            stmt.bindLong(2, walking);
        }
 
        Integer running = entity.getRunning();
        if (running != null) {
            stmt.bindLong(3, running);
        }
 
        Integer cycling = entity.getCycling();
        if (cycling != null) {
            stmt.bindLong(4, cycling);
        }
 
        Integer driving = entity.getDriving();
        if (driving != null) {
            stmt.bindLong(5, driving);
        }
 
        Integer stationary = entity.getStationary();
        if (stationary != null) {
            stmt.bindLong(6, stationary);
        }
 
        Integer unknown = entity.getUnknown();
        if (unknown != null) {
            stmt.bindLong(7, unknown);
        }
        stmt.bindString(8, entity.getCreated());
 
        Integer onFoot = entity.getOnFoot();
        if (onFoot != null) {
            stmt.bindLong(9, onFoot);
        }
 
        Integer tilting = entity.getTilting();
        if (tilting != null) {
            stmt.bindLong(10, tilting);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public DbMotionActivityEvent readEntity(Cursor cursor, int offset) {
        DbMotionActivityEvent entity = new DbMotionActivityEvent( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // walking
            cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2), // running
            cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3), // cycling
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // driving
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // stationary
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // unknown
            cursor.getString(offset + 7), // created
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // onFoot
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9) // tilting
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DbMotionActivityEvent entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setWalking(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setRunning(cursor.isNull(offset + 2) ? null : cursor.getInt(offset + 2));
        entity.setCycling(cursor.isNull(offset + 3) ? null : cursor.getInt(offset + 3));
        entity.setDriving(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setStationary(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setUnknown(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setCreated(cursor.getString(offset + 7));
        entity.setOnFoot(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setTilting(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DbMotionActivityEvent entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DbMotionActivityEvent entity) {
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