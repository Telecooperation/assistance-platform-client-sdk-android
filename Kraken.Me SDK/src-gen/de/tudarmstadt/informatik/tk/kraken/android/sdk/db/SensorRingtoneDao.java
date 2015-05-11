package de.tudarmstadt.informatik.tk.kraken.android.sdk.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.tudarmstadt.informatik.tk.kraken.android.sdk.db.SensorRingtone;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table SENSOR_RINGTONE.
*/
public class SensorRingtoneDao extends AbstractDao<SensorRingtone, Long> {

    public static final String TABLENAME = "SENSOR_RINGTONE";

    /**
     * Properties of entity SensorRingtone.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property RingtoneMode = new Property(1, Integer.class, "ringtoneMode", false, "RINGTONE_MODE");
        public final static Property Timestamp = new Property(2, Long.class, "timestamp", false, "TIMESTAMP");
    };


    public SensorRingtoneDao(DaoConfig config) {
        super(config);
    }
    
    public SensorRingtoneDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "'SENSOR_RINGTONE' (" + //
                "'_id' INTEGER PRIMARY KEY ," + // 0: id
                "'RINGTONE_MODE' INTEGER," + // 1: ringtoneMode
                "'TIMESTAMP' INTEGER);"); // 2: timestamp
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "'SENSOR_RINGTONE'";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, SensorRingtone entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Integer ringtoneMode = entity.getRingtoneMode();
        if (ringtoneMode != null) {
            stmt.bindLong(2, ringtoneMode);
        }
 
        Long timestamp = entity.getTimestamp();
        if (timestamp != null) {
            stmt.bindLong(3, timestamp);
        }
    }

    /** @inheritdoc */
    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    /** @inheritdoc */
    @Override
    public SensorRingtone readEntity(Cursor cursor, int offset) {
        SensorRingtone entity = new SensorRingtone( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1), // ringtoneMode
            cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2) // timestamp
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, SensorRingtone entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setRingtoneMode(cursor.isNull(offset + 1) ? null : cursor.getInt(offset + 1));
        entity.setTimestamp(cursor.isNull(offset + 2) ? null : cursor.getLong(offset + 2));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(SensorRingtone entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(SensorRingtone entity) {
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
