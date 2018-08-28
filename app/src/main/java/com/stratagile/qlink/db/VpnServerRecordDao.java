package com.stratagile.qlink.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VPN_SERVER_RECORD".
*/
public class VpnServerRecordDao extends AbstractDao<VpnServerRecord, Long> {

    public static final String TABLENAME = "VPN_SERVER_RECORD";

    /**
     * Properties of entity VpnServerRecord.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property P2pId = new Property(1, String.class, "p2pId", false, "P2P_ID");
        public final static Property VpnName = new Property(2, String.class, "vpnName", false, "VPN_NAME");
        public final static Property VpnfileName = new Property(3, String.class, "vpnfileName", false, "VPNFILE_NAME");
        public final static Property UserName = new Property(4, String.class, "userName", false, "USER_NAME");
        public final static Property Password = new Property(5, String.class, "password", false, "PASSWORD");
        public final static Property PrivateKey = new Property(6, String.class, "privateKey", false, "PRIVATE_KEY");
        public final static Property IsMainNet = new Property(7, boolean.class, "isMainNet", false, "IS_MAIN_NET");
    }


    public VpnServerRecordDao(DaoConfig config) {
        super(config);
    }
    
    public VpnServerRecordDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VPN_SERVER_RECORD\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"P2P_ID\" TEXT," + // 1: p2pId
                "\"VPN_NAME\" TEXT," + // 2: vpnName
                "\"VPNFILE_NAME\" TEXT," + // 3: vpnfileName
                "\"USER_NAME\" TEXT," + // 4: userName
                "\"PASSWORD\" TEXT," + // 5: password
                "\"PRIVATE_KEY\" TEXT," + // 6: privateKey
                "\"IS_MAIN_NET\" INTEGER NOT NULL );"); // 7: isMainNet
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VPN_SERVER_RECORD\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VpnServerRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String p2pId = entity.getP2pId();
        if (p2pId != null) {
            stmt.bindString(2, p2pId);
        }
 
        String vpnName = entity.getVpnName();
        if (vpnName != null) {
            stmt.bindString(3, vpnName);
        }
 
        String vpnfileName = entity.getVpnfileName();
        if (vpnfileName != null) {
            stmt.bindString(4, vpnfileName);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(5, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String privateKey = entity.getPrivateKey();
        if (privateKey != null) {
            stmt.bindString(7, privateKey);
        }
        stmt.bindLong(8, entity.getIsMainNet() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VpnServerRecord entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String p2pId = entity.getP2pId();
        if (p2pId != null) {
            stmt.bindString(2, p2pId);
        }
 
        String vpnName = entity.getVpnName();
        if (vpnName != null) {
            stmt.bindString(3, vpnName);
        }
 
        String vpnfileName = entity.getVpnfileName();
        if (vpnfileName != null) {
            stmt.bindString(4, vpnfileName);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(5, userName);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(6, password);
        }
 
        String privateKey = entity.getPrivateKey();
        if (privateKey != null) {
            stmt.bindString(7, privateKey);
        }
        stmt.bindLong(8, entity.getIsMainNet() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VpnServerRecord readEntity(Cursor cursor, int offset) {
        VpnServerRecord entity = new VpnServerRecord( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // p2pId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // vpnName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // vpnfileName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // userName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // password
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // privateKey
            cursor.getShort(offset + 7) != 0 // isMainNet
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VpnServerRecord entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setP2pId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setVpnName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setVpnfileName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setUserName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPassword(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setPrivateKey(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setIsMainNet(cursor.getShort(offset + 7) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VpnServerRecord entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VpnServerRecord entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VpnServerRecord entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}