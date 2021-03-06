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
 * DAO for table "USER_ACCOUNT".
*/
public class UserAccountDao extends AbstractDao<UserAccount, Long> {

    public static final String TABLENAME = "USER_ACCOUNT";

    /**
     * Properties of entity UserAccount.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Account = new Property(1, String.class, "account", false, "ACCOUNT");
        public final static Property Password = new Property(2, String.class, "password", false, "PASSWORD");
        public final static Property PubKey = new Property(3, String.class, "pubKey", false, "PUB_KEY");
        public final static Property IsLogin = new Property(4, boolean.class, "isLogin", false, "IS_LOGIN");
        public final static Property InviteCode = new Property(5, String.class, "inviteCode", false, "INVITE_CODE");
        public final static Property UserName = new Property(6, String.class, "userName", false, "USER_NAME");
        public final static Property Avatar = new Property(7, String.class, "avatar", false, "AVATAR");
        public final static Property Test = new Property(8, String.class, "test", false, "TEST");
        public final static Property BindDate = new Property(9, String.class, "bindDate", false, "BIND_DATE");
        public final static Property Phone = new Property(10, String.class, "phone", false, "PHONE");
        public final static Property UserId = new Property(11, String.class, "userId", false, "USER_ID");
        public final static Property Email = new Property(12, String.class, "email", false, "EMAIL");
        public final static Property Vstatus = new Property(13, String.class, "vstatus", false, "VSTATUS");
        public final static Property FacePhoto = new Property(14, String.class, "facePhoto", false, "FACE_PHOTO");
        public final static Property TotalInvite = new Property(15, int.class, "totalInvite", false, "TOTAL_INVITE");
        public final static Property QlcAddress = new Property(16, String.class, "qlcAddress", false, "QLC_ADDRESS");
        public final static Property StartApp = new Property(17, boolean.class, "startApp", false, "START_APP");
        public final static Property HoldingPhoto = new Property(18, String.class, "holdingPhoto", false, "HOLDING_PHOTO");
    }


    public UserAccountDao(DaoConfig config) {
        super(config);
    }
    
    public UserAccountDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_ACCOUNT\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"ACCOUNT\" TEXT," + // 1: account
                "\"PASSWORD\" TEXT," + // 2: password
                "\"PUB_KEY\" TEXT," + // 3: pubKey
                "\"IS_LOGIN\" INTEGER NOT NULL ," + // 4: isLogin
                "\"INVITE_CODE\" TEXT," + // 5: inviteCode
                "\"USER_NAME\" TEXT," + // 6: userName
                "\"AVATAR\" TEXT," + // 7: avatar
                "\"TEST\" TEXT," + // 8: test
                "\"BIND_DATE\" TEXT," + // 9: bindDate
                "\"PHONE\" TEXT," + // 10: phone
                "\"USER_ID\" TEXT," + // 11: userId
                "\"EMAIL\" TEXT," + // 12: email
                "\"VSTATUS\" TEXT," + // 13: vstatus
                "\"FACE_PHOTO\" TEXT," + // 14: facePhoto
                "\"TOTAL_INVITE\" INTEGER NOT NULL ," + // 15: totalInvite
                "\"QLC_ADDRESS\" TEXT," + // 16: qlcAddress
                "\"START_APP\" INTEGER NOT NULL ," + // 17: startApp
                "\"HOLDING_PHOTO\" TEXT);"); // 18: holdingPhoto
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_ACCOUNT\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserAccount entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String pubKey = entity.getPubKey();
        if (pubKey != null) {
            stmt.bindString(4, pubKey);
        }
        stmt.bindLong(5, entity.getIsLogin() ? 1L: 0L);
 
        String inviteCode = entity.getInviteCode();
        if (inviteCode != null) {
            stmt.bindString(6, inviteCode);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(8, avatar);
        }
 
        String test = entity.getTest();
        if (test != null) {
            stmt.bindString(9, test);
        }
 
        String bindDate = entity.getBindDate();
        if (bindDate != null) {
            stmt.bindString(10, bindDate);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(11, phone);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(12, userId);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(13, email);
        }
 
        String vstatus = entity.getVstatus();
        if (vstatus != null) {
            stmt.bindString(14, vstatus);
        }
 
        String facePhoto = entity.getFacePhoto();
        if (facePhoto != null) {
            stmt.bindString(15, facePhoto);
        }
        stmt.bindLong(16, entity.getTotalInvite());
 
        String qlcAddress = entity.getQlcAddress();
        if (qlcAddress != null) {
            stmt.bindString(17, qlcAddress);
        }
        stmt.bindLong(18, entity.getStartApp() ? 1L: 0L);
 
        String holdingPhoto = entity.getHoldingPhoto();
        if (holdingPhoto != null) {
            stmt.bindString(19, holdingPhoto);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserAccount entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String account = entity.getAccount();
        if (account != null) {
            stmt.bindString(2, account);
        }
 
        String password = entity.getPassword();
        if (password != null) {
            stmt.bindString(3, password);
        }
 
        String pubKey = entity.getPubKey();
        if (pubKey != null) {
            stmt.bindString(4, pubKey);
        }
        stmt.bindLong(5, entity.getIsLogin() ? 1L: 0L);
 
        String inviteCode = entity.getInviteCode();
        if (inviteCode != null) {
            stmt.bindString(6, inviteCode);
        }
 
        String userName = entity.getUserName();
        if (userName != null) {
            stmt.bindString(7, userName);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(8, avatar);
        }
 
        String test = entity.getTest();
        if (test != null) {
            stmt.bindString(9, test);
        }
 
        String bindDate = entity.getBindDate();
        if (bindDate != null) {
            stmt.bindString(10, bindDate);
        }
 
        String phone = entity.getPhone();
        if (phone != null) {
            stmt.bindString(11, phone);
        }
 
        String userId = entity.getUserId();
        if (userId != null) {
            stmt.bindString(12, userId);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(13, email);
        }
 
        String vstatus = entity.getVstatus();
        if (vstatus != null) {
            stmt.bindString(14, vstatus);
        }
 
        String facePhoto = entity.getFacePhoto();
        if (facePhoto != null) {
            stmt.bindString(15, facePhoto);
        }
        stmt.bindLong(16, entity.getTotalInvite());
 
        String qlcAddress = entity.getQlcAddress();
        if (qlcAddress != null) {
            stmt.bindString(17, qlcAddress);
        }
        stmt.bindLong(18, entity.getStartApp() ? 1L: 0L);
 
        String holdingPhoto = entity.getHoldingPhoto();
        if (holdingPhoto != null) {
            stmt.bindString(19, holdingPhoto);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserAccount readEntity(Cursor cursor, int offset) {
        UserAccount entity = new UserAccount( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // account
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // password
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // pubKey
            cursor.getShort(offset + 4) != 0, // isLogin
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // inviteCode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // userName
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // avatar
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // test
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // bindDate
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // phone
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // userId
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // email
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // vstatus
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // facePhoto
            cursor.getInt(offset + 15), // totalInvite
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // qlcAddress
            cursor.getShort(offset + 17) != 0, // startApp
            cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18) // holdingPhoto
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserAccount entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setAccount(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPassword(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setPubKey(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setIsLogin(cursor.getShort(offset + 4) != 0);
        entity.setInviteCode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setUserName(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setAvatar(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setTest(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setBindDate(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPhone(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setUserId(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setEmail(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setVstatus(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setFacePhoto(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setTotalInvite(cursor.getInt(offset + 15));
        entity.setQlcAddress(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setStartApp(cursor.getShort(offset + 17) != 0);
        entity.setHoldingPhoto(cursor.isNull(offset + 18) ? null : cursor.getString(offset + 18));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserAccount entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserAccount entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserAccount entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
