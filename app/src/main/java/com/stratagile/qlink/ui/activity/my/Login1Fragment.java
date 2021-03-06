package com.stratagile.qlink.ui.activity.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.socks.library.KLog;
import com.stratagile.qlink.R;
import com.stratagile.qlink.application.AppConfig;
import com.stratagile.qlink.base.BaseFragment;
import com.stratagile.qlink.constant.ConstantValue;
import com.stratagile.qlink.constant.MainConstant;
import com.stratagile.qlink.db.UserAccount;
import com.stratagile.qlink.entity.VcodeLogin;
import com.stratagile.qlink.entity.eventbus.LoginSuccess;
import com.stratagile.qlink.entity.newwinq.Register;
import com.stratagile.qlink.ui.activity.my.component.DaggerLogin1Component;
import com.stratagile.qlink.ui.activity.my.contract.Login1Contract;
import com.stratagile.qlink.ui.activity.my.module.Login1Module;
import com.stratagile.qlink.ui.activity.my.presenter.Login1Presenter;
import com.stratagile.qlink.utils.AccountUtil;
import com.stratagile.qlink.utils.FireBaseUtils;
import com.stratagile.qlink.utils.MD5Util;
import com.stratagile.qlink.utils.RSAEncrypt;
import com.stratagile.qlink.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

/**
 * @author hzp
 * @Package com.stratagile.qlink.ui.activity.my
 * @Description: $description
 * @date 2019/04/24 18:02:10
 */

public class Login1Fragment extends BaseFragment implements Login1Contract.View {
    @BindView(R.id.webview)
    WebView webview;

    @Override
    protected void initDataFromNet() {

    }

