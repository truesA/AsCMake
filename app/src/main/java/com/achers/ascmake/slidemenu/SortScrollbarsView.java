package com.achers.ascmake.slidemenu;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.achers.ascmake.R;

/**
 * Created on 2019/5/14 11:12
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class SortScrollbarsView extends View {

    private Paint bgPaint;//绘制背景的画笔
    private Paint scrollbarPaint;//绘制进度的画笔
    private float offsetX = 0;
    private float barWidth;//圆弧进度条宽度
    private int defaultSize;//自定义View默认的宽高

    private int scrollbarColor;
    private int scrollbarBgColor;
    private float scrollbarHeight;
    private float scrollbarBgWidth;
    private float scrollbarWidth;
    private float scrollbarRadio;


    public SortScrollbarsView(Context context) {
        this(context, null);
    }

    public SortScrollbarsView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SortScrollbarsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //省略部分代码...
        defaultSize = dip2px(context, 100);
        barWidth = dip2px(context, 10);

        //省略部分代码...
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SortScrollbarsView);
        scrollbarColor = typedArray.getColor(R.styleable.SortScrollbarsView_scroll_color, ContextCompat.getColor(context, R.color.white));
        scrollbarBgColor = typedArray.getColor(R.styleable.SortScrollbarsView_scroll_bg_color, ContextCompat.getColor(context, R.color.gravy));
        scrollbarHeight = typedArray.getDimension(R.styleable.SortScrollbarsView_scroll_height, dip2px(context, 5));
        scrollbarBgWidth = typedArray.getDimension(R.styleable.SortScrollbarsView_scroll_bg_width, dip2px(context, 150));
        scrollbarRadio = typedArray.getFloat(R.styleable.SortScrollbarsView_scroll_radio, 0.5f);
        scrollbarWidth = (scrollbarBgWidth * scrollbarRadio);

        typedArray.recycle();

        initPaint();

    }

    /**
     * 初始化 笔
     */
    private void initPaint() {
        //省略部分代码...
        scrollbarPaint = new Paint();
        scrollbarPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        scrollbarPaint.setColor(scrollbarColor);
        scrollbarPaint.setAntiAlias(true);//设置抗锯齿
        scrollbarPaint.setStrokeWidth(scrollbarHeight);//随便设置一个画笔宽度，看看效果就好，之后会通过attr自定义属性进行设置
        scrollbarPaint.setStrokeCap(Paint.Cap.ROUND);

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.STROKE);//只描边，不填充
        bgPaint.setColor(scrollbarBgColor);
        bgPaint.setAntiAlias(true);//设置抗锯齿
        bgPaint.setStrokeWidth(scrollbarHeight);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);

    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//
//        int height = measureSize(defaultSize, heightMeasureSpec);
//        int width = measureSize(defaultSize, widthMeasureSpec);
//        int min = Math.min(width, height);// 获取View最短边的长度
//        setMeasuredDimension(min, min);// 强制改View为以最短边为长度的正方形
//
////        if(min >= barWidth*2){//这里简单限制了圆弧的最大宽度
////            mRectF.set(barWidth/2,barWidth/2,min-barWidth/2,min-barWidth/2);
////        }
//
//    }


    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;

        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED: {//如果没有指定大小，就设置为默认大小
                mySize = defaultSize;
                break;
            }
            case MeasureSpec.AT_MOST: {//如果测量模式是最大取值为size
                //我们将大小取最大值,你也可以取其他值
                mySize = size;
                break;
            }
            case MeasureSpec.EXACTLY: {//如果是固定的大小，那就不要去改变它
                mySize = size;
                break;
            }
        }
        return mySize;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(100, widthMeasureSpec);
        int height = getMySize(10, heightMeasureSpec);
//
//        if (width < height) {
//            height = width;
//        } else {
//            width = height;
//        }
        setMeasuredDimension(width, height);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawLine(getWidth()/2-scrollbarBgWidth/2, 20, getWidth()/2-scrollbarBgWidth/2 + scrollbarBgWidth, 20, bgPaint);
        canvas.drawLine(getWidth()/2-scrollbarBgWidth/2+ offsetX, 20, getWidth()/2-scrollbarBgWidth/2 + scrollbarWidth + offsetX, 20, scrollbarPaint);

    }

    public void changeOffsetX(int dx, int layoutWidth) {
        Log.e("changeOffsetX", dx + "-----" + scrollbarWidth);
        float radio = scrollbarWidth / layoutWidth;

        if (dx * radio< scrollbarWidth) {
            Log.e("changeOffsetX", "return---"+radio);
            offsetX = dx * radio;
            Log.e("changeOffsetX", "return offsetX---"+offsetX);
            invalidate();
        } else {
            offsetX = scrollbarWidth;
            invalidate();
        }

    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
