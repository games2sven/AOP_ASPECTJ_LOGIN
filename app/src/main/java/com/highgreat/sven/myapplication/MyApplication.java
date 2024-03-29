package com.highgreat.sven.myapplication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.highgreat.sven.myapplication.core.ILogin;
import com.highgreat.sven.myapplication.core.LoginSDK;
import com.highgreat.sven.myapplication.utils.SharePreferenceUtil;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LoginSDK.getInstance().init(this, iLogin);
    }

    ILogin iLogin =  new ILogin() {
        @Override
        public void login(Context applicationContext, int userDefine) {
            switch (userDefine) {
                case 0:
                    Intent intent = new Intent(applicationContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                case 1:
                    Toast.makeText(applicationContext, "您还没有登录，请登陆后执行", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(applicationContext, "执行失败，因为您还没有登录！", Toast.LENGTH_SHORT).show();
                    break;
            }
        }

        @Override
        public boolean isLogin(Context applicationContext) {
            return SharePreferenceUtil.getBooleanSp(SharePreferenceUtil.IS_LOGIN, applicationContext);
        }

        @Override
        public void clearLoginStatus(Context applicationContext) {
            SharePreferenceUtil.setBooleanSp(SharePreferenceUtil.IS_LOGIN, false, applicationContext);
        }
    };

}
