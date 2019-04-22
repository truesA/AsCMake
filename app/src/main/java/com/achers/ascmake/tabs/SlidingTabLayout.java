package com.achers.ascmake.tabs;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import com.achers.ascmake.R;

import java.util.List;

/**
 * 首页滑动条
 *
 * @author gaok
 * @date 2017/7/6
 */
public class SlidingTabLayout extends HorizontalScrollView {
    private Context mContext;
    private int selection = 0;
    /**
     * tab容器
     */
    private LinearLayout mItemsLayout;
    /**
     * 指示器
     */
    private Bitmap mSlideIcon;
    /**
     * 指示器初始X偏移量
     */
    private int mInitTranslationX;
    /**
     * 指示器初始Y偏移量
     */
    private int mInitTranslationY;
    /**
     * 滑动过程中指示器的水平偏移量
     */
    private int mTranslationX;
    /**
     * tab总数
     */
    private int mTotalItemsCount;
    /**
     * 指示器绘制数据的初始化标志
     */
    private boolean isFirstTime = true;
    /**
     * 页面可见的tab数量，默认4个
     */
    private int VISIBLE_TAB_COUNT = 4;
    /**
     * 移动到倒数第几个，容器开始滑动
     */
    private int START_SCROLL = 2;
    /**
     * 标题正常时的颜色
     */
//    private static final int COLOR_TEXT_NORMAL = 0x77FFFFFF;
    private static final int COLOR_TEXT_NORMAL = 0xFF000000;
    /**
     * 标题选中时的颜色
     */
//    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFFFFFFFF;
    private static final int COLOR_TEXT_HIGHLIGHTCOLOR = 0xFF000000;
    private ViewPager mViewPager;
    private List<String> mTabTitles;
    private int mTabVisibleCount = VISIBLE_TAB_COUNT;
    private OnPageChangeListener onPageChangeListener;


    /**
     * 文字大小
     */
    private int mTextSize;

    /**
     * 文字正常时的颜色
     */
    private int mTextColorNormal = 0xFF000000;

    /**
     * 文字选中时的颜色
     */
    private int mTextColorSelected = 0xFF000000;

    /**
     * 指示器正常时的颜色
     */
    private int mIndicatorColor = 0x77FFFFFF;

    /**
     * 圖片指示器宽
     */
    private int mBitmapNewWidth;

    /**
     * 圖片指示器高
     */
    private int mBitmapNewHeight;

    private boolean mBitmapNewSetting;


    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
//        this.mSlideIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_indicator);
        this.mItemsLayout = new LinearLayout(context);
        initTabStripParams();
        addView(mItemsLayout, 0, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
//        Bitmap bitmap =BitmapFactory.decodeResource(getResources(), R.drawable.ic_indicator);
//        this.mSlideIcon = setImgSize(bitmap,dipToPx(context,20f),dipToPx(context,2f));

        //
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.SlidingTabLayouts);
        mBitmapNewSetting=a.getBoolean(R.styleable.SlidingTabLayouts_tab_slide_bitmap_setting,false);
        mBitmapNewWidth=a.getDimensionPixelSize(R.styleable.SlidingTabLayouts_tab_slide_bitmap_width,30);
        mBitmapNewHeight=a.getDimensionPixelSize(R.styleable.SlidingTabLayouts_tab_slide_bitmap_height,2);
        mTextSize=a.getDimensionPixelSize(R.styleable.SlidingTabLayouts_tab_slide_text_size,dipToPx(context, 15));
        mTextColorNormal=a.getColor(R.styleable.SlidingTabLayouts_tab_slide_text_color_normal,COLOR_TEXT_NORMAL);
        mTextColorSelected=a.getColor(R.styleable.SlidingTabLayouts_tab_slide_text_color_selected,COLOR_TEXT_HIGHLIGHTCOLOR);
        mTabVisibleCount=a.getInteger(R.styleable.SlidingTabLayouts_tab_slide_indicator_visible_count,VISIBLE_TAB_COUNT);

