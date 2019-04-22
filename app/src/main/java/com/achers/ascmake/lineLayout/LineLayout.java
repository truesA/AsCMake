package com.achers.ascmake.lineLayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.achers.ascmake.R;

/**
 * Created on 2019/2/14 15:18
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class LineLayout extends ViewGroup {

    /**
     * 两个View之间距的比例
     */
    private float mViewMarginRate = 0.5f;
    /**
     * 是不是从后面向前摆放
     */
    private boolean mIsReverse = true;

    private LineAdapter mAdapter;
    private DataSetObserver mObserver;

    public LineLayout(Context context) {
        this(context, null);
    }

    public LineLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LineLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.LineLayout);
        // 默认是在一半的位置
        mViewMarginRate = array.getFloat(R.styleable.LineLayout_lineViewMarginRate, mViewMarginRate);
        // 默认第一个在上面
        mIsReverse = array.getBoolean(R.styleable.LineLayout_lineIsReverse, mIsReverse);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 1.测量控件的宽高
        // 获取自已的测量模式
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        // 获取自已的宽高
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);

        int count = getChildCount();//获取 子控件数量
        int height = 0; //最终高度
        int width = 0; //最终宽度

        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);//获取每一个child
            measureChild(child, widthMeasureSpec, heightMeasureSpec); //测量子控件
            //测量后去获得 子控件的宽度
            int measuredWidth = child.getMeasuredWidth();
            //计算控件的宽度
            if (i == 0) {//如果是第一个子控件 所占的宽度为子控件本身的宽度
                width = measuredWidth;
            } else { //如果不是第一个 且 子控件数量为2个或2个以上 宽度为没错累加一个子控件宽度 * 间隔比率
                width += (int) (mViewMarginRate * width + 0.5f);
            }

            //测量后去获得 子控件的高度
            int measuredHeight = child.getMeasuredHeight();
            //取子控件中最高的高度
            height = Math.max(height, measuredHeight);
        }
        //循环完 我们还需要加上 padding
        width += getPaddingLeft() + getPaddingRight();
        height += getPaddingBottom() + getPaddingTop();
        //最后将宽高设置上去
        setMeasuredDimension(
                //如果是设置了精确模式 那么宽高就是 外部自己的值 否则就是我们计算的值
                modeWidth == MeasureSpec.EXACTLY ? sizeWidth : width,
                modeHeight == MeasureSpec.EXACTLY ? sizeHeight : height
        );
    }

    //摆放 子View
    //摆放很简单，从（0，0，childWidth,childHeight）开始。
    //第二个子View的左边开始位置就是叠加左边的间距。
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //获取 子控件数量
        int count = getChildCount();
        //考虑padding
        int childLeft = getPaddingLeft();
        int childTop = getPaddingTop();
        // 循环摆放
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            int width = child.getMeasuredWidth();
            int height = child.getMeasuredHeight();
            if (i > 0) {
                // 计算第二个后面子View左边的位置
                childLeft += (int) (mViewMarginRate * width + 0.5f);
            }
            //摆放子view
            child.layout(childLeft, childTop, childLeft + width, childTop + height);
        }
    }


    /**
     * 怎么样让前面的View压后面的View
     * 设置充许改变绘制顺序：setChildrenDrawingOrderEnabled(true);
     * 复写getChildDrawingOrder这个方法
     * <p>
     * <p>
     * 注意：为了调用此方法，您必须启用子订购
     * 首先调用{@link #setChildrenDrawingOrderEnabled（boolean）}。
     * <p>
     * 默认  return i;
     */
    @Override
    protected int getChildDrawingOrder(int childCount, int i) {
        // 确定View的绘制优先级、
        if (!mIsReverse) {  //正常 后面的View 压 前面的View
            return i;
        }
        return childCount - 1 - i;
    }


    /**
     * 设置Adapter
     */
    public void setAdapter(LineAdapter adapter) {
        // 移除监听
        if (mAdapter != null && mObserver != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter = null;
            mObserver = null;
        }
        if (adapter == null) {
            throw new NullPointerException("FlowBaseAdapter is null");
        }
        mAdapter = adapter;
        resetLayout();
        mObserver = new DataSetObserver() {
            @Override
            public void onChanged() {
                resetLayout();
            }
        };
        mAdapter.registerDataSetObserver(mObserver);

    }

    /**
     * 重新添加布局
     */
    private void resetLayout() {
        removeAllViews();
        int count = mAdapter.getCount();
        for (int i = 0; i < count; i++) {
            View view = mAdapter.getView(i, this);
            addView(view);
        }
    }

    @Override
    protected void onDetachedFromWindow() {
        // 移除监听
        if (mAdapter != null && mObserver != null) {
            mAdapter.unregisterDataSetObserver(mObserver);
            mAdapter = null;
            mObserver = null;

        }
        super.onDetachedFromWindow();
    }
}



