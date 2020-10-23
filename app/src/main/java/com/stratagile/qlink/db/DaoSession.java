package com.stratagile.qlink.db;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.stratagile.qlink.db.TopupTodoList;
import com.stratagile.qlink.db.BuySellSellTodo;
import com.stratagile.qlink.db.BnbWallet;
import com.stratagile.qlink.db.VpnEntity;
import com.stratagile.qlink.db.VpnServerRecord;
import com.stratagile.qlink.db.EthWallet;
import com.stratagile.qlink.db.BuySellBuyTodo;
import com.stratagile.qlink.db.DWCSession;
import com.stratagile.qlink.db.EosAccount;
import com.stratagile.qlink.db.RecordSave;
import com.stratagile.qlink.db.BtcWallet;
import com.stratagile.qlink.db.Wallet;
import com.stratagile.qlink.db.DefiSearchHistory;
import com.stratagile.qlink.db.SwapRecord;
import com.stratagile.qlink.db.QLCAccount;
import com.stratagile.qlink.db.EntrustTodo;
import com.stratagile.qlink.db.TransactionRecord;
import com.stratagile.qlink.db.UserAccount;

import com.stratagile.qlink.db.TopupTodoListDao;
import com.stratagile.qlink.db.BuySellSellTodoDao;
import com.stratagile.qlink.db.BnbWalletDao;
import com.stratagile.qlink.db.VpnEntityDao;
import com.stratagile.qlink.db.VpnServerRecordDao;
import com.stratagile.qlink.db.EthWalletDao;
import com.stratagile.qlink.db.BuySellBuyTodoDao;
import com.stratagile.qlink.db.DWCSessionDao;
import com.stratagile.qlink.db.EosAccountDao;
import com.stratagile.qlink.db.RecordSaveDao;
import com.stratagile.qlink.db.BtcWalletDao;
import com.stratagile.qlink.db.WalletDao;
import com.stratagile.qlink.db.DefiSearchHistoryDao;
import com.stratagile.qlink.db.SwapRecordDao;
import com.stratagile.qlink.db.QLCAccountDao;
import com.stratagile.qlink.db.EntrustTodoDao;
import com.stratagile.qlink.db.TransactionRecordDao;
import com.stratagile.qlink.db.UserAccountDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig topupTodoListDaoConfig;
    private final DaoConfig buySellSellTodoDaoConfig;
    private final DaoConfig bnbWalletDaoConfig;
    private final DaoConfig vpnEntityDaoConfig;
    private final DaoConfig vpnServerRecordDaoConfig;
    private final DaoConfig ethWalletDaoConfig;
    private final DaoConfig buySellBuyTodoDaoConfig;
    private final DaoConfig dWCSessionDaoConfig;
    private final DaoConfig eosAccountDaoConfig;
    private final DaoConfig recordSaveDaoConfig;
    private final DaoConfig btcWalletDaoConfig;
    private final DaoConfig walletDaoConfig;
    private final DaoConfig defiSearchHistoryDaoConfig;
    private final DaoConfig swapRecordDaoConfig;
    private final DaoConfig qLCAccountDaoConfig;
    private final DaoConfig entrustTodoDaoConfig;
    private final DaoConfig transactionRecordDaoConfig;
    private final DaoConfig userAccountDaoConfig;

    private final TopupTodoListDao topupTodoListDao;
    private final BuySellSellTodoDao buySellSellTodoDao;
    private final BnbWalletDao bnbWalletDao;
    private final VpnEntityDao vpnEntityDao;
    private final VpnServerRecordDao vpnServerRecordDao;
    private final EthWalletDao ethWalletDao;
    private final BuySellBuyTodoDao buySellBuyTodoDao;
    private final DWCSessionDao dWCSessionDao;
    private final EosAccountDao eosAccountDao;
    private final RecordSaveDao recordSaveDao;
    private final BtcWalletDao btcWalletDao;
    private final WalletDao walletDao;
    private final DefiSearchHistoryDao defiSearchHistoryDao;
    private final SwapRecordDao swapRecordDao;
    private final QLCAccountDao qLCAccountDao;
    private final EntrustTodoDao entrustTodoDao;
    private final TransactionRecordDao transactionRecordDao;
    private final UserAccountDao userAccountDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        topupTodoListDaoConfig = daoConfigMap.get(TopupTodoListDao.class).clone();
        topupTodoListDaoConfig.initIdentityScope(type);

        buySellSellTodoDaoConfig = daoConfigMap.get(BuySellSellTodoDao.class).clone();
        buySellSellTodoDaoConfig.initIdentityScope(type);

        bnbWalletDaoConfig = daoConfigMap.get(BnbWalletDao.class).clone();
        bnbWalletDaoConfig.initIdentityScope(type);

        vpnEntityDaoConfig = daoConfigMap.get(VpnEntityDao.class).clone();
        vpnEntityDaoConfig.initIdentityScope(type);

        vpnServerRecordDaoConfig = daoConfigMap.get(VpnServerRecordDao.class).clone();
        vpnServerRecordDaoConfig.initIdentityScope(type);

        ethWalletDaoConfig = daoConfigMap.get(EthWalletDao.class).clone();
        ethWalletDaoConfig.initIdentityScope(type);

        buySellBuyTodoDaoConfig = daoConfigMap.get(BuySellBuyTodoDao.class).clone();
        buySellBuyTodoDaoConfig.initIdentityScope(type);

        dWCSessionDaoConfig = daoConfigMap.get(DWCSessionDao.class).clone();
        dWCSessionDaoConfig.initIdentityScope(type);

        eosAccountDaoConfig = daoConfigMap.get(EosAccountDao.class).clone();
        eosAccountDaoConfig.initIdentityScope(type);

        recordSaveDaoConfig = daoConfigMap.get(RecordSaveDao.class).clone();
        recordSaveDaoConfig.initIdentityScope(type);

        btcWalletDaoConfig = daoConfigMap.get(BtcWalletDao.class).clone();
        btcWalletDaoConfig.initIdentityScope(type);

        walletDaoConfig = daoConfigMap.get(WalletDao.class).clone();
        walletDaoConfig.initIdentityScope(type);

        defiSearchHistoryDaoConfig = daoConfigMap.get(DefiSearchHistoryDao.class).clone();
        defiSearchHistoryDaoConfig.initIdentityScope(type);

        swapRecordDaoConfig = daoConfigMap.get(SwapRecordDao.class).clone();
        swapRecordDaoConfig.initIdentityScope(type);

        qLCAccountDaoConfig = daoConfigMap.get(QLCAccountDao.class).clone();
        qLCAccountDaoConfig.initIdentityScope(type);

        entrustTodoDaoConfig = daoConfigMap.get(EntrustTodoDao.class).clone();
        entrustTodoDaoConfig.initIdentityScope(type);

        transactionRecordDaoConfig = daoConfigMap.get(TransactionRecordDao.class).clone();
        transactionRecordDaoConfig.initIdentityScope(type);

        userAccountDaoConfig = daoConfigMap.get(UserAccountDao.class).clone();
        userAccountDaoConfig.initIdentityScope(type);

        topupTodoListDao = new TopupTodoListDao(topupTodoListDaoConfig, this);
        buySellSellTodoDao = new BuySellSellTodoDao(buySellSellTodoDaoConfig, this);
        bnbWalletDao = new BnbWalletDao(bnbWalletDaoConfig, this);
        vpnEntityDao = new VpnEntityDao(vpnEntityDaoConfig, this);
        vpnServerRecordDao = new VpnServerRecordDao(vpnServerRecordDaoConfig, this);
        ethWalletDao = new EthWalletDao(ethWalletDaoConfig, this);
        buySellBuyTodoDao = new BuySellBuyTodoDao(buySellBuyTodoDaoConfig, this);
        dWCSessionDao = new DWCSessionDao(dWCSessionDaoConfig, this);
        eosAccountDao = new EosAccountDao(eosAccountDaoConfig, this);
        recordSaveDao = new RecordSaveDao(recordSaveDaoConfig, this);
        btcWalletDao = new BtcWalletDao(btcWalletDaoConfig, this);
        walletDao = new WalletDao(walletDaoConfig, this);
        defiSearchHistoryDao = new DefiSearchHistoryDao(defiSearchHistoryDaoConfig, this);
        swapRecordDao = new SwapRecordDao(swapRecordDaoConfig, this);
        qLCAccountDao = new QLCAccountDao(qLCAccountDaoConfig, this);
        entrustTodoDao = new EntrustTodoDao(entrustTodoDaoConfig, this);
        transactionRecordDao = new TransactionRecordDao(transactionRecordDaoConfig, this);
        userAccountDao = new UserAccountDao(userAccountDaoConfig, this);

        registerDao(TopupTodoList.class, topupTodoListDao);
        registerDao(BuySellSellTodo.class, buySellSellTodoDao);
        registerDao(BnbWallet.class, bnbWalletDao);
        registerDao(VpnEntity.class, vpnEntityDao);
        registerDao(VpnServerRecord.class, vpnServerRecordDao);
        registerDao(EthWallet.class, ethWalletDao);
        registerDao(BuySellBuyTodo.class, buySellBuyTodoDao);
        registerDao(DWCSession.class, dWCSessionDao);
        registerDao(EosAccount.class, eosAccountDao);
        registerDao(RecordSave.class, recordSaveDao);
        registerDao(BtcWallet.class, btcWalletDao);
        registerDao(Wallet.class, walletDao);
        registerDao(DefiSearchHistory.class, defiSearchHistoryDao);
        registerDao(SwapRecord.class, swapRecordDao);
        registerDao(QLCAccount.class, qLCAccountDao);
        registerDao(EntrustTodo.class, entrustTodoDao);
        registerDao(TransactionRecord.class, transactionRecordDao);
        registerDao(UserAccount.class, userAccountDao);
    }
    
    public void clear() {
        topupTodoListDaoConfig.clearIdentityScope();
        buySellSellTodoDaoConfig.clearIdentityScope();
        bnbWalletDaoConfig.clearIdentityScope();
        vpnEntityDaoConfig.clearIdentityScope();
        vpnServerRecordDaoConfig.clearIdentityScope();
        ethWalletDaoConfig.clearIdentityScope();
        buySellBuyTodoDaoConfig.clearIdentityScope();
        dWCSessionDaoConfig.clearIdentityScope();
        eosAccountDaoConfig.clearIdentityScope();
        recordSaveDaoConfig.clearIdentityScope();
        btcWalletDaoConfig.clearIdentityScope();
        walletDaoConfig.clearIdentityScope();
        defiSearchHistoryDaoConfig.clearIdentityScope();
        swapRecordDaoConfig.clearIdentityScope();
        qLCAccountDaoConfig.clearIdentityScope();
        entrustTodoDaoConfig.clearIdentityScope();
        transactionRecordDaoConfig.clearIdentityScope();
        userAccountDaoConfig.clearIdentityScope();
    }

    public TopupTodoListDao getTopupTodoListDao() {
        return topupTodoListDao;
    }

    public BuySellSellTodoDao getBuySellSellTodoDao() {
        return buySellSellTodoDao;
    }

    public BnbWalletDao getBnbWalletDao() {
        return bnbWalletDao;
    }

    public VpnEntityDao getVpnEntityDao() {
        return vpnEntityDao;
    }

    public VpnServerRecordDao getVpnServerRecordDao() {
        return vpnServerRecordDao;
    }

    public EthWalletDao getEthWalletDao() {
        return ethWalletDao;
    }

    public BuySellBuyTodoDao getBuySellBuyTodoDao() {
        return buySellBuyTodoDao;
    }

    public DWCSessionDao getDWCSessionDao() {
        return dWCSessionDao;
    }

    public EosAccountDao getEosAccountDao() {
        return eosAccountDao;
    }

    public RecordSaveDao getRecordSaveDao() {
        return recordSaveDao;
    }

    public BtcWalletDao getBtcWalletDao() {
        return btcWalletDao;
    }

    public WalletDao getWalletDao() {
        return walletDao;
    }

    public DefiSearchHistoryDao getDefiSearchHistoryDao() {
        return defiSearchHistoryDao;
    }

    public SwapRecordDao getSwapRecordDao() {
        return swapRecordDao;
    }

    public QLCAccountDao getQLCAccountDao() {
        return qLCAccountDao;
    }

    public EntrustTodoDao getEntrustTodoDao() {
        return entrustTodoDao;
    }

    public TransactionRecordDao getTransactionRecordDao() {
        return transactionRecordDao;
    }

    public UserAccountDao getUserAccountDao() {
        return userAccountDao;
    }

}