        Drawable drawable = a.getDrawable(R.styleable.SlidingTabLayouts_tab_slide_style_bitmap_src);

        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                if (mBitmapNewSetting){
                   Bitmap b= ((BitmapDrawable) drawable).getBitmap();
                    this.mSlideIcon = setImgSize(b,mBitmapNewWidth,mBitmapNewHeight);
                }else {
                    this.mSlideIcon = ((BitmapDrawable) drawable).getBitmap();
                }

            } else if (drawable instanceof NinePatchDrawable) {
                // .9图处理
                Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
                Canvas canvas = new Canvas(bitmap);
                drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
                drawable.draw(canvas);

                if (mBitmapNewSetting){
                    this.mSlideIcon = setImgSize(bitmap,mBitmapNewWidth,mBitmapNewHeight);
                }else {
                    this.mSlideIcon = bitmap;
                }

            }
        } else {
            if (mBitmapNewSetting){
                Bitmap bp  = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
                this.mSlideIcon = setImgSize(bp,mBitmapNewWidth,mBitmapNewHeight);
            }else {
                this.mSlideIcon = BitmapFactory.decodeResource(getResources(), R.drawable.icon);
            }

        }
        a.recycle();
    }

    public void scroll(int position, float positionOffset) {
        int tabWidth = mItemsLayout.getChildAt(position).getWidth();
        mTranslationX = (int) ((position + positionOffset) * tabWidth);

        // 容器滚动，当移动到倒数最后一个的时候，开始滚动
        if (positionOffset > 0 && position >= (VISIBLE_TAB_COUNT - START_SCROLL) && mTotalItemsCount > VISIBLE_TAB_COUNT) {
            if (VISIBLE_TAB_COUNT != 1) {
                //注意这里是整体滑动，使得tabs跟指示器保持相对静止
                this.scrollTo((position - (VISIBLE_TAB_COUNT - START_SCROLL)) * tabWidth + (int) (tabWidth * positionOffset), 0);
            } else
            // 为count为1时 的特殊处理
            {
                this.scrollTo(position * tabWidth + (int) (tabWidth * positionOffset), 0);
            }
        }
        invalidate();
    }

    private void initTabStripParams() {
        mItemsLayout.setClipChildren(false);
        mItemsLayout.setClipToPadding(false);
        mItemsLayout.setGravity(Gravity.BOTTOM);
        mItemsLayout.setPadding(0, 0, 0, 0);
    }

    /**
     * 绘制指示器
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        canvas.save();
        // 平移到正确的位置
        canvas.translate(mInitTranslationX + mTranslationX, this.mInitTranslationY);//修正tabs的平移量
        canvas.drawBitmap(this.mSlideIcon, 0, 0, null);
        canvas.restore();
        super.dispatchDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (isFirstTime && (mTotalItemsCount > 0) && getItemView(this.selection) != null) {
            View currentItemView = getItemView(this.selection);
            this.mInitTranslationX = (currentItemView.getLeft() + currentItemView.getWidth() / 2 - this.mSlideIcon.getWidth() / 2);
            this.mInitTranslationY = (b - t - this.mSlideIcon.getHeight());
            isFirstTime = false;
        }
    }

    public void setVisibleTabCount(int VISIBLE_TAB_COUNT) {
        this.VISIBLE_TAB_COUNT = VISIBLE_TAB_COUNT;
    }

    public void setStartScroll(int START_SCROLL) {
        this.START_SCROLL = START_SCROLL;
    }

    public View getItemView(int itemPosition) {
        if ((itemPosition >= 0) && (itemPosition < this.mTotalItemsCount)) {
            return this.mItemsLayout.getChildAt(itemPosition);
        }
        return null;
    }

    public void setData(List<String> datas) {
        mTotalItemsCount = 0;
        // 如果传入的list有值，则移除布局文件中设置的view
        if (datas != null && datas.size() > 0) {
            this.mItemsLayout.removeAllViews();
            this.mTabTitles = datas;

            for (String title : mTabTitles) {
                // 添加view
                this.mItemsLayout.addView(generateTextView(title));
                mTotalItemsCount++;
            }
            // 设置item的click事件
            setItemClickEvent();
        }
    }

    /**
     * 对外的ViewPager的回调接口
     *
     * @author zhy
     */
    public interface PageChangeListener {
        void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

        void onPageSelected(int position);

        void onPageScrollStateChanged(int state);
    }

    // 设置关联的ViewPager
    public void setViewPager(ViewPager mViewPager, int pos) {
        this.mViewPager = mViewPager;

        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // 设置字体颜色高亮
                resetTextViewColor();
                highLightTextView(position);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageSelected(position);
                }
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 滚动
                scroll(position, positionOffset);

                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 回调
                if (onPageChangeListener != null) {
                    onPageChangeListener.onPageScrollStateChanged(state);
                }

            }
        });
        // 设置当前页
        mViewPager.setCurrentItem(pos);
        // 高亮
        highLightTextView(pos);
    }

    /**
     * 重置文本颜色
     */
    private void resetTextViewColor()
    {
        for (int i = 0; i < mItemsLayout.getChildCount(); i++)
        {
            View view = mItemsLayout.getChildAt(i);
            if (view instanceof TextView)
            {
                ((TextView) view).setTextColor(mTextColorNormal);
                ((TextView) view).setScaleX(1f);
                ((TextView) view).setScaleY(1f);
            }
        }
    }

    /**
     * 高亮文本
     *
     * @param position
     */
    protected void highLightTextView(int position)
    {
        View view = mItemsLayout.getChildAt(position);
        if (view instanceof TextView)
        {
            ((TextView) view).setTextColor(mTextColorSelected);
            changeTabSelect((TextView) view);
        }

    }

    private void setItemClickEvent() {
        int cCount = mItemsLayout.getChildCount();
        for (int i = 0; i < cCount; i++) {
            final int j = i;
            View view = mItemsLayout.getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    mViewPager.setCurrentItem(j);
                }
            });
        }
    }

    /**
     * 根据标题生成我们的TextView
     *
     * @param text
     * @return
     */
    private TextView generateTextView(String text) {
        TextView tv = new TextView(getContext());
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.width = getScreenWidth() / mTabVisibleCount;
        tv.setGravity(Gravity.CENTER);
//        tv.setTextColor(0x77FFFFFF);
        tv.setTextColor(mTextColorNormal);
        tv.setText(text);
//        tv.setPadding(10,10,10,10);
        tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
        tv.setLayoutParams(lp);
        return tv;
    }

    /**
     * 获得屏幕的宽度
     *
     * @return
     */
    public int getScreenWidth() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public LinearLayout getLinearLayout() {
        return mItemsLayout;
    }

    /**
     * 改变TabLayout的View到选中状态
     * 使用属性动画改编Tab中View的状态
     */
    private void changeTabSelect(TextView tab) {
        final View view = tab;
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "alpha", 1.0F, 1.2F)
                .setDuration(200);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(0.5f + (cVal - 1f) * (0.5f / 0.1f));
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    /**
     * 改变TabLayout的View到未选中状态
     */
    private void changeTabNormal(TextView tab) {
        final View view = tab;
        ObjectAnimator anim = ObjectAnimator
                .ofFloat(view, "alpha", 1.0F, 0.9F)
                .setDuration(200);
        anim.start();
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float cVal = (Float) animation.getAnimatedValue();
                view.setAlpha(1f - (1f - cVal) * (0.5f / 0.1f));
                view.setScaleX(cVal);
                view.setScaleY(cVal);
            }
        });
    }

    public Bitmap setImgSize(Bitmap bm, int newWidth ,int newHeight) {
        // 获得图片的宽高.
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例.
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数.
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片.
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        return newbm;
    }

    /**
     * dp转px
     *
     * @param context context
     * @param dpValue dp
     * @return px
     */
    private static int dipToPx(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}