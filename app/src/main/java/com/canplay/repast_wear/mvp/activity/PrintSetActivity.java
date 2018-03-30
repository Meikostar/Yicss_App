package com.canplay.repast_wear.mvp.activity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.canplay.repast_wear.R;
import com.canplay.repast_wear.base.BaseActivity;
import com.canplay.repast_wear.base.BaseApplication;
import com.canplay.repast_wear.base.RxBus;
import com.canplay.repast_wear.base.SubscriptionBean;
import com.canplay.repast_wear.bean.ORDER;
import com.canplay.repast_wear.bean.PrintBean;
import com.canplay.repast_wear.mvp.adapter.PrintAdapter;
import com.canplay.repast_wear.view.NavigationBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscription;
import rx.functions.Action1;

import static com.canplay.repast_wear.bean.PrintBean.PRINT_TYPE;

public class PrintSetActivity extends BaseActivity {

    @BindView(R.id.iv_star)
    ImageView ivStar;
    @BindView(R.id.ll_reflash)
    LinearLayout llReflash;
    @BindView(R.id.tv_contact)
    TextView tvContact;
    @BindView(R.id.ll_contact)
    LinearLayout llContact;
    @BindView(R.id.switch1)
    Switch mSwitch;
    @BindView(R.id.list_view)
    ListView listView;
    @BindView(R.id.tv_text)
    TextView tv_text;

    public static final int REQUEST_ENABLE_BT = 1;
    @BindView(R.id.navigationBar)
    NavigationBar navigationBar;
    //蓝牙适配器
    private BluetoothAdapter mBluetoothAdapter;
    private PrintAdapter adapter;
    private ORDER order;
    private int type;
    private Subscription mSubscription;
    @Override
    public void initViews() {
        setContentView(R.layout.activity_print_set);
        ButterKnife.bind(this);
        navigationBar.setNavigationBarListener(this);
        //初始化
        //广播注册
        BaseApplication.adapter.context=this;
        order = (ORDER) getIntent().getSerializableExtra("order");
        type = getIntent().getIntExtra("type", 1);
//        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
//        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
//        registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        mBluetoothAdapter = BaseApplication.mBluetoothAdapter;
        mBluetoothDevicesDatas = BaseApplication.mBluetoothDevicesDatas;
        adapter = BaseApplication.adapter;
        adapter.setOrder(order);
        listView.setAdapter(adapter);
        chechBluetooth();
        addViewListener();
        mSubscription = RxBus.getInstance().toObserverable(SubscriptionBean.RxBusSendBean.class).subscribe(new Action1<SubscriptionBean.RxBusSendBean>() {
            @Override
            public void call(SubscriptionBean.RxBusSendBean bean) {
                if (bean == null) return;


            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                throwable.printStackTrace();
            }
        });
        RxBus.getInstance().addSubscription(mSubscription);
    }

