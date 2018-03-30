package com.canplay.repast_wear.mvp.present;


import android.support.annotation.NonNull;

import com.canplay.repast_wear.base.manager.ApiManager;
import com.canplay.repast_wear.bean.BASEBEAN;
import com.canplay.repast_wear.bean.BEAN;
import com.canplay.repast_wear.bean.COOK;
import com.canplay.repast_wear.bean.MENU;
import com.canplay.repast_wear.bean.ORDER;
import com.canplay.repast_wear.mvp.http.BaseApi;
import com.canplay.repast_wear.mvp.model.BaseType;
import com.canplay.repast_wear.net.MySubscriber;
import com.canplay.repast_wear.util.SpUtil;
import com.canplay.repast_wear.util.TextUtil;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.inject.Inject;

import rx.Subscription;


public class CookClassifyPresenter implements CookClassifyContract.Presenter {
    private Subscription subscription;

    private CookClassifyContract.View mView;

    private BaseApi contactApi;

    @Inject
    CookClassifyPresenter(ApiManager apiManager){
        contactApi = apiManager.createApi(BaseApi.class);
    }
    @Override
    public void getCookClassifyList() {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getCookClassifyList(ApiManager.getParameters(params, true)), new MySubscriber<List<BaseType>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(List<BaseType> entity){
                mView.toList(entity,1);

            }
        });

    }

    @Override
    public void getCookbookList(String classifyId ,int pageIndex ,final int loadtype ) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        if(TextUtil.isNotEmpty(classifyId)){
            params.put("classifyId",classifyId);
        }
        params.put("pageIndex",pageIndex+"");
        params.put("pageSize", 12+"");

        subscription = ApiManager.setSubscribe(contactApi.getCookbookList(ApiManager.getParameters(params, true)), new MySubscriber<COOK>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(COOK entity){
                mView.toEntity(entity,loadtype);

            }
        });

    }

    public void createOrEditMenu(String menuId ,String templateId ,String classifyId ,String cookbookIds ,String sort  ) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
       if(TextUtil.isNotEmpty(menuId)){
           params.put("menuId",menuId+"");
       }

        params.put("templateId", templateId+"");
        params.put("classifyId", classifyId+"");
        params.put("cookbookIds", cookbookIds+"");
        params.put("sort", sort+"");

        subscription = ApiManager.setSubscribe(contactApi.createOrEditMenu(ApiManager.getParameters(params, true)), new MySubscriber<BEAN>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BEAN entity){
                mView.toEntity(entity,1);

            }
        });

    }


    public void updateOrderState(String detaiNo,String state) {
        Map<String, String> params = new TreeMap<>();
        params.put("detailNo",detaiNo);
        params.put("state", state);

        subscription = ApiManager.setSubscribe(contactApi.updateOrderState(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,-2);

            }
        });

    }
    public void updateDetailCount(String cbCountInfo) {
        Map<String, String> params = new TreeMap<>();
        params.put("cbCountInfo",cbCountInfo);

        subscription = ApiManager.setSubscribe(contactApi.updateDetailCount(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,6);

            }
        });

    }


    @Override
    public void editCookbookState(String cookbookId,String state) {
        Map<String, String> params = new TreeMap<>();
        params.put("cookbookId",cookbookId);
        params.put("state", state);

        subscription = ApiManager.setSubscribe(contactApi.editCookbookState(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,-1);

            }
        });

    }
    @Override
    public void getSurcharge() {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getSurcharge(ApiManager.getParameters(params, true)), new MySubscriber<BASEBEAN>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BASEBEAN entity){
                mView.toEntity(entity.surcharge,2);

            }
        });

    }
    @Override
    public void editSurcharge(String surcharge) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        params.put("surcharge", surcharge);

        subscription = ApiManager.setSubscribe(contactApi.editSurcharge(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,6);

            }
        });

    }



    @Override
    public void getAppOrderInfo(String detailNo) {
        Map<String, String> params = new TreeMap<>();
        params.put("detailNo", detailNo);
        subscription = ApiManager.setSubscribe(contactApi.getAppOrderInfo(ApiManager.getParameters(params, true)), new MySubscriber<ORDER>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(ORDER entity){
                mView.toEntity(entity,1);

            }
        });

    }
    @Override
    public void getOrderInfoList(String orderNo) {
        Map<String, String> params = new TreeMap<>();
        params.put("orderNo", orderNo);
        subscription = ApiManager.setSubscribe(contactApi.getOrderInfoList(ApiManager.getParameters(params, true)), new MySubscriber<ORDER>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(ORDER entity){
                mView.toEntity(entity,1);

            }
        });

    }
    @Override
    public void getAppOrderList(String merchantId,int page,int state,final int loadtype) {

        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        params.put("page", page+"");
        params.put("state", state+"");
        subscription = ApiManager.setSubscribe(contactApi.getAppOrderList(ApiManager.getParameters(params, true)), new MySubscriber<ORDER>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(ORDER entity){
                mView.toEntity(entity,loadtype);

            }
        });

    }

    @Override
    public void getFoodClassifyList() {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getFoodClassifyList(ApiManager.getParameters(params, true)), new MySubscriber<List<BaseType>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(List<BaseType> entity){
                mView.toEntity(entity,2);

            }
        });

    }
    @Override
    public void getRecipesClassifyList() {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getRecipesClassifyList(ApiManager.getParameters(params, true)), new MySubscriber<List<BaseType>>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(List<BaseType> entity){
                mView.toEntity(entity,3);

            }
        });

    }
    @Override
    public void addBookClassfy(String content) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        params.put("name", content);

        subscription = ApiManager.setSubscribe(contactApi.addBookClassify(ApiManager.getParameters(params, true)), new MySubscriber<BaseType>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BaseType entity){
                mView.toEntity(entity,1);

            }
        });

    }
    @Override
    public void delBookClassfy(String cbClassifyId) {
        Map<String, String> params = new TreeMap<>();
        params.put("cbClassifyId", cbClassifyId);

        subscription = ApiManager.setSubscribe(contactApi.delCookClassify(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,1);

            }
        });

    }
    @Override
    public void addFoodClassify(String content) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        params.put("name", content);

        subscription = ApiManager.setSubscribe(contactApi.addFoodClassify(ApiManager.getParameters(params, true)), new MySubscriber<BaseType>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BaseType entity){
                mView.toEntity(entity,1);

            }
        });

    }
    @Override
    public void delFoodClassify(String cbClassifyId) {
        Map<String, String> params = new TreeMap<>();
        params.put("classifyId", cbClassifyId);

        subscription = ApiManager.setSubscribe(contactApi.delFoodClassify(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,4);

            }
        });

    }
    @Override
    public void addRecipesClassify(String content) {
        Map<String, String> params = new TreeMap<>();
        params.put("merchantId", SpUtil.getInstance().getUserId());
        params.put("name", content);

        subscription = ApiManager.setSubscribe(contactApi.addRecipesClassify(ApiManager.getParameters(params, true)), new MySubscriber<BaseType>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BaseType entity){
                mView.toEntity(entity,4);

            }
        });

    }


    @Override
    public void getMenuList() {
        Map<String, String> params = new TreeMap<>();

        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getMenuList(ApiManager.getParameters(params, true)), new MySubscriber<List<MENU> >(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(List<MENU> entity){
                mView.toList(entity,6);

            }
        });

    }
    @Override
    public void delMenuInfo(String menuId) {
        Map<String, String> params = new TreeMap<>();

        params.put("menuId", menuId);

        subscription = ApiManager.setSubscribe(contactApi.delMenuInfo(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,8);

            }
        });

    }

    @Override
    public void getCookbookInfo(String cookbookId) {
        Map<String, String> params = new TreeMap<>();
        params.put("cookbookId", cookbookId);
        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.getCookbookInfo(ApiManager.getParameters(params, true)), new MySubscriber<COOK>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(COOK entity){
                mView.toEntity(entity,6);

            }
        });

    }
    @Override
    public void getToken(final String path) {
        Map<String, String> params = new TreeMap<>();

        subscription = ApiManager.setSubscribe(contactApi.getToken(ApiManager.getParameters(params, true)), new MySubscriber<BASEBEAN>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(BASEBEAN token){
                mView.toEntity(token.upToken,2);

            }
        });
    }

    @Override
    public void getMenuInfo(String menuId) {
        Map<String, String> params = new TreeMap<>();
        params.put("menuId", menuId);

        subscription = ApiManager.setSubscribe(contactApi.getMenuInfo(ApiManager.getParameters(params, true)), new MySubscriber<COOK>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(COOK entity){
                mView.toEntity(entity,3);

            }
        });

    }
    @Override
    public void delRecipesClassify(String cbClassifyId) {
        Map<String, String> params = new TreeMap<>();
        params.put("classifyId", cbClassifyId);

        subscription = ApiManager.setSubscribe(contactApi.delRecipesClassify(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,3);

            }
        });

    }

    @Override
    public void createOrEditCookbook(String cookbookId,String resourceKey,String cnName,String enName,String classifyId,
                                     String price,String foodIds,String recipesIds) {
        Map<String, String> params = new TreeMap<>();
        if(TextUtil.isNotEmpty(cookbookId)){
            params.put("cookbookId", cookbookId);

        }else {
            params.put("cookbookId", "0");

        }
        params.put("resourceKey", resourceKey);
        params.put("cnName", cnName);
        if(TextUtil.isNotEmpty(enName)){
            params.put("enName", enName);
        }   if(TextUtil.isNotEmpty(foodIds)){
            params.put("foodIds", foodIds);
        }   if(TextUtil.isNotEmpty(recipesIds)){
            params.put("recipesIds", recipesIds);
        }

        params.put("classifyId", classifyId);
        params.put("price", price);


        params.put("merchantId", SpUtil.getInstance().getUserId());

        subscription = ApiManager.setSubscribe(contactApi.createOrEditCookbook(ApiManager.getParameters(params, true)), new MySubscriber<String>(){
            @Override
            public void onError(Throwable e){
                super.onError(e);
                mView.showTomast(e.toString());
            }

            @Override
            public void onNext(String entity){
                mView.toEntity(entity,3);

            }
        });

    }

    @Override
    public void attachView(@NonNull CookClassifyContract.View view){
        mView = view;
    }


    @Override
    public void detachView(){
        if(subscription != null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
        mView = null;
    }
}
