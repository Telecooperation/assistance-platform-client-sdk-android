package de.tudarmstadt.informatik.tk.assistance.sdk.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import de.greenrobot.dao.AbstractDao;
import de.greenrobot.dao.Property;
import de.greenrobot.dao.internal.DaoConfig;

import de.tudarmstadt.informatik.tk.assistance.sdk.db.DbUser;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "user".
*/
public class DbUserDao extends AbstractDao<DbUser, Long> {

    public static final String TABLENAME = "user";

    /**
     * Properties of entity DbUser.<br/>
     * Can be used for QueryBuilder and for referencing column names.
    */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Token = new Property(1, String.class, "token", false, "TOKEN");
        public final static Property Firstname = new Property(2, String.class, "firstname", false, "FIRSTNAME");
        public final static Property Lastname = new Property(3, String.class, "lastname", false, "LASTNAME");
        public final static Property PrimaryEmail = new Property(4, String.class, "primaryEmail", false, "PRIMARY_EMAIL");
        public final static Property UserpicFilename = new Property(5, String.class, "userpicFilename", false, "USERPIC_FILENAME");
        public final static Property LastLogin = new Property(6, String.class, "lastLogin", false, "LAST_LOGIN");
        public final static Property JoinedSince = new Property(7, String.class, "joinedSince", false, "JOINED_SINCE");
        public final static Property Uuid = new Property(8, String.class, "uuid", false, "UUID");
        public final static Property Created = new Property(9, String.class, "created", false, "CREATED");
    };

    private DaoSession daoSession;


    public DbUserDao(DaoConfig config) {
        super(config);
    }
    
    public DbUserDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(SQLiteDatabase db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"user\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TOKEN\" TEXT NOT NULL ," + // 1: token
                "\"FIRSTNAME\" TEXT," + // 2: firstname
                "\"LASTNAME\" TEXT," + // 3: lastname
                "\"PRIMARY_EMAIL\" TEXT NOT NULL ," + // 4: primaryEmail
                "\"USERPIC_FILENAME\" TEXT," + // 5: userpicFilename
                "\"LAST_LOGIN\" TEXT," + // 6: lastLogin
                "\"JOINED_SINCE\" TEXT," + // 7: joinedSince
                "\"UUID\" TEXT," + // 8: uuid
                "\"CREATED\" TEXT NOT NULL );"); // 9: created
        // Add Indexes
        db.execSQL("CREATE INDEX " + constraint + "IDX_user__id ON user" +
                " (\"_id\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_user_TOKEN ON user" +
                " (\"TOKEN\");");
        db.execSQL("CREATE INDEX " + constraint + "IDX_user_PRIMARY_EMAIL ON user" +
                " (\"PRIMARY_EMAIL\");");
    }

    /** Drops the underlying database table. */
    public static void dropTable(SQLiteDatabase db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"user\"";
        db.execSQL(sql);
    }

    /** @inheritdoc */
    @Override
    protected void bindValues(SQLiteStatement stmt, DbUser entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindString(2, entity.getToken());
 
        String firstname = entity.getFirstname();
        if (firstname != null) {
            stmt.bindString(3, firstname);
        }
 
        String lastname = entity.getLastname();
        if (lastname != null) {
            stmt.bindString(4, lastname);
        }
        stmt.bindString(5, entity.getPrimaryEmail());
 
        String userpicFilename = entity.getUserpicFilename();
        if (userpicFilename != null) {
            stmt.bindString(6, userpicFilename);
        }
 
        String lastLogin = entity.getLastLogin();
        if (lastLogin != null) {
            stmt.bindString(7, lastLogin);
        }
 
        String joinedSince = entity.getJoinedSince();
        if (joinedSince != null) {
            stmt.bindString(8, joinedSince);
        }
 
        String uuid = entity.getUuid();
        if (uuid != null) {
            stmt.bindString(9, uuid);
        }
        stmt.bindString(10, entity.getCreated());
    }

    @Override
    protected void attachEntity(DbUser entity) {
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
    public DbUser readEntity(Cursor cursor, int offset) {
        DbUser entity = new DbUser( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getString(offset + 1), // token
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // firstname
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // lastname
            cursor.getString(offset + 4), // primaryEmail
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userpicFilename
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // lastLogin
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // joinedSince
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // uuid
            cursor.getString(offset + 9) // created
        );
        return entity;
    }
     
    /** @inheritdoc */
    @Override
    public void readEntity(Cursor cursor, DbUser entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setToken(cursor.getString(offset + 1));
        entity.setFirstname(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setLastname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPrimaryEmail(cursor.getString(offset + 4));
        entity.setUserpicFilename(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setLastLogin(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setJoinedSince(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setUuid(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setCreated(cursor.getString(offset + 9));
     }
    
    /** @inheritdoc */
    @Override
    protected Long updateKeyAfterInsert(DbUser entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    /** @inheritdoc */
    @Override
    public Long getKey(DbUser entity) {
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
