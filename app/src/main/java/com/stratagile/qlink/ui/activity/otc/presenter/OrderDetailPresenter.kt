package com.stratagile.qlink.ui.activity.otc.presenter
import android.support.annotation.NonNull
import com.stratagile.qlink.data.api.HttpAPIWrapper
import com.stratagile.qlink.ui.activity.otc.contract.OrderDetailContract
import com.stratagile.qlink.ui.activity.otc.OrderDetailActivity
import javax.inject.Inject
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer

/**
 * @author hzp
 * @Package com.stratagile.qlink.ui.activity.otc
 * @Description: presenter of OrderDetailActivity
 * @date 2019/07/10 10:03:30
 */
class OrderDetailPresenter @Inject
constructor(internal var httpAPIWrapper: HttpAPIWrapper, private val mView: OrderDetailContract.View) : OrderDetailContract.OrderDetailContractPresenter {

    private val mCompositeDisposable: CompositeDisposable

    init {
        mCompositeDisposable = CompositeDisposable()
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }

    fun getEntrustOrderDetail(map: MutableMap<String, String>) {
        mCompositeDisposable.add(httpAPIWrapper.getEntrustOrderInfo(map).subscribe({
            mView.setEntrustOrder(it)
        }, {
            mView.closeProgressDialog()
        }, {
            mView.closeProgressDialog()
        }))
    }

    fun getTradeOrderList(map : MutableMap<String, String>) {
        mCompositeDisposable.add(httpAPIWrapper.tradeOrderList(map).subscribe({
            mView.setTradeOrderList(it)
        }, {
            mView.closeProgressDialog()
        }, {
            mView.closeProgressDialog()
        }))
    }
    fun revokeOrder(map : MutableMap<String, String>) {
        mCompositeDisposable.add(httpAPIWrapper.cancelEntrustOrder(map).subscribe({
            mView.revokeOrderSuccess()
        }, {
            mView.closeProgressDialog()
        }, {
            mView.closeProgressDialog()
        }))
    }
}