    @Inject
    Login1Presenter mPresenter;
    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etVcode)
    EditText etVcode;
    @BindView(R.id.tvVerificationCode)
    TextView tvVerificationCode;
    @BindView(R.id.llVcode)
    RelativeLayout llVcode;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.tvLogin)
    TextView tvLogin;
    @BindView(R.id.tvForgetPassword)
    TextView tvForgetPassword;

    private FirebaseAnalytics mFirebaseAnalytics;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, null);
        ButterKnife.bind(this, view);
        Bundle mBundle = getArguments();
        etAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (AccountUtil.isEmail(s.toString().trim()) || AccountUtil.isTelephone(s.toString().trim())) {
                    regexAccount(s.toString().trim());
                } else {
                    llVcode.setVisibility(View.GONE);
                }
            }
        });
        if (ConstantValue.lastLoginOut != null) {
            etAccount.setText(ConstantValue.lastLoginOut.getAccount());
            etAccount.setSelection(etAccount.getText().length());
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview.setBackgroundColor(Color.WHITE);
        webview.setLayerType(WebView.LAYER_TYPE_SOFTWARE, null);
        webview.getSettings().setUseWideViewPort(true);
        webview.getSettings().setLoadWithOverviewMode(true);
        // 禁止缓存加载，以确保可获取最新的验证图片。
        webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        // 设置不使用默认浏览器，而直接使用WebView组件加载页面。
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                KLog.i(url);
                view.loadUrl(url);
                return true;
            }
        });
        // 设置WebView组件支持加载JavaScript。
        webview.getSettings().setJavaScriptEnabled(true);
        webview.setHorizontalScrollBarEnabled(false);
        webview.setVerticalScrollBarEnabled(false);
        // 建立JavaScript调用Java接口的桥梁。
        webview.addJavascriptInterface(new WebAppInterface(), "successCallback");
    }

    public class WebAppInterface {

        @JavascriptInterface
        public void postMessage(String message) {
            try {
                JSONObject jsonObject = new JSONObject(message);
                String token = jsonObject.getString("token");
                String sid = jsonObject.getString("sid");
                String sig = jsonObject.getString("sig");
                KLog.i(token);
                KLog.i(sid);
                KLog.i(sig);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        @JavascriptInterface
        public void sendToken(String token, String sid, String sig) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    KLog.i(token);
                    KLog.i(sid);
                    KLog.i(sig);
                    webview.setVisibility(View.GONE);
                    Map map = new HashMap<String, String>();
                    map.put("account", etAccount.getText().toString().trim().toLowerCase());
                    map.put("sessionId", sid);
                    map.put("sig", sig);
                    map.put("afsToken", token);
                    map.put("appKey", MainConstant.afsFFFF0N00000000009290AppKey);
                    map.put("scene", MainConstant.ncLogin);
                    mPresenter.getSignInVcode(map);
                }
            });
        }
    }

    boolean isVCodeLogin = false;

    //返回true，代表验证码登录
    private boolean regexAccount(String account) {
        List<UserAccount> userAccounts = AppConfig.getInstance().getDaoSession().getUserAccountDao().loadAll();
        if (userAccounts.size() > 0) {
            for (UserAccount userAccount : userAccounts) {
                if (userAccount.getEmail() == null) {
                    userAccount.setEmail("");
                }
                if (userAccount.getPhone() == null) {
                    userAccount.setPhone("");
                }
                if ((account.toLowerCase().equals(userAccount.getAccount().toLowerCase()) || account.toLowerCase().equals(userAccount.getEmail().toLowerCase()) || account.equals(userAccount.getPhone())) && userAccount.getPubKey() != null && !"".equals(userAccount.getPubKey())) {
                    //账号密码登录
                    isVCodeLogin = false;
                    llVcode.setVisibility(View.GONE);
                    return false;
                } else {
                    //验证码登录
                    isVCodeLogin = true;
                    llVcode.setVisibility(View.VISIBLE);
                }
            }
        }
        //验证码登录
        isVCodeLogin = true;
        llVcode.setVisibility(View.VISIBLE);
        return true;
    }

    private void bindPush(UserAccount userAccount) {
        if ("".equals(JPushInterface.getRegistrationID(getActivity()))) {
            return;
        }
        Map map = new HashMap<String, String>();
        map.put("account", userAccount.getAccount());
        map.put("token", AccountUtil.getUserToken());
        map.put("appOs", "Android");
        map.put("pushPlatform", "JIGUANG");
        map.put("pushId", JPushInterface.getRegistrationID(getActivity()));
        mPresenter.bindPush(map);
    }


    @Override
    protected void setupFragmentComponent() {
        DaggerLogin1Component
                .builder()
                .appComponent(((AppConfig) getActivity().getApplication()).getApplicationComponent())
                .login1Module(new Login1Module(this))
                .build()
                .inject(this);
    }

    @Override
    public void setPresenter(Login1Contract.Login1ContractPresenter presenter) {
        mPresenter = (Login1Presenter) presenter;
    }

    @Override
    protected void initDataFromLocal() {

    }

    @Override
    public void showProgressDialog() {
        progressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        progressDialog.hide();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private String account;
    private String password;

    @OnClick({R.id.tvLogin, R.id.tvForgetPassword, R.id.tvVerificationCode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvLogin:
                account = etAccount.getText().toString().trim();
                password = etPassword.getText().toString().trim();
                if ("".equals(account)) {
                    ToastUtil.displayShortToast(getString(R.string.wrong_account));
                    return;
                }
                if (!AccountUtil.isTelephone(account) && !AccountUtil.isEmail(account)) {
                    ToastUtil.displayShortToast(getString(R.string.wrong_account));
                    return;
                }
                if ("".equals(password) || password.length() < 6) {
                    ToastUtil.displayShortToast(getString(R.string.wrong_password));
                    return;
                }
                if (regexAccount(account)) {
                    vCodeLogin();
                } else {
                    login();
                }
                break;
            case R.id.tvForgetPassword:
                startActivity(new Intent(getActivity(), RetrievePasswordActivity.class));
                break;
            case R.id.tvVerificationCode:
                getLoginVcode();
                break;
            default:
                break;
        }
    }

    private Disposable mdDisposable;

    private void startVCodeCountDown() {
        tvVerificationCode.setEnabled(false);
        tvVerificationCode.setBackground(getResources().getDrawable(R.drawable.vcode_count_down_bg));
        mdDisposable = Flowable.intervalRange(0, 60, 0, 1, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        tvVerificationCode.setText("" + (60 - aLong) + "");
                    }
                })
                .doOnComplete(new Action() {
                    @Override
                    public void run() throws Exception {
                        //倒计时完毕置为可点击状态
                        tvVerificationCode.setEnabled(true);
                        tvVerificationCode.setText(getString(R.string.get_the_code));
                        tvVerificationCode.setBackgroundColor(getResources().getColor(R.color.white));
                    }
                })
                .subscribe();
    }

    @Override
    public void onDestroy() {
        if (mdDisposable != null) {
            mdDisposable.dispose();
        }
        super.onDestroy();
    }

    /**
     * 获取登录验证码
     */
    private void getLoginVcode() {
        webview.setVisibility(View.VISIBLE);
        webview.loadUrl("file:///android_asset/slideLogin.html");
//        showProgressDialog();
//        Map map = new HashMap<String, String>();
//        map.put("account", etAccount.getText().toString().trim().toLowerCase());
//        mPresenter.getSignInVcode(map);
    }

    private void vCodeLogin() {
        showProgressDialog();
        Map map = new HashMap<String, String>();
        map.put("account", etAccount.getText().toString().trim().toLowerCase());
        map.put("code", etVcode.getText().toString().trim());
        mPresenter.vCodeLogin(map);
    }

    private void login() {
        List<UserAccount> userAccounts = AppConfig.getInstance().getDaoSession().getUserAccountDao().loadAll();
        for (UserAccount userAccount : userAccounts) {
            if (userAccount.getAccount().toLowerCase().equals(etAccount.getText().toString().trim().toLowerCase())) {
                showProgressDialog();
                Map map = new HashMap<String, String>();
                map.put("account", account.toLowerCase());
                String orgin = Calendar.getInstance().getTimeInMillis() + "," + MD5Util.getStringMD5(password);
                KLog.i("加密前的原始：" + orgin);
                String token = RSAEncrypt.encrypt(orgin, userAccount.getPubKey());
                KLog.i("加密后：" + token);
                map.put("token", token);
                mPresenter.login(map);
                break;
            }
        }
    }


    @Override
    public void loginSuccess(Register register) {
        closeProgressDialog();
        ToastUtil.displayShortToast(getString(R.string.Login_success));
        FireBaseUtils.logEvent(getActivity(), FireBaseUtils.eventLogin);
        List<UserAccount> userAccounts = AppConfig.getInstance().getDaoSession().getUserAccountDao().loadAll();
        if (userAccounts.size() > 0) {
            for (UserAccount userAccount : userAccounts) {
                if (userAccount.getAccount().toLowerCase().equals(account.toLowerCase()) && userAccount.getPubKey() != null && !"".equals(userAccount.getPubKey())) {
                    //账号密码登录
                    userAccount.setIsLogin(true);
                    userAccount.setPassword(MD5Util.getStringMD5(password));
                    ConstantValue.currentUser = userAccount;
                    AppConfig.getInstance().getDaoSession().getUserAccountDao().update(userAccount);
                } else {
                    //验证码登录
                    userAccount.setIsLogin(false);
                    AppConfig.getInstance().getDaoSession().getUserAccountDao().update(userAccount);
                }
            }
        }
        Set<String> tags = new HashSet<>();
        tags.add(ConstantValue.userAll);
        if (!"".equals(ConstantValue.currentUser.getBindDate())) {
            tags.add(ConstantValue.userLend);
        }
        ConstantValue.jpushOpreateCount++;
        JPushInterface.setTags(getActivity(), ConstantValue.jpushOpreateCount, tags);

        bindPush(ConstantValue.currentUser);
        EventBus.getDefault().post(new LoginSuccess());
        getActivity().finish();
    }

    @Override
    public void vCodeLoginSuccess(VcodeLogin register) {
        KLog.i("登录成功返回");
        UserAccount userAccount = new UserAccount();
        userAccount.setAccount(account);
        userAccount.setUserId(register.getId());
        userAccount.setVstatus(register.getVstatus());
        userAccount.setFacePhoto(register.getFacePhoto());
        userAccount.setHoldingPhoto(register.getHoldingPhoto());
        userAccount.setPubKey(register.getData());
        userAccount.setAvatar(register.getHead());
        userAccount.setBindDate(register.getBindDate());
        userAccount.setInviteCode(register.getNumber());
        userAccount.setTotalInvite(register.getTotalInvite());
        userAccount.setEmail(register.getEmail());
        userAccount.setPhone(register.getPhone());
        userAccount.setUserName(register.getNickname());
        userAccount.setIsLogin(false);
        ConstantValue.currentUser = userAccount;
        AppConfig.getInstance().getDaoSession().getUserAccountDao().insert(userAccount);
        login();
    }

    @Override
    public void getLoginVCodeSuccess() {
        ToastUtil.displayShortToast(getString(R.string.the_verification_code_has_been_sent_successfully));
        startVCodeCountDown();
    }

    @Override
    public void loginError(String s) {
        ToastUtil.displayShortToast(s);
    }
}