package com.stratagile.qlink.ui.activity.otc.module

import com.stratagile.qlink.data.api.HttpAPIWrapper
import com.stratagile.qlink.ui.activity.base.ActivityScope
import com.stratagile.qlink.ui.activity.otc.AppealActivity
import com.stratagile.qlink.ui.activity.otc.contract.AppealContract
import com.stratagile.qlink.ui.activity.otc.presenter.AppealPresenter

import dagger.Module;
import dagger.Provides;

/**
 * @author hzp
 * @Package com.stratagile.qlink.ui.activity.otc
 * @Description: The moduele of AppealActivity, provide field for AppealActivity
 * @date 2019/07/19 11:44:36
 */
@Module
class AppealModule (private val mView: AppealContract.View) {

    @Provides
    @ActivityScope
    fun provideAppealPresenter(httpAPIWrapper: HttpAPIWrapper) :AppealPresenter {
        return AppealPresenter(httpAPIWrapper, mView)
    }

    @Provides
    @ActivityScope
    fun provideAppealActivity() : AppealActivity {
        return mView as AppealActivity
    }
}