package com.achers.ascmake.gangedrecyclerview;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.achers.ascmake.R;

import java.util.List;

/**
 * Created on 2019/5/31 10:44
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class ClassifyDetailAdapter2 extends RvAdapter<RightBean2> {

    public ClassifyDetailAdapter2(Context context, List<RightBean2> list, RvListener listener) {
        super(context, list, listener);
    }


    @Override
    protected int getLayoutId(int viewType) {
        if (viewType==0){
            return R.layout.item_img;
        }else if (viewType==1){
            return R.layout.item_title;
        }else {
            return R.layout.item_classify_detail;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (list.get(position).getIsTitle() == 0) {
            return 0;
        } else if (list.get(position).getIsTitle() == 1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    protected RvHolder getHolder(View view, int viewType) {
        return new ClassifyDetailAdapter2.ClassifyHolder(view, viewType, listener);
    }

    public class ClassifyHolder extends RvHolder<RightBean2> {
        TextView tvCity;
        ImageView avatar;
        TextView tvTitle;
        ImageView ivHead;

        public ClassifyHolder(View itemView, int type, RvListener listener) {
            super(itemView, type, listener);
            switch (type) {
                case 0:
                    ivHead =(ImageView) itemView.findViewById(R.id.iv_img);
                    break;
                case 1:
                    tvTitle = (TextView) itemView.findViewById(R.id.tv_title);

                    break;
                case 2:
                    tvCity = (TextView) itemView.findViewById(R.id.tvCity);
                    avatar = (ImageView) itemView.findViewById(R.id.ivAvatar);
                    break;
            }

        }

        @Override
        public void bindHolder(RightBean2 sortBean, int position) {
            int itemViewType = ClassifyDetailAdapter2.this.getItemViewType(position);
            switch (itemViewType) {
                case 0:
                    ivHead.setImageResource(R.drawable.dk_data_clean);
                    break;
                case 1:
                    tvTitle.setText(sortBean.getName());
                    break;
                case 2:
                    tvCity.setText(sortBean.getName());
                    break;
            }

        }
    }
}
