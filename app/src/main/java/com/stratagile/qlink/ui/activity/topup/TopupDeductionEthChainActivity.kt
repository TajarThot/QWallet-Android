package com.stratagile.qlink.ui.activity.topup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import com.pawegio.kandroid.alert
import com.pawegio.kandroid.toast
import com.socks.library.KLog
import com.stratagile.qlc.QLCAPI
import com.stratagile.qlc.entity.QlcTokenbalance
import com.stratagile.qlink.R

import com.stratagile.qlink.application.AppConfig
import com.stratagile.qlink.base.BaseActivity
import com.stratagile.qlink.constant.ConstantValue
import com.stratagile.qlink.db.EthWallet
import com.stratagile.qlink.db.QLCAccount
import com.stratagile.qlink.entity.AllWallet
import com.stratagile.qlink.entity.EthWalletDetail
import com.stratagile.qlink.entity.EthWalletInfo
import com.stratagile.qlink.entity.SwitchToOtc
import com.stratagile.qlink.entity.topup.TopupOrder
import com.stratagile.qlink.ui.activity.main.WebViewActivity
import com.stratagile.qlink.ui.activity.otc.OtcChooseWalletActivity
import com.stratagile.qlink.ui.activity.topup.component.DaggerTopupDeductionEthChainComponent
import com.stratagile.qlink.ui.activity.topup.contract.TopupDeductionEthChainContract
import com.stratagile.qlink.ui.activity.topup.module.TopupDeductionEthChainModule
import com.stratagile.qlink.ui.activity.topup.presenter.TopupDeductionEthChainPresenter
import com.stratagile.qlink.utils.*
import com.stratagile.qlink.view.SweetAlertDialog
import kotlinx.android.synthetic.main.activity_topup_deduction_eth_chain.*
import kotlinx.android.synthetic.main.activity_topup_eth_pay.*
import kotlinx.android.synthetic.main.activity_topup_eth_pay.etEthTokenSendMemo
import kotlinx.android.synthetic.main.activity_topup_eth_pay.llSelectQlcWallet
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvAmountQgas
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvQGASBalance
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvQlcWalletAddess
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvQlcWalletName
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvReceiveAddress
import kotlinx.android.synthetic.main.activity_topup_eth_pay.tvSend
import org.greenrobot.eventbus.EventBus
import org.web3j.utils.Convert
import java.io.File
import java.lang.Error
import java.math.BigDecimal
import java.util.*

import javax.inject.Inject;
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

/**
 * @author hzp
 * @Package com.stratagile.qlink.ui.activity.topup
 * @Description: $description
 * @date 2019/12/27 11:59:29
 */

class TopupDeductionEthChainActivity : BaseActivity(), TopupDeductionEthChainContract.View {

    @Inject
    internal lateinit var mPresenter: TopupDeductionEthChainPresenter

    lateinit var deductionTokensBean : EthWalletInfo.DataBean.TokensBean

    override fun onCreate(savedInstanceState: Bundle?) {
        mainColor = R.color.white
        super.onCreate(savedInstanceState)
    }

