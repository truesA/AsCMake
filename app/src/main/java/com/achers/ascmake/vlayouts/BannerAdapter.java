package com.achers.ascmake.vlayouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.achers.ascmake.R;
import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;

/**
 * Created on 2019/2/24 17:47
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class BannerAdapter extends DelegateAdapter.Adapter<BannerAdapter.ViewHolder> {
    public Context context;
    private LayoutHelper helper;
    private LayoutInflater inflater;
    private String name;
    private ArrayList<String> data;
    private int mViewTypeItem;

    public BannerAdapter(Context context,LayoutHelper helper,int mViewTypeItem,ArrayList<String> url){
        this.context=context;
        this.helper=helper;
        this.data=url;
        this.inflater = LayoutInflater.from(context);
        this.mViewTypeItem=mViewTypeItem;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new ViewHolder(inflater.inflate(R.layout.layout_banner_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        //设置banner样式
        viewHolder.banners.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        viewHolder.banners.setImageLoader(new GlideImageLoader());
        //设置图片集合
        viewHolder.banners.setImages(data);
        //设置banner动画效果
        viewHolder.banners.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
        //        mBanner.setBannerTitles(titles);
        //设置自动轮播，默认为true
        viewHolder.banners.isAutoPlay(true);
        //设置轮播时间
        viewHolder.banners.setDelayTime(5000);
        //设置指示器位置（当banner模式中有指示器时）
        viewHolder.banners.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        viewHolder.banners.start();

        viewHolder.banners.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(context, "banner点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private Banner banners;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            banners=itemView.findViewById(R.id.banner);
        }

    }

    /**
     * 必须重写不然会出现滑动不流畅的情况
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return 0;
    }
}
