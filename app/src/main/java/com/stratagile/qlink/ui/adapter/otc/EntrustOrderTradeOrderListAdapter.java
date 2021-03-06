package com.stratagile.qlink.ui.adapter.otc;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.stratagile.qlink.R;
import com.stratagile.qlink.application.AppConfig;
import com.stratagile.qlink.constant.ConstantValue;
import com.stratagile.qlink.data.api.API;
import com.stratagile.qlink.data.api.MainAPI;
import com.stratagile.qlink.entity.otc.TradeOrderList;
import com.stratagile.qlink.utils.AccountUtil;
import com.stratagile.qlink.utils.TimeUtil;

import java.math.BigDecimal;
import java.util.List;

public class EntrustOrderTradeOrderListAdapter extends BaseQuickAdapter<TradeOrderList.OrderListBean, BaseViewHolder> {

    public EntrustOrderTradeOrderListAdapter(@Nullable List<TradeOrderList.OrderListBean> data) {
        super(R.layout.entrust_order_trade_order_list_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TradeOrderList.OrderListBean item) {
        if (item.getBuyerId().equals(ConstantValue.currentUser.getUserId())) {
            //我买单
            helper.setText(R.id.tvOrderType, R.string.seller);
            helper.setText(R.id.tvQgasAmount, "+" + item.getQgasAmount() + "");
            switch (item.getStatus()) {
                case "QGAS_TO_PLATFORM":
                    helper.setText(R.id.tvOrderState, R.string.wait_buyer_payment);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "USDT_PAID":
                    helper.setText(R.id.tvOrderState, R.string.wait_seller_confirmation);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.mainColor));
                    break;
                case "USDT_PENDING":
                    helper.setText(R.id.tvOrderState, R.string.wait_seller_confirmation);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.mainColor));
                    break;
                case "OVERTIME":
                    helper.setText(R.id.tvOrderState, R.string.overtime);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "CANCEL":
                    helper.setText(R.id.tvOrderState, R.string.canceled);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "QGAS_PAID":
                    helper.setText(R.id.tvOrderState, R.string.completed);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_01b5ab));
                    break;
                default:
                    break;
            }
        } else {
            helper.setText(R.id.tvOrderType, R.string.buyer);
            helper.setText(R.id.tvQgasAmount, "-" + item.getQgasAmount() + "");
            switch (item.getStatus()) {
                case "QGAS_TO_PLATFORM":
                    helper.setText(R.id.tvOrderState, R.string.wait_buyer_payment);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.mainColor));
                    break;
                case "USDT_PAID":
                    helper.setText(R.id.tvOrderState, R.string.wait_seller_confirmation);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "USDT_PENDING":
                    helper.setText(R.id.tvOrderState, R.string.wait_seller_confirmation);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "OVERTIME":
                    helper.setText(R.id.tvOrderState, R.string.overtime);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "CANCEL":
                    helper.setText(R.id.tvOrderState, R.string.canceled);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_ff3669));
                    break;
                case "QGAS_PAID":
                    helper.setText(R.id.tvOrderState, R.string.completed);
                    helper.setTextColor(R.id.tvOrderState, mContext.getResources().getColor(R.color.color_01b5ab));
                    break;
                default:
                    break;
            }
        }
        helper.setText(R.id.tvTime, TimeUtil.getOrderTime(TimeUtil.timeStamp(item.getCreateDate())));
        helper.setText(R.id.tvNickName, AccountUtil.setUserNickName(item.getNickname()));
        Glide.with(mContext)
                .load(MainAPI.MainBASE_URL + item.getHead())
                .apply(AppConfig.getInstance().options)
                .into((ImageView) helper.getView(R.id.ivAvatar));
    }
}
