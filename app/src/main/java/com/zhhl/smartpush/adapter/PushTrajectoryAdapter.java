package com.zhhl.smartpush.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhhl.smartpush.R;
import com.zhhl.smartpush.data.PushInfo;

import butterknife.BindView;

/**
 * Created by miao on 2018/12/28.
 */
public class PushTrajectoryAdapter extends BaseAdapter<PushInfo.DataBean, PushTrajectoryAdapter.PushTrajectoryViewHolder> {

    @Override
    protected PushTrajectoryViewHolder create(View view, int itemViewType) {
        return new PushTrajectoryViewHolder(view);
    }

    @Override
    protected void bindView(PushTrajectoryViewHolder vh, int position, PushInfo.DataBean item) {
        vh.mName.setText(item.getZtname());
        vh.mDate.setText(item.getZttime());
        vh.mContent.setText(item.getTrackdiscription());
        vh.idCard.setText(item.getZtidcard());
        vh.mTime.setText(item.getCreatetime());
//        vh.mTime.setVisibility(View.GONE);
    }

    @Override
    protected int getDefaultLayout() {
        return R.layout.item_zaitao_renyuan;
    }

    class PushTrajectoryViewHolder extends BaseAdapter.ViewHolder {
        @BindView(R.id.idCard)
        TextView idCard;
        @BindView(R.id.mContent)
        TextView mContent;
        @BindView(R.id.mTime)
        TextView mTime;

        @BindView(R.id.mName)
        TextView mName;
        @BindView(R.id.mDate)
        TextView mDate;

        PushTrajectoryViewHolder(View view) {
            super(view);
        }
    }
}