    @Override
    public void bindEvents() {
        tvContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchDevices();
            }
        });
    }

    @Override
    public void initData() {

    }

    /**
     * 判断有没有开启蓝牙
     */
    private void chechBluetooth() {
        //没有开启蓝牙
        if (mBluetoothAdapter != null) {
            if (!mBluetoothAdapter.isEnabled()) {
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // 设置蓝牙可见性，最多300秒
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 20);
                startActivityForResult(intent, REQUEST_ENABLE_BT);
                setViewStatus(true);
                //开启蓝牙
            } else {
                if(BaseApplication.maps.size()==0){
                    searchDevices();
                }

                setViewStatus(true);
                mSwitch.setChecked(true);
            }
        }
    }

    private ArrayList<PrintBean> mBluetoothDevicesDatas;

    /**
     * 搜索状态调整
     *
     * @param isSearch 是否开始搜索
     */
    private void setViewStatus(boolean isSearch) {
        int count = adapter.getCount();
        if (count == 0) {
            tvContact.setVisibility(View.VISIBLE);
        }
//        if (isSearch) {
//            mFloatingActionButton.setVisibility(View.GONE);
//            searchHint.setVisibility(View.VISIBLE);
//            mProgressBar.setVisibility(View.VISIBLE);
//        } else {
//            mFloatingActionButton.setVisibility(View.VISIBLE);
//            mProgressBar.setVisibility(View.GONE);
//            searchHint.setVisibility(View.GONE);
//        }
    }

    /**
     * 搜索蓝牙设备
     */
    public void searchDevices() {
        BaseApplication.getInstance().clear();
        mBluetoothDevicesDatas.clear();
        adapter.notifyDataSetChanged();
        //开始搜索蓝牙设备
        mBluetoothAdapter.startDiscovery();
    }

    /**
     * 添加View的监听
     */
    private void addViewListener() {
        //蓝牙的状态
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    openBluetooth();
                    setViewStatus(true);
                    tv_text.setText("蓝牙功能已启用");
                } else {
                    closeBluetooth();
                    tv_text.setText("蓝牙功能已关闭");
                }
            }
        });
//        //重新搜索
//        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mSwitch.isChecked()) {
//                    searchDevices();
//                    setViewStatus(true);
//                } else {
//                    openBluetooth();
//                    setViewStatus(true);
//                }
//            }
//        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK){
            tv_text.setText("蓝牙功能已启用");
            mSwitch.setChecked(true);
        }else {
            tv_text.setText("蓝牙功能已关闭");
        }
    }

    /**
     * 打开蓝牙
     */
    public void openBluetooth() {
        Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE); // 设置蓝牙可见性，最多300秒
        intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 20);
        startActivityForResult(intent, REQUEST_ENABLE_BT);

    }

//    /**
//     * 通过广播搜索蓝牙设备
//     */
//    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
//        public void onReceive(Context context, Intent intent) {
//            String action = intent.getAction();
//            // 把搜索的设置添加到集合中
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                tvContact.setVisibility(View.GONE);
//                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
//                //已经匹配的设备
//                if (device.getBondState() == BluetoothDevice.BOND_BONDED) {
//                    addBluetoothDevice(device);
//
//                    //没有匹配的设备
//                } else {
//                    addBluetoothDevice(device);
//                }
//                adapter.notifyDataSetChanged();
//                //搜索完成
//            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
//                setViewStatus(false);
//            }
//        }
//
//        /**
//         * 添加数据
//         * @param device 蓝牙设置对象
//         */
//        private void addBluetoothDevice(BluetoothDevice device) {
//
//            for (int i = 0; i < mBluetoothDevicesDatas.size(); i++) {
//                if (device.getAddress().equals(mBluetoothDevicesDatas.get(i).getAddress())) {
//                    mBluetoothDevicesDatas.remove(i);
//                }
//            }
//            if (device.getBondState() == BluetoothDevice.BOND_BONDED && device.getBluetoothClass().getDeviceClass() == PRINT_TYPE) {
//                mBluetoothDevicesDatas.add(0, new PrintBean(device));
//            } else {
//                mBluetoothDevicesDatas.add(new PrintBean(device));
//            }
//        }
//    };

    /**
     * 关闭蓝牙
     */
    public void closeBluetooth() {
        mBluetoothAdapter.disable();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.unsubscribe();
    }

//        //屏蔽返回键的代码:
//    public boolean onKeyDown(int keyCode,KeyEvent event)
//    {
//        switch(keyCode)
//        {
//            case KeyEvent.KEYCODE_BACK :
//
//                if(type==1){
//                    startActivity(new Intent(this,OrderDetailActivity.class));
//                }else if(type==2){
//                    startActivity(new Intent(this,OrderDetailfatherActivity.class));
//                }else {
//                    Intent intent = new Intent(this, MainActivity.class);
//                    startActivity(intent);
//                }
//                    return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }
}
