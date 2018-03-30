package com.canplay.repast_wear.mvp.present;

import com.canplay.repast_wear.base.BasePresenter;
import com.canplay.repast_wear.base.BaseView;

import java.util.List;

public class CookClassifyContract {
    public    interface View extends BaseView {

        <T> void toList(List<T> list, int type);
        <T> void toEntity(T entity, int type);

//        void toNextStep(int type);

        void showTomast(String msg);
    }

    public  interface Presenter extends BasePresenter<View> {

        /**
         * 菜品分类列表
         */
        void getCookClassifyList();

        /**
         * 添加菜品分类
         */
        void addBookClassfy(String content);

        /**
         * 删除菜品分类
         */
        void delBookClassfy(String content);
        /**
         * 配菜分类列表
         */
        void getFoodClassifyList();
        /**
         * 做法分类列表
         */
        void getRecipesClassifyList();


        /**
         * 添加菜品分类
         */
        void addFoodClassify(String content);

        /**
         * 删除菜品分类
         */
        void delFoodClassify(String content);
        /**
         * 添加菜品分类
         */
        void addRecipesClassify(String content);

        /**
         * 删除菜品分类
         */
        void delRecipesClassify(String content);
        /**
         * 2 商家菜谱列表
         */
        void getCookbookList(String classifyId ,int pageIndex ,int pageSize ) ;

        /**
         * 新增修改菜谱
         */
        void createOrEditCookbook(String cookbookId,String resourceKey,String cnName,String enName,String classifyId,
                             String price,String foodIds,String recipesIds);

        /**
         * 菜谱详情
         */
        void getCookbookInfo(String cookbookId);

        void getToken( String path);
        /**
         * 商家菜谱列表
         */
        void getMenuList();

        /**
         * 商家菜谱详情
         */
        void getMenuInfo(String menuId);

        /**
         *  新增修改菜单
         */
        void createOrEditMenu(String menuId ,String templateId ,String classifyId ,String cookbookIds ,String sort  );


        /**
         *  删除菜谱
         */

        void delMenuInfo(String menuId);


        /**
         *  编辑茶位fei
         */
        void editSurcharge(String surcharge);
        /**
         *  查询茶位费
         */
        void getSurcharge();
        /**
         *  估清
         */
        void  editCookbookState(String cookbookId,String state);

        /**
         *  获取订单详情
         */
        void getOrderInfoList(String orderNo);


        /**
         *  订单管理列表
         */
        void getAppOrderList(String merchantId,int page,int state,int loadtype);



        /**
         *  获取订单详情2
         */
        void getAppOrderInfo(String detailNo);




        void updateOrderState(String detaiNo,String state);




        void updateDetailCount(String cbCountInfo);
    }


}
