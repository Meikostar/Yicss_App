package com.canplay.repast_wear.fragment;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.canplay.repast_wear.R;
import com.canplay.repast_wear.base.BaseApplication;
import com.canplay.repast_wear.base.BaseFragment;
import com.canplay.repast_wear.base.RxBus;
import com.canplay.repast_wear.base.SubscriptionBean;
import com.canplay.repast_wear.bean.ORDER;
import com.canplay.repast_wear.mvp.activity.OrderDetailActivity;
import com.canplay.repast_wear.mvp.adapter.recycle.OrderMangerRecycleAdapter;
import com.canplay.repast_wear.mvp.component.DaggerBaseComponent;
import com.canplay.repast_wear.mvp.present.CookClassifyContract;
import com.canplay.repast_wear.mvp.present.CookClassifyPresenter;
import com.canplay.repast_wear.view.DivItemDecoration;
import com.canplay.repast_wear.view.PopView_NavigationBar;
import com.malinskiy.superrecyclerview.OnMoreListener;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Subscription;
import rx.functions.Action1;


/**
 * Created by mykar on 17/4/10.
 */
public class OrderMangerFragment extends BaseFragment implements View.OnClickListener ,CookClassifyContract.View{

    @Inject
    CookClassifyPresenter presenter;
    @BindView(R.id.fl_choose)
    FrameLayout flChoose;
    @BindView(R.id.ll_dishe)
    RelativeLayout llDishe;
    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    Unbinder unbinder;
    @BindView(R.id.line)
    View line;
//    private List<AD> list = new ArrayList<>();
    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private OrderMangerRecycleAdapter adapter;
    private LinearLayoutManager mLinearLayoutManager;
    private final int TYPE_PULL_REFRESH = 1;
    private final int TYPE_PULL_MORE = 2;
    private final int TYPE_REMOVE = 3;
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_manger, null);
        unbinder = ButterKnife.bind(this, view);
        DaggerBaseComponent.builder().appComponent(((BaseApplication) getActivity().getApplication()).getAppComponent()).build().inject(this);
        presenter.attachView(this);
        initView();
        initListener();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private Subscription mSubscription;
    private void initListener() {
        initPopView();
        flChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popView_navigationBar.showPopView();
            }
        });
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;

                if(bean.type==SubscriptionBean.NOFIFY){
                    reflash();

                }else if(bean.type==SubscriptionBean.NOFIFYS){
                    reflash();
                    star.play(music, 1, 1, 0, 1, 1);


                }

            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);

    }
    private void reflash(){
        if(mSuperRecyclerView!=null) {
            //实现自动下拉刷新功能
            mSuperRecyclerView.getSwipeToRefresh().post(new Runnable(){
                @Override
                public void run() {
                    mSuperRecyclerView.setRefreshing(true);//执行下拉刷新的动画
                    refreshListener.onRefresh();//执行数据加载操作
                }
            });
        }
    }
    private SoundPool star;//声明一个SoundPool
    private int music;//定义一个整型用load（）；来设置suondID
   public int currpage=1;
    private void initView() {
        star = new SoundPool(1, AudioManager.STREAM_SYSTEM, 0);//第一个参数为同时播放数据流的最大个数，第二数据流类型，第三为声音质量
        music = star.load(getActivity(), R.raw.star, 1); //把你的声音素材放到res/raw里，第2个参数即为资源文件，第3个为音乐的优先级
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));
        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
        adapter=new OrderMangerRecycleAdapter(getActivity());
        mSuperRecyclerView.setAdapter(adapter);
        reflash();
        // mSuperRecyclerView.setRefreshing(false);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                // mSuperRecyclerView.showMoreProgress();
                presenter.getAppOrderList("",currpage,state,TYPE_PULL_REFRESH);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(mSuperRecyclerView!=null){
                            mSuperRecyclerView.hideMoreProgress();
                        }

                    }
                }, 2000);
            }
        };
        mSuperRecyclerView.setRefreshListener(refreshListener);
        adapter.setClickListener(new OrderMangerRecycleAdapter.OnItemClickListener() {
            @Override
            public void clickListener(int poiston, String id) {
                Intent intent = new Intent(getActivity(), OrderDetailActivity.class);
                intent.putExtra("order",id);
                startActivity(intent);
            }
        });
    }
    public List<ORDER> list=new ArrayList<>();
    public void onDataLoaded(int loadtype,final boolean haveNext, List<ORDER> datas) {

        if (loadtype == TYPE_PULL_REFRESH) {
            currpage=1;
            list.clear();
            for (ORDER info : datas) {
                list.add(info);
            }
        } else {
            for (ORDER info : datas) {
                list.add(info);
            }
        }
        adapter.setDatas(list);
        adapter.notifyDataSetChanged();
//        mSuperRecyclerView.setLoadingMore(false);
        mSuperRecyclerView.hideMoreProgress();
        /**
         * 判断是否需要加载更多，与服务器的总条数比
         */
        if (haveNext) {
            mSuperRecyclerView.setupMoreListener(new OnMoreListener() {
                @Override
                public void onMoreAsked(int overallItemsCount, int itemsBeforeMore, int maxLastVisiblePosition) {
                    currpage++;
                    mSuperRecyclerView.showMoreProgress();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (haveNext)
                                mSuperRecyclerView.hideMoreProgress();
                                presenter.getAppOrderList("",currpage,state,TYPE_PULL_MORE);

                        }
                    }, 2000);
                }
            }, 1);
        } else {
            mSuperRecyclerView.removeMoreListener();
            mSuperRecyclerView.hideMoreProgress();

        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if(mSubscription!=null){
            mSubscription.unsubscribe();
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
    private int state=-1;
    private PopView_NavigationBar popView_navigationBar;
    private void initPopView() {
//        mWindowAddPhoto = new PhotoPopupWindow(getActivity());
        popView_navigationBar = new PopView_NavigationBar(getActivity(),1);

        popView_navigationBar.setView(line);
        popView_navigationBar.setClickListener(new PopView_NavigationBar.ItemCliskListeners() {
            @Override
            public void clickListener(int poition) {
                switch (poition) {
                    case 0://全部
                        state=-1;
                        break;
                    case 1://待接单
                        state=0;
                        break;
                    case 2://待结账
                        state=1;
                        break;
                    case 3://已完成
                        state=2;
                        break;
                    case 4://已撤销
                        state=4;
                        break;

                }
                popView_navigationBar.dismiss();
                reflash();
            }

        });
    }

    @Override
    public <T> void toList(List<T> list, int type) {

    }
   private ORDER data;
    @Override
    public <T> void toEntity(T entity, int type) {
        data= (ORDER) entity;
        onDataLoaded(type,data.hasNext!=0,data.list);
    }

    @Override
    public void showTomast(String msg) {

    }
}
