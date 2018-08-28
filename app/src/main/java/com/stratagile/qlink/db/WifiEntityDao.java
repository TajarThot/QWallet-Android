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
 * DAO for table "WIFI_ENTITY".
*/
public class WifiEntityDao extends AbstractDao<WifiEntity, Long> {

    public static final String TABLENAME = "WIFI_ENTITY";

    /**
     * Properties of entity WifiEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Ssid = new Property(1, String.class, "ssid", false, "SSID");
        public final static Property FreindNum = new Property(2, String.class, "freindNum", false, "FREIND_NUM");
        public final static Property GroupNum = new Property(3, int.class, "groupNum", false, "GROUP_NUM");
        public final static Property Avatar = new Property(4, String.class, "avatar", false, "AVATAR");
        public final static Property UnReadMessageCount = new Property(5, int.class, "unReadMessageCount", false, "UN_READ_MESSAGE_COUNT");
        public final static Property MacAdrees = new Property(6, String.class, "macAdrees", false, "MAC_ADREES");
        public final static Property LastFindTimeStamp = new Property(7, String.class, "lastFindTimeStamp", false, "LAST_FIND_TIME_STAMP");
        public final static Property Level = new Property(8, int.class, "level", false, "LEVEL");
        public final static Property IsRegiste = new Property(9, boolean.class, "isRegiste", false, "IS_REGISTE");
        public final static Property IsRegisteByMe = new Property(10, boolean.class, "isRegisteByMe", false, "IS_REGISTE_BY_ME");
        public final static Property PaymentType = new Property(11, int.class, "paymentType", false, "PAYMENT_TYPE");
        public final static Property PriceInQlc = new Property(12, float.class, "priceInQlc", false, "PRICE_IN_QLC");
        public final static Property TimeLimitPerDevice = new Property(13, int.class, "timeLimitPerDevice", false, "TIME_LIMIT_PER_DEVICE");
        public final static Property DailyTotalTimeLimit = new Property(14, int.class, "dailyTotalTimeLimit", false, "DAILY_TOTAL_TIME_LIMIT");
        public final static Property Capabilities = new Property(15, String.class, "capabilities", false, "CAPABILITIES");
        public final static Property PriceMode = new Property(16, int.class, "priceMode", false, "PRICE_MODE");
        public final static Property DeviceAllowed = new Property(17, int.class, "deviceAllowed", false, "DEVICE_ALLOWED");
        public final static Property ConnectCount = new Property(18, int.class, "connectCount", false, "CONNECT_COUNT");
        public final static Property Longitude = new Property(19, float.class, "longitude", false, "LONGITUDE");
        public final static Property Latitude = new Property(20, float.class, "latitude", false, "LATITUDE");
        public final static Property IsFriend = new Property(21, boolean.class, "isFriend", false, "IS_FRIEND");
        public final static Property OwnerP2PId = new Property(22, String.class, "ownerP2PId", false, "OWNER_P2_PID");
        public final static Property WifiPassword = new Property(23, String.class, "wifiPassword", false, "WIFI_PASSWORD");
        public final static Property Online = new Property(24, boolean.class, "online", false, "ONLINE");
        public final static Property AvaterUpdateTime = new Property(25, long.class, "avaterUpdateTime", false, "AVATER_UPDATE_TIME");
        public final static Property IsLoadingAvater = new Property(26, boolean.class, "isLoadingAvater", false, "IS_LOADING_AVATER");
        public final static Property WalletAddress = new Property(27, String.class, "walletAddress", false, "WALLET_ADDRESS");
        public final static Property IsConnected = new Property(28, boolean.class, "isConnected", false, "IS_CONNECTED");
        public final static Property AssetTranfer = new Property(29, double.class, "assetTranfer", false, "ASSET_TRANFER");
        public final static Property RegisterQlc = new Property(30, double.class, "registerQlc", false, "REGISTER_QLC");
        public final static Property IsInMainWallet = new Property(31, boolean.class, "isInMainWallet", false, "IS_IN_MAIN_WALLET");
    }


    public WifiEntityDao(DaoConfig config) {
        super(config);
    }
    
    public WifiEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"WIFI_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SSID\" TEXT," + // 1: ssid
                "\"FREIND_NUM\" TEXT," + // 2: freindNum
                "\"GROUP_NUM\" INTEGER NOT NULL ," + // 3: groupNum
                "\"AVATAR\" TEXT," + // 4: avatar
                "\"UN_READ_MESSAGE_COUNT\" INTEGER NOT NULL ," + // 5: unReadMessageCount
                "\"MAC_ADREES\" TEXT," + // 6: macAdrees
                "\"LAST_FIND_TIME_STAMP\" TEXT," + // 7: lastFindTimeStamp
                "\"LEVEL\" INTEGER NOT NULL ," + // 8: level
                "\"IS_REGISTE\" INTEGER NOT NULL ," + // 9: isRegiste
                "\"IS_REGISTE_BY_ME\" INTEGER NOT NULL ," + // 10: isRegisteByMe
                "\"PAYMENT_TYPE\" INTEGER NOT NULL ," + // 11: paymentType
                "\"PRICE_IN_QLC\" REAL NOT NULL ," + // 12: priceInQlc
                "\"TIME_LIMIT_PER_DEVICE\" INTEGER NOT NULL ," + // 13: timeLimitPerDevice
                "\"DAILY_TOTAL_TIME_LIMIT\" INTEGER NOT NULL ," + // 14: dailyTotalTimeLimit
                "\"CAPABILITIES\" TEXT," + // 15: capabilities
                "\"PRICE_MODE\" INTEGER NOT NULL ," + // 16: priceMode
                "\"DEVICE_ALLOWED\" INTEGER NOT NULL ," + // 17: deviceAllowed
                "\"CONNECT_COUNT\" INTEGER NOT NULL ," + // 18: connectCount
                "\"LONGITUDE\" REAL NOT NULL ," + // 19: longitude
                "\"LATITUDE\" REAL NOT NULL ," + // 20: latitude
                "\"IS_FRIEND\" INTEGER NOT NULL ," + // 21: isFriend
                "\"OWNER_P2_PID\" TEXT," + // 22: ownerP2PId
                "\"WIFI_PASSWORD\" TEXT," + // 23: wifiPassword
                "\"ONLINE\" INTEGER NOT NULL ," + // 24: online
                "\"AVATER_UPDATE_TIME\" INTEGER NOT NULL ," + // 25: avaterUpdateTime
                "\"IS_LOADING_AVATER\" INTEGER NOT NULL ," + // 26: isLoadingAvater
                "\"WALLET_ADDRESS\" TEXT," + // 27: walletAddress
                "\"IS_CONNECTED\" INTEGER NOT NULL ," + // 28: isConnected
                "\"ASSET_TRANFER\" REAL NOT NULL ," + // 29: assetTranfer
                "\"REGISTER_QLC\" REAL NOT NULL ," + // 30: registerQlc
                "\"IS_IN_MAIN_WALLET\" INTEGER NOT NULL );"); // 31: isInMainWallet
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"WIFI_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, WifiEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String ssid = entity.getSsid();
        if (ssid != null) {
            stmt.bindString(2, ssid);
        }
 
        String freindNum = entity.getFreindNum();
        if (freindNum != null) {
            stmt.bindString(3, freindNum);
        }
        stmt.bindLong(4, entity.getGroupNum());
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
        stmt.bindLong(6, entity.getUnReadMessageCount());
 
        String macAdrees = entity.getMacAdrees();
        if (macAdrees != null) {
            stmt.bindString(7, macAdrees);
        }
 
        String lastFindTimeStamp = entity.getLastFindTimeStamp();
        if (lastFindTimeStamp != null) {
            stmt.bindString(8, lastFindTimeStamp);
        }
        stmt.bindLong(9, entity.getLevel());
        stmt.bindLong(10, entity.getIsRegiste() ? 1L: 0L);
        stmt.bindLong(11, entity.getIsRegisteByMe() ? 1L: 0L);
        stmt.bindLong(12, entity.getPaymentType());
        stmt.bindDouble(13, entity.getPriceInQlc());
        stmt.bindLong(14, entity.getTimeLimitPerDevice());
        stmt.bindLong(15, entity.getDailyTotalTimeLimit());
 
        String capabilities = entity.getCapabilities();
        if (capabilities != null) {
            stmt.bindString(16, capabilities);
        }
        stmt.bindLong(17, entity.getPriceMode());
        stmt.bindLong(18, entity.getDeviceAllowed());
        stmt.bindLong(19, entity.getConnectCount());
        stmt.bindDouble(20, entity.getLongitude());
        stmt.bindDouble(21, entity.getLatitude());
        stmt.bindLong(22, entity.getIsFriend() ? 1L: 0L);
 
        String ownerP2PId = entity.getOwnerP2PId();
        if (ownerP2PId != null) {
            stmt.bindString(23, ownerP2PId);
        }
 
        String wifiPassword = entity.getWifiPassword();
        if (wifiPassword != null) {
            stmt.bindString(24, wifiPassword);
        }
        stmt.bindLong(25, entity.getOnline() ? 1L: 0L);
        stmt.bindLong(26, entity.getAvaterUpdateTime());
        stmt.bindLong(27, entity.getIsLoadingAvater() ? 1L: 0L);
 
        String walletAddress = entity.getWalletAddress();
        if (walletAddress != null) {
            stmt.bindString(28, walletAddress);
        }
        stmt.bindLong(29, entity.getIsConnected() ? 1L: 0L);
        stmt.bindDouble(30, entity.getAssetTranfer());
        stmt.bindDouble(31, entity.getRegisterQlc());
        stmt.bindLong(32, entity.getIsInMainWallet() ? 1L: 0L);
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, WifiEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String ssid = entity.getSsid();
        if (ssid != null) {
            stmt.bindString(2, ssid);
        }
 
        String freindNum = entity.getFreindNum();
        if (freindNum != null) {
            stmt.bindString(3, freindNum);
        }
        stmt.bindLong(4, entity.getGroupNum());
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(5, avatar);
        }
        stmt.bindLong(6, entity.getUnReadMessageCount());
 
        String macAdrees = entity.getMacAdrees();
        if (macAdrees != null) {
            stmt.bindString(7, macAdrees);
        }
 
        String lastFindTimeStamp = entity.getLastFindTimeStamp();
        if (lastFindTimeStamp != null) {
            stmt.bindString(8, lastFindTimeStamp);
        }
        stmt.bindLong(9, entity.getLevel());
        stmt.bindLong(10, entity.getIsRegiste() ? 1L: 0L);
        stmt.bindLong(11, entity.getIsRegisteByMe() ? 1L: 0L);
        stmt.bindLong(12, entity.getPaymentType());
        stmt.bindDouble(13, entity.getPriceInQlc());
        stmt.bindLong(14, entity.getTimeLimitPerDevice());
        stmt.bindLong(15, entity.getDailyTotalTimeLimit());
 
        String capabilities = entity.getCapabilities();
        if (capabilities != null) {
            stmt.bindString(16, capabilities);
        }
        stmt.bindLong(17, entity.getPriceMode());
        stmt.bindLong(18, entity.getDeviceAllowed());
        stmt.bindLong(19, entity.getConnectCount());
        stmt.bindDouble(20, entity.getLongitude());
        stmt.bindDouble(21, entity.getLatitude());
        stmt.bindLong(22, entity.getIsFriend() ? 1L: 0L);
 
        String ownerP2PId = entity.getOwnerP2PId();
        if (ownerP2PId != null) {
            stmt.bindString(23, ownerP2PId);
        }
 
        String wifiPassword = entity.getWifiPassword();
        if (wifiPassword != null) {
            stmt.bindString(24, wifiPassword);
        }
        stmt.bindLong(25, entity.getOnline() ? 1L: 0L);
        stmt.bindLong(26, entity.getAvaterUpdateTime());
        stmt.bindLong(27, entity.getIsLoadingAvater() ? 1L: 0L);
 
        String walletAddress = entity.getWalletAddress();
        if (walletAddress != null) {
            stmt.bindString(28, walletAddress);
        }
        stmt.bindLong(29, entity.getIsConnected() ? 1L: 0L);
        stmt.bindDouble(30, entity.getAssetTranfer());
        stmt.bindDouble(31, entity.getRegisterQlc());
        stmt.bindLong(32, entity.getIsInMainWallet() ? 1L: 0L);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public WifiEntity readEntity(Cursor cursor, int offset) {
        WifiEntity entity = new WifiEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // ssid
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // freindNum
            cursor.getInt(offset + 3), // groupNum
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // avatar
            cursor.getInt(offset + 5), // unReadMessageCount
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // macAdrees
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // lastFindTimeStamp
            cursor.getInt(offset + 8), // level
            cursor.getShort(offset + 9) != 0, // isRegiste
            cursor.getShort(offset + 10) != 0, // isRegisteByMe
            cursor.getInt(offset + 11), // paymentType
            cursor.getFloat(offset + 12), // priceInQlc
            cursor.getInt(offset + 13), // timeLimitPerDevice
            cursor.getInt(offset + 14), // dailyTotalTimeLimit
            cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15), // capabilities
            cursor.getInt(offset + 16), // priceMode
            cursor.getInt(offset + 17), // deviceAllowed
            cursor.getInt(offset + 18), // connectCount
            cursor.getFloat(offset + 19), // longitude
            cursor.getFloat(offset + 20), // latitude
            cursor.getShort(offset + 21) != 0, // isFriend
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // ownerP2PId
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // wifiPassword
            cursor.getShort(offset + 24) != 0, // online
            cursor.getLong(offset + 25), // avaterUpdateTime
            cursor.getShort(offset + 26) != 0, // isLoadingAvater
            cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27), // walletAddress
            cursor.getShort(offset + 28) != 0, // isConnected
            cursor.getDouble(offset + 29), // assetTranfer
            cursor.getDouble(offset + 30), // registerQlc
            cursor.getShort(offset + 31) != 0 // isInMainWallet
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, WifiEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSsid(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setFreindNum(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setGroupNum(cursor.getInt(offset + 3));
        entity.setAvatar(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setUnReadMessageCount(cursor.getInt(offset + 5));
        entity.setMacAdrees(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setLastFindTimeStamp(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLevel(cursor.getInt(offset + 8));
        entity.setIsRegiste(cursor.getShort(offset + 9) != 0);
        entity.setIsRegisteByMe(cursor.getShort(offset + 10) != 0);
        entity.setPaymentType(cursor.getInt(offset + 11));
        entity.setPriceInQlc(cursor.getFloat(offset + 12));
        entity.setTimeLimitPerDevice(cursor.getInt(offset + 13));
        entity.setDailyTotalTimeLimit(cursor.getInt(offset + 14));
        entity.setCapabilities(cursor.isNull(offset + 15) ? null : cursor.getString(offset + 15));
        entity.setPriceMode(cursor.getInt(offset + 16));
        entity.setDeviceAllowed(cursor.getInt(offset + 17));
        entity.setConnectCount(cursor.getInt(offset + 18));
        entity.setLongitude(cursor.getFloat(offset + 19));
        entity.setLatitude(cursor.getFloat(offset + 20));
        entity.setIsFriend(cursor.getShort(offset + 21) != 0);
        entity.setOwnerP2PId(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setWifiPassword(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setOnline(cursor.getShort(offset + 24) != 0);
        entity.setAvaterUpdateTime(cursor.getLong(offset + 25));
        entity.setIsLoadingAvater(cursor.getShort(offset + 26) != 0);
        entity.setWalletAddress(cursor.isNull(offset + 27) ? null : cursor.getString(offset + 27));
        entity.setIsConnected(cursor.getShort(offset + 28) != 0);
        entity.setAssetTranfer(cursor.getDouble(offset + 29));
        entity.setRegisterQlc(cursor.getDouble(offset + 30));
        entity.setIsInMainWallet(cursor.getShort(offset + 31) != 0);
     }
    
    @Override
    protected final Long updateKeyAfterInsert(WifiEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(WifiEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(WifiEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}