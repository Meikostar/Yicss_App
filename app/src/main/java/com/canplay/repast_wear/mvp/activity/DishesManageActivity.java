package com.canplay.repast_wear.mvp.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.canplay.repast_wear.R;
import com.canplay.repast_wear.base.BaseActivity;
import com.canplay.repast_wear.mvp.adapter.recycle.DishesRecycleAdapter;
import com.canplay.repast_wear.view.DivItemDecoration;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 菜品管理
 */

public class DishesManageActivity extends BaseActivity {

    @BindView(R.id.super_recycle_view)
    SuperRecyclerView mSuperRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;

    private SwipeRefreshLayout.OnRefreshListener refreshListener;
    private DishesRecycleAdapter adapter;



    @Override
    public void initViews() {
        setContentView(R.layout.activity_dishes_manage);
        ButterKnife.bind(this);

        mLinearLayoutManager = new LinearLayoutManager(this);

        mSuperRecyclerView.setLayoutManager(mLinearLayoutManager);
        mSuperRecyclerView.addItemDecoration(new DivItemDecoration(2, true));

        mSuperRecyclerView.getMoreProgressView().getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;

        adapter=new DishesRecycleAdapter(this);
        refreshListener = new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {
                //  mSuperRecyclerView.showMoreProgress();

            }
        };

        mSuperRecyclerView.setRefreshListener(refreshListener);
    }

    @Override
    public void bindEvents() {

    }

    @Override
    public void initData() {

    }

    private void reflash() {
        if (mSuperRecyclerView != null) {
            //实现自动下拉刷新功能
            mSuperRecyclerView.getSwipeToRefresh().post(new Runnable() {
                @Override
                public void run() {
                    mSuperRecyclerView.setRefreshing(true);//执行下拉刷新的动画
                    refreshListener.onRefresh();//执行数据加载操作
                }
            });
        }
    }

}