    override fun sendPayTokenSuccess(txid: String) {
        tvPaying.text = getString(R.string.qgas_transferred, topupOrderBean.symbol)
        ivLoad1.clearAnimation()
        sa1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {

            }

        })
        saHalf.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {

            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        ivLoad1.setImageResource(R.mipmap.background_success)
        showChangeAnimation(ivLoad1)
        showViewAnimation(view2)

        thread {
            Thread.sleep(5000)
            saveDeductionTokenTxid(txid, topupOrderBean.id)
        }
    }

    override fun getEthWalletBack(ethWalletInfo: EthWalletInfo) {
        if ("false".equals(ethWalletInfo.data.eth.balance.toString())) {
            toast(getString(R.string.not_enough) + "ETH")
        } else if ("-1".equals(ethWalletInfo.data.eth.balance.toString())){
            toast(getString(R.string.not_enough) + "ETH")
        } else {
            ethCount = ethWalletInfo.data.eth.balance.toString().toBigDecimal()
        }

        ethWalletInfo.data.tokens.forEach {
            ethWalletInfo.data.tokens.forEach {
                if (topupOrderBean.symbol.equals(it.tokenInfo.symbol, true)) {
                    deductionTokensBean = it
                    payTokenBalance = it.balance.toBigDecimal().divide(10.toBigDecimal().pow(it.tokenInfo.decimals.toInt()), it.tokenInfo.decimals.toInt(), BigDecimal.ROUND_HALF_UP)
                    tvQGASBalance.text = getString(R.string.balance) + " " + payTokenBalance.stripTrailingZeros().toPlainString()
                }
            }
        }
    }


    override fun initView() {
        setContentView(R.layout.activity_topup_deduction_eth_chain)
    }

    override fun setupActivityComponent() {
       DaggerTopupDeductionEthChainComponent
               .builder()
               .appComponent((application as AppConfig).applicationComponent)
               .topupDeductionEthChainModule(TopupDeductionEthChainModule(this))
               .build()
               .inject(this)
    }
    override fun setPresenter(presenter: TopupDeductionEthChainContract.TopupDeductionEthChainContractPresenter) {
            mPresenter = presenter as TopupDeductionEthChainPresenter
        }

    override fun topupOrderStatus(topupOrder: TopupOrder) {
        if (isFinish) {
            return
        }
        if (!"QGAS_PAID".equals(topupOrder.order.status, true)) {
            thread {
                Thread.sleep(5000)
                var map = hashMapOf<String, String>()
                map["orderId"] = topupOrder.order.id
                mPresenter.topupOrderConfirm(map)
            }
        } else {
            ivLoad2.setImageResource(R.mipmap.background_success)
            tvPaying.text = getString(R.string.qgas_transferred, topupOrderBean.symbol)
            tvVoucher.text = getString(R.string.blockchain_inoice_created)
            ivLoad2.clearAnimation()
            sa1.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })
            saHalf.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            showChangeAnimation(ivLoad2)
            tvSend.postDelayed({
                sweetAlertDialog.dismissWithAnimation()
            }, 1000)
            tvSend.postDelayed({
                if ("TOKEN".equals(topupOrderBean.payWay)) {
                    when(OtcUtils.parseChain(topupOrderBean.payTokenChain)) {
                        AllWallet.WalletType.NeoWallet -> {
                            var payIntent = Intent(this, TopupPayNeoChainActivity::class.java)
                            payIntent.putExtra("order", topupOrder.order)
                            startActivity(payIntent)
                            setResult(Activity.RESULT_OK)
                            finish()
                        }
                    }
                } else {
                    var url = "https://shop.huagaotx.cn/vendor/third_pay/index.html?sid=8a51FmcnWGH-j2F-g9Ry2KT4FyZ_Rr5xcKdt7i96&trace_id=mm_1000001_${topupOrder.order.userId}_${topupOrder.order.id}&package=${topupOrder.order.originalPrice.toBigDecimal().stripTrailingZeros().toPlainString()}&mobile=${topupOrder.order.phoneNumber}"
                    val intent = Intent(this, WebViewActivity::class.java)
                    intent.putExtra("url", url)
                    intent.putExtra("title", getString(R.string.payment))
                    startActivityForResult(intent, 10)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }, 1500)
        }
    }

    override fun onBackPressed() {
        if (sweetAlertDialog.isShowing) {
            sweetAlertDialog.dismissWithAnimation()
            isFinish = true
//            startActivity(Intent(this, TopupOrderListActivity::class.java))
//            setResult(Activity.RESULT_OK)
//            finish()
        } else {
            super.onBackPressed()
        }
    }

    override fun createTopupOrderError() {
        sweetAlertDialog.dismissWithAnimation()
    }

    override fun setMainAddress() {
        tvReceiveAddress.text = ConstantValue.mainAddressData.qlcchian.address
    }

    override fun createTopupOrderSuccess(topupOrder: TopupOrder) {

    }

    override fun saveDeductionTokenTxidBack(topupOrder: TopupOrder) {
        if (!"QGAS_PAID".equals(topupOrder.order.status, true)) {
            thread {
                Thread.sleep(5000)
                var map = hashMapOf<String, String>()
                map["orderId"] = topupOrder.order.id
                mPresenter.topupOrderConfirm(map)
            }
        } else {
            ivLoad2.setImageResource(R.mipmap.background_success)
            tvPaying.text = getString(R.string.qgas_transferred, topupOrderBean.symbol)
            tvVoucher.text = getString(R.string.blockchain_inoice_created)
            ivLoad2.clearAnimation()
            sa1.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {

                }

            })
            saHalf.setAnimationListener(object : Animation.AnimationListener {
                override fun onAnimationRepeat(animation: Animation?) {

                }

                override fun onAnimationEnd(animation: Animation?) {

                }

                override fun onAnimationStart(animation: Animation?) {
                }

            })
            showChangeAnimation(ivLoad2)
            tvSend.postDelayed({
                sweetAlertDialog.dismissWithAnimation()
            }, 1000)
            tvSend.postDelayed({
                when(OtcUtils.parseChain(topupOrderBean.payTokenChain)) {
                    AllWallet.WalletType.NeoWallet -> {
                        var payIntent = Intent(this, TopupPayNeoChainActivity::class.java)
                        payIntent.putExtra("order", topupOrder.order)
                        startActivity(payIntent)
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                }
            }, 1500)
        }

    }

    var qgasCount = BigDecimal.ZERO

    lateinit var saHalf : ScaleAnimation
    lateinit var sa1 : ScaleAnimation

    var ethAccount: EthWallet? = null
    var ethCount = BigDecimal.ZERO

    var payTokenBalance = BigDecimal.ZERO

    private val gasLimit = 60000

    private var gasPrice = 6

    private var gasEth: String? = null

    lateinit var animationView: View
    lateinit var ivLoad1: ImageView
    lateinit var ivLoad2: ImageView
    lateinit var ivChain: ImageView
    lateinit var tvPaying: TextView
    lateinit var tvVoucher: TextView
    lateinit var view2 : View
    var isFinish = false

    lateinit var sweetAlertDialog: SweetAlertDialog

    lateinit var topupOrderBean : TopupOrder.OrderBean

    override fun initData() {
        title.text = getString(R.string.payment_wallet)
        topupOrderBean = intent.getParcelableExtra("order")

        payToken.text = topupOrderBean.symbol

        animationView = layoutInflater.inflate(R.layout.alert_show_otc_pay_animation, null, false)
        ivLoad1 = animationView.findViewById<ImageView>(R.id.ivLoad1)
        ivLoad2 = animationView.findViewById<ImageView>(R.id.ivLoad2)
        ivChain = animationView.findViewById<ImageView>(R.id.ivChain)
        ivChain.setImageResource(R.mipmap.icons_qlc_wallet)
        tvPaying = animationView.findViewById<TextView>(R.id.tvPaying)
        tvVoucher = animationView.findViewById<TextView>(R.id.tvVoucher)
        view2 = animationView.findViewById(R.id.view2)
        sweetAlertDialog = SweetAlertDialog(this)
        sweetAlertDialog.setView(animationView)
        sweetAlertDialog.setOnBackListener {
            onBackPressed()
        }

        saHalf = ScaleAnimation(1f, 0.5f, 1f, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        saHalf.setDuration(400)

        sa1 = ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sa1.setDuration(1000)

        if (ConstantValue.mainAddressData != null) {
            tvReceiveAddress.text = ConstantValue.mainAddressData.eth.address
        } else {
            mPresenter.getMainAddress()
        }

        val gas = Convert.toWei(gasPrice.toString() + "", Convert.Unit.GWEI).divide(Convert.toWei(1.toString() + "", Convert.Unit.ETHER))
        val f = gas.multiply(BigDecimal(gasLimit))
        gasEth = f.setScale(4, BigDecimal.ROUND_HALF_UP).toPlainString()

        tvAmountQgas.text = topupOrderBean.qgasAmount.toBigDecimal().stripTrailingZeros().toPlainString()
        llSelectQlcWallet.setOnClickListener {
            var intent1 = Intent(this, OtcChooseWalletActivity::class.java)
            intent1.putExtra("walletType", AllWallet.WalletType.EthWallet.ordinal)
            intent1.putExtra("select", true)
            startActivityForResult(intent1, AllWallet.WalletType.EthWallet.ordinal)
            overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out)
        }

        var message = "topup_" + topupOrderBean.id + "_" + topupOrderBean.discountPrice
        etEthTokenSendMemo.setText(message)
        var ethWallets = AppConfig.instance.daoSession.ethWalletDao.loadAll()
        if (ethWallets.size > 0) {
            ethWallets.forEach {
                if (it.isCurrent()) {
                    ethAccount = it
                    tvQlcWalletName.text = ethAccount!!.name
                    tvQlcWalletAddess.text = ethAccount!!.address
                    tvQGASBalance.text = getString(R.string.balance) + ": -/-"
                    thread {
                        getWalletBalance()
                    }
                }
            }
        }
        tvSend.setOnClickListener {
            if (ethAccount == null) {
                return@setOnClickListener
            }
            if (tvAmountQgas.text.toString().toBigDecimal() > payTokenBalance) {
                alert(getString(R.string.balance_insufficient_to_purchase_qgas_on_otc_pages, topupOrderBean.symbol)) {
                    negativeButton(getString(R.string.cancel)) {
                        dismiss()
                        setResult(Activity.RESULT_OK)
                        finish()
                    }
                    positiveButton(getString(R.string.buy_topup)) {
                        setResult(Activity.RESULT_OK)
                        EventBus.getDefault().post(SwitchToOtc())
                        finish()
                    }
                }.show()
                return@setOnClickListener
            }
            if (ethCount.toDouble() >= gasEth!!.toDouble()) {
                sendPayToken()
            } else {
                ToastUtil.displayShortToast(getString(R.string.not_enough) + " eth")
            }
        }
    }

    fun sendPayToken() {
        showPayAnimation()
        mPresenter.transaction(ethAccount!!.address, deductionTokensBean.tokenInfo.address, deductionTokensBean.tokenInfo.decimals.toInt(), tvReceiveAddress.getText().toString(), tvAmountQgas.text.toString(), gasLimit, gasPrice)
    }

    fun saveDeductionTokenTxid(txid : String, orderId : String) {
        var map = hashMapOf<String, String>()
        var topUpP2pId = SpUtil.getString(this, ConstantValue.topUpP2pId, "")
        if ("".equals(topUpP2pId)) {
            var saveP2pId = FileUtil.readData("/Qwallet/p2pId.txt")
            if ("".equals(saveP2pId)) {
                val uuid = UUID.randomUUID()
                var p2pId = ""
                p2pId += uuid.toString().replace("-", "")
                topUpP2pId = p2pId
                SpUtil.putString(this, ConstantValue.topUpP2pId, p2pId)

                val file = File(Environment.getExternalStorageDirectory().toString() + "/Qwallet/p2pId.txt")
                if (file.exists()) {
                    FileUtil.savaData("/Qwallet/p2pId.txt", topUpP2pId)
                } else {
                    file.createNewFile()
                    FileUtil.savaData("/Qwallet/p2pId.txt", topUpP2pId)
                }

            } else {
                topUpP2pId = saveP2pId
                SpUtil.putString(this, ConstantValue.topUpP2pId, topUpP2pId)
            }
        }
        KLog.i("p2pId为：" + topUpP2pId)
        if (ConstantValue.currentUser != null) {
            map["account"] = ConstantValue.currentUser.account
            map["p2pId"] = topUpP2pId
        } else {
            map["p2pId"] = topUpP2pId
        }

        map["orderId"] = orderId
        map["deductionTokenTxid"] = txid
        mPresenter.saveDeductionTokenTxid(map)
    }

    fun showPayAnimation() {
        tvPaying.text = getString(R.string.transferring_qgas, topupOrderBean.symbol)
        tvVoucher.text = getString(R.string.creating_blockchain_invoice)
        ivLoad1.visibility = View.VISIBLE
        view2.visibility = View.INVISIBLE
        ivLoad1.setImageResource(R.mipmap.background_load)
        ivLoad2.setImageResource(R.mipmap.background_no)
        var tvWalletName = animationView.findViewById<TextView>(R.id.tvWalletName)
        tvWalletName.text = ethAccount!!.name
        scaleAnimationTo1(ivLoad1)
        var tvWalletAddess = animationView.findViewById<TextView>(R.id.tvWalletAddess)
        tvWalletAddess.text = ethAccount!!.address

        sweetAlertDialog.show()
    }

    fun showViewAnimation(view1: View) {
        var viewSa = ScaleAnimation(1f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0f);
        viewSa.setDuration(1000)
        viewSa.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                view1.visibility = View.VISIBLE
                ivLoad2.setImageResource(R.mipmap.background_load)
                tvVoucher.text = getString(R.string.to_create_blockchain_invoice)
                showChangeAnimation(ivLoad2)
                ivLoad2.postDelayed({
                    scaleAnimationTo1(ivLoad2)
                }, 300)
            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })
        view1.startAnimation(viewSa)
    }

    fun scaleAnimationTo1(imageView: ImageView) {
        sa1.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                scaleAnimationToHalf(imageView)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        imageView.startAnimation(sa1)
    }
    fun scaleAnimationToHalf(imageView: ImageView) {
        saHalf.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                scaleAnimationTo1(imageView)
            }

            override fun onAnimationStart(animation: Animation?) {
            }

        })
        imageView.startAnimation(saHalf)
    }

    fun showChangeAnimation(view1: View) {
        SpringAnimationUtil.startScaleSpringViewAnimation(view1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            AllWallet.WalletType.EthWallet.ordinal -> {
                if (resultCode == Activity.RESULT_OK) {
                    ethAccount = data!!.getParcelableExtra<EthWallet>("wallet")
                    tvQlcWalletName.text = ethAccount!!.name
                    tvQlcWalletAddess.text = ethAccount!!.address
                    tvQGASBalance.text = getString(R.string.balance) + ": -/- ${topupOrderBean.symbol}"
                    payTokenBalance = BigDecimal.ZERO
                    ethCount = BigDecimal.ZERO
                    thread {
                        getWalletBalance()
                    }
                }
            }
            10 -> {
                startActivity(Intent(this, TopupOrderListActivity::class.java))
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    fun getWalletBalance() {
        val infoMap = HashMap<String, String>()
        infoMap["address"] = ethAccount!!.address
        mPresenter.getETHWalletDetail(ethAccount!!.address, infoMap)
    }


    override fun showProgressDialog() {
        progressDialog.show()
    }

    override fun closeProgressDialog() {
        progressDialog.hide()
    }

}