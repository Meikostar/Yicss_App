package com.canplay.repast_wear.mvp.adapter.recycle;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.canplay.repast_wear.R;
import com.canplay.repast_wear.bean.ORDER;
import com.canplay.repast_wear.util.TextUtil;
import com.canplay.repast_wear.util.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by mykar on 17/4/12.
 */
public class OrderMangerRecycleAdapter extends BaseRecycleViewAdapter {

    private Context context;

    public OrderMangerRecycleAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_manger_itemview, null);

        return new OrderMangerViewHolder(view);
    }
//0待接单，1待结账 2已完成，4已撤销
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        OrderMangerViewHolder holders = (OrderMangerViewHolder) holder;
      final ORDER data= (ORDER) datas.get(position);
        holders.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.clickListener(position,data.detailNo);
            }
        });
          if(TextUtil.isNotEmpty(data.cookbookName)){
              holders.tvName.setText(data.cookbookName);
          } if(TextUtil.isNotEmpty(data.totalPrice)){
            holders.tvMoney.setText("￥ "+data.totalPrice);
        }
          if(data.createTime!=0){
              holders.tvTime.setText(TimeUtil.formatTimes(data.createTime));
          }
          if(TextUtil.isNotEmpty(data.tableNo)){
              holders.tv_number.setText(data.tableNo);
          }
        if(data.state==0){
            holders.tvStatus.setText("待接单");
            holders.tvMoney.setTextColor(context.getResources().getColor(R.color.yellows));
            holders.flBg.setBackgroundResource(R.drawable.yuan_y);
        }else if(data.state==1){
            holders.tvStatus.setText("待结账");
            holders.tvMoney.setTextColor(context.getResources().getColor(R.color.color9));
            holders.flBg.setBackgroundResource(R.drawable.yuan_b);
        }else if(data.state==2){
            holders.tvStatus.setText("已完成");
            holders.tvMoney.setTextColor(context.getResources().getColor(R.color.color9));
            holders.flBg.setBackgroundResource(R.drawable.yuan_g);
        }else if(data.state==3){
            holders.tvStatus.setText("已撤销");
            holders.tvMoney.setTextColor(context.getResources().getColor(R.color.color9));
            holders.flBg.setBackgroundResource(R.drawable.yuan_f);
        }

    }

    @Override
    public int getItemCount() {
        int count = 0;

        if (datas != null && datas.size() > 0) {
            count = datas.size();
        }

        return count;
    }
    public static class OrderMangerViewHolder extends RecyclerView.ViewHolder  {

        @BindView(R.id.fl_bg)
        FrameLayout flBg;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_status)
        TextView tvStatus;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_money)
        TextView tvMoney;
        @BindView(R.id.tv_number)
        TextView tv_number;
        @BindView(R.id.card)
        CardView cardView;
        public OrderMangerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
    public void setClickListener(OnItemClickListener listener){
        this.listener=listener;
    }
    public OnItemClickListener listener;
    public interface  OnItemClickListener{
        void clickListener(int poiston,String id);
    }
}
