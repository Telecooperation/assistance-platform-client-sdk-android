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

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "power_state_sensor".
*/
public class DbPowerStateSensorDao extends AbstractDao<DbPowerStateSensor, Long> {

    public static final String TABLENAME = "power_state_sensor";

    /**
     * Properties of entity DbPowerStateSensor.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property IsCharging = new Property(1, Boolean.class, "isCharging", false, "IS_CHARGING");
        public final static Property Percent = new Property(2, Float.class, "percent", false, "PERCENT");
        public final static Property Created = new Property(3, String.class, "created", false, "CREATED");
        public final static Property ChargingState = new Property(4, Integer.class, "chargingState", false, "CHARGING_STATE");
        public final static Property ChargingMode = new Property(5, Integer.class, "chargingMode", false, "CHARGING_MODE");
        public final static Property PowerSaveMode = new Property(6, Boolean.class, "powerSaveMode", false, "POWER_SAVE_MODE");
        public final static Property DeviceId = new Property(7, Long.class, "deviceId", false, "DEVICE_ID");
    }

    private DaoSession daoSession;


    public DbPowerStateSensorDao(DaoConfig config) {
        super(config);
    }
    
    public DbPowerStateSensorDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"power_state_sensor\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"IS_CHARGING\" INTEGER," + // 1: isCharging
                "\"PERCENT\" REAL," + // 2: percent
                "\"CREATED\" TEXT NOT NULL ," + // 3: created
                "\"CHARGING_STATE\" INTEGER," + // 4: chargingState
                "\"CHARGING_MODE\" INTEGER," + // 5: chargingMode
                "\"POWER_SAVE_MODE\" INTEGER," + // 6: powerSaveMode
                "\"DEVICE_ID\" INTEGER);"); // 7: deviceId
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_power_state_sensor__id ON power_state_sensor" +
                " (\"_id\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_power_state_sensor_DEVICE_ID ON power_state_sensor" +
                " (\"DEVICE_ID\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"power_state_sensor\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, DbPowerStateSensor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Boolean isCharging = entity.getIsCharging();
        if (isCharging != null) {
            stmt.bindLong(2, isCharging ? 1L: 0L);
        }
 
        Float percent = entity.getPercent();
        if (percent != null) {
            stmt.bindDouble(3, percent);
        }
        stmt.bindString(4, entity.getCreated());
 
        Integer chargingState = entity.getChargingState();
        if (chargingState != null) {
            stmt.bindLong(5, chargingState);
        }
 
        Integer chargingMode = entity.getChargingMode();
        if (chargingMode != null) {
            stmt.bindLong(6, chargingMode);
        }
 
        Boolean powerSaveMode = entity.getPowerSaveMode();
        if (powerSaveMode != null) {
            stmt.bindLong(7, powerSaveMode ? 1L: 0L);
        }
 
        Long deviceId = entity.getDeviceId();
        if (deviceId != null) {
            stmt.bindLong(8, deviceId);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, DbPowerStateSensor entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Boolean isCharging = entity.getIsCharging();
        if (isCharging != null) {
            stmt.bindLong(2, isCharging ? 1L: 0L);
        }
 
        Float percent = entity.getPercent();
        if (percent != null) {
            stmt.bindDouble(3, percent);
        }
        stmt.bindString(4, entity.getCreated());
 
        Integer chargingState = entity.getChargingState();
        if (chargingState != null) {
            stmt.bindLong(5, chargingState);
        }
 
        Integer chargingMode = entity.getChargingMode();
        if (chargingMode != null) {
            stmt.bindLong(6, chargingMode);
        }
 
        Boolean powerSaveMode = entity.getPowerSaveMode();
        if (powerSaveMode != null) {
            stmt.bindLong(7, powerSaveMode ? 1L: 0L);
        }
 
        Long deviceId = entity.getDeviceId();
        if (deviceId != null) {
            stmt.bindLong(8, deviceId);
        }
    }

    @Override
    protected final void attachEntity(DbPowerStateSensor entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public DbPowerStateSensor readEntity(Cursor cursor, int offset) {
        DbPowerStateSensor entity = new DbPowerStateSensor( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0, // isCharging
            cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2), // percent
            cursor.getString(offset + 3), // created
            cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4), // chargingState
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // chargingMode
            cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0, // powerSaveMode
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7) // deviceId
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, DbPowerStateSensor entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setIsCharging(cursor.isNull(offset + 1) ? null : cursor.getShort(offset + 1) != 0);
        entity.setPercent(cursor.isNull(offset + 2) ? null : cursor.getFloat(offset + 2));
        entity.setCreated(cursor.getString(offset + 3));
        entity.setChargingState(cursor.isNull(offset + 4) ? null : cursor.getInt(offset + 4));
        entity.setChargingMode(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setPowerSaveMode(cursor.isNull(offset + 6) ? null : cursor.getShort(offset + 6) != 0);
        entity.setDeviceId(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(DbPowerStateSensor entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(DbPowerStateSensor entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(DbPowerStateSensor entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
    private String selectDeep;

    protected String getSelectDeep() {
        if (selectDeep == null) {
            StringBuilder builder = new StringBuilder("SELECT ");
            SqlUtils.appendColumns(builder, "T", getAllColumns());
            builder.append(',');
            SqlUtils.appendColumns(builder, "T0", daoSession.getDbDeviceDao().getAllColumns());
            builder.append(" FROM power_state_sensor T");
            builder.append(" LEFT JOIN device T0 ON T.\"DEVICE_ID\"=T0.\"_id\"");
            builder.append(' ');
            selectDeep = builder.toString();
        }
        return selectDeep;
    }
    
    protected DbPowerStateSensor loadCurrentDeep(Cursor cursor, boolean lock) {
        DbPowerStateSensor entity = loadCurrent(cursor, 0, lock);
        int offset = getAllColumns().length;

        DbDevice dbDevice = loadCurrentOther(daoSession.getDbDeviceDao(), cursor, offset);
        entity.setDbDevice(dbDevice);

        return entity;    
    }

    public DbPowerStateSensor loadDeep(Long key) {
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
    public List<DbPowerStateSensor> loadAllDeepFromCursor(Cursor cursor) {
        int count = cursor.getCount();
        List<DbPowerStateSensor> list = new ArrayList<DbPowerStateSensor>(count);
        
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
    
    protected List<DbPowerStateSensor> loadDeepAllAndCloseCursor(Cursor cursor) {
        try {
            return loadAllDeepFromCursor(cursor);
        } finally {
            cursor.close();
        }
    }
    

    /** A raw-style query where you can pass any WHERE clause and arguments. */
    public List<DbPowerStateSensor> queryDeep(String where, String... selectionArg) {
        Cursor cursor = db.rawQuery(getSelectDeep() + where, selectionArg);
        return loadDeepAllAndCloseCursor(cursor);
    }
 
}
