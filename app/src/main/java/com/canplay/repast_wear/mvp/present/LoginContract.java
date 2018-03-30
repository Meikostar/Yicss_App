package com.canplay.repast_wear.mvp.present;

import com.canplay.repast_wear.base.BasePresenter;
import com.canplay.repast_wear.base.BaseView;

public class LoginContract {
    public    interface View extends BaseView {

//        <T> void toList(List<T> list, int type, int... refreshType);
        <T> void toEntity(T entity);

//        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 获得联系人列表
         */
        void goLogin(String account, String pwd);

        /**
         * 获取token
         */
        void getToken();

        void  downApk();
    }
}
