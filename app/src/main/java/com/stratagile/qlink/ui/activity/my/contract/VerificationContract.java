package com.stratagile.qlink.ui.activity.my.contract;

import com.stratagile.qlink.entity.otc.Passport;
import com.stratagile.qlink.ui.activity.base.BasePresenter;
import com.stratagile.qlink.ui.activity.base.BaseView;

import java.util.Map;

/**
 * @author hzp
 * @Package The contract for VerificationActivity
 * @Description: $description
 * @date 2019/06/14 15:10:49
 */
public interface VerificationContract {
    interface View extends BaseView<VerificationContractPresenter> {
        /**
         *
         */
        void showProgressDialog();

        /**
         *
         */
        void closeProgressDialog();
        void uploadImgSuccess(Passport upLoadAvatar);
    }

    interface VerificationContractPresenter extends BasePresenter {
//        /**
//         *
//         */
//        void getBusinessInfo(Map map);

        void uploadImg(Map map, String idNumber);
    }
}