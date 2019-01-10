package com.achers.ascmake.view.requesbuttons;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achers.ascmake.R;
import com.achers.ascmake.view.customstatusview.CustomStatusView;

/**
 * author：lhm on 2018/5/25 15:38
 * <p>
 * email：3186834196@qq.com
 */
public class NewRequesButton extends LinearLayout implements View.OnClickListener {
    /**
     * the spacing between icon and text
     * <p>
     * 图标和文字的间隔
     */
    private int iconSpacing = 0;
    /**
     * the text of button when request
     * <p>
     * 请求时按钮的文字
     */
    private String progressText = "progress";
    /**
     * the text of button when success
     * <p>
     * 请求成功时按钮的文字
     */
    private String successText = "success";
    /**
     * the text of button when failure
     * <p>
     * 请求失败时按钮的文字
     */
    private String failureText = "failure";
    /**
     * the text color of button
     * <p>
     * 按钮字体颜色
     */
    private int textColor = Color.BLACK;
    /**
     * the width of text,wrap content default
     * <p>
     * 文本宽度，默认包含内容
     */
    private int textWidth = ViewGroup.LayoutParams.WRAP_CONTENT;
    /**
     * the text size of button
     * <p>
     * 按钮字体大小
     */
    private float textSize = 20f;
    /**
     * the text of button when request
     * <p>
     * 请求时icon的颜色
     */
    private int progressColor =Color.WHITE;
    /**
     * the text of button when success
     * <p>
     * 请求成功时icon的颜色
     */
    private int successColor=Color.WHITE;
    /**
     * the text of button when failure
     * <p>
     * 请求失败时icon的颜色
     */
    private int failureColor=Color.WHITE;
    /**
     * 圆的半径
     */
    private int iconRadius=15;

    /**
     * 圆的线宽
     */
    private int iconLineWidth=2;


    private CustomStatusView customStatusView;
    private TextView buttonText,buttonText2;

    public NewRequesButton(Context context) {
        this(context,null);
    }

    public NewRequesButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NewRequesButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        setOnClickListener(this);
        if (attrs != null) {
            TypedArray typedArray =context.obtainStyledAttributes(attrs, R.styleable.NewRequesButton);
            progressText=typedArray.getString(R.styleable.NewRequesButton_bt_text_progress);
            failureText=typedArray.getString(R.styleable.NewRequesButton_bt_text_failure);
            successText=typedArray.getString(R.styleable.NewRequesButton_bt_text_success);
            failureColor=typedArray.getColor(R.styleable.NewRequesButton_bt_icon_failure,Color.WHITE);
            progressColor=typedArray.getColor(R.styleable.NewRequesButton_bt_icon_progress,Color.WHITE);
            successColor=typedArray.getColor(R.styleable.NewRequesButton_bt_icon_success,Color.WHITE);
            textSize = typedArray.getDimensionPixelSize(R.styleable.NewRequesButton_bt_text_size, 20);
            textWidth=typedArray.getDimensionPixelSize(R.styleable.NewRequesButton_bt_text_width,-1);
            iconLineWidth = typedArray.getDimensionPixelSize(R.styleable.NewRequesButton_bt_icon_line_width, 1);
            iconSpacing = typedArray.getDimensionPixelSize(R.styleable.NewRequesButton_bt_request_icon_spacing, 0);

            typedArray.recycle();
        }
      //  customStatusView=new
//        LinearLayout linearLayout = new LinearLayout(context);
//        linearLayout.setLayoutParams(new LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f));
//        linearLayout.setGravity(Gravity.CENTER_HORIZONTAL);

        buttonText = new TextView(context);
        buttonText.setLayoutParams(new LayoutParams(textWidth == -1 ? ViewGroup.LayoutParams.WRAP_CONTENT : textWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonText.setGravity(Gravity.CENTER);
        buttonText.setText(progressText);

        buttonText2 = new TextView(context);
        buttonText2.setLayoutParams(new LayoutParams(textWidth == -1 ? ViewGroup.LayoutParams.WRAP_CONTENT : textWidth,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        buttonText2.setGravity(Gravity.CENTER);
        buttonText2.setText(failureText);
       addView(buttonText2);
        addView(buttonText);
    }

    @Override
    public void onClick(View v) {
        buttonText2.setText("NO");
        invalidate();
    }
    int allwidth=0;
    int layoutWidth = 0;
    // 最小不能小于 进度圈+文字宽度+他们之间的间隔
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
        int widthMode = MeasureSpec. getMode(widthMeasureSpec);
        int heightMode = MeasureSpec. getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec. getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec. getSize(heightMeasureSpec);

        int layoutHeight = 0;
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        int cWidth = 0;
        int cHeight = 0;
        int count = getChildCount();

        if(widthMode == MeasureSpec. EXACTLY){
            //如果布局容器的宽度模式是确定的（具体的size或者match_parent），直接使用父窗体建议的宽度
            layoutWidth = sizeWidth;
            allwidth=sizeWidth;
        } else{
            //如果是未指定或者wrap_content，我们都按照包裹内容做，宽度方向上只需要拿到所有子控件中宽度做大的作为布局宽度

            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cWidth = child.getMeasuredWidth();
                //获取子控件最大宽=度
                allwidth+=cWidth;
            }
            layoutWidth = allwidth > layoutWidth ? allwidth : layoutWidth;
            Log.e("layoutWidth",allwidth+"");
        }
        //高度很宽度处理思想一样
        if(heightMode == MeasureSpec. EXACTLY){
            layoutHeight = sizeHeight;
        } else{
            for ( int i = 0; i < count; i++)  {
                View child = getChildAt(i);
                cHeight = child.getMeasuredHeight();
                layoutHeight = cHeight > layoutHeight ? cHeight : layoutHeight;
            }
        }

        // 测量并保存layout的宽高
        setMeasuredDimension(layoutWidth, layoutHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childAllWidth=0;
        int height = 0;
        int count = getChildCount();
        View child0;
        Log.e("ri", count + "");
        for(int i = 0 ;i < count;i++) {
            childAllWidth+=getChildAt(i).getMeasuredWidth();
        }
        Log.e("ri", childAllWidth + "");
        child0 =getChildAt(0);
        child0.layout(allwidth/2-childAllWidth/2,0,allwidth/2+childAllWidth/2,child0.getMeasuredHeight());
        Log.e("ri", allwidth/2-childAllWidth/2 + "");
        View child1;
        child1 =getChildAt(1);
        child1.layout(allwidth/2-childAllWidth/2+child0.getMeasuredWidth(),0,allwidth/2+childAllWidth/2,child0.getMeasuredHeight());
        Log.e("ri", allwidth + ""+layoutWidth);
        //        for(int i = 0 ;i < count;i++) {
//            child = getChildAt(i);
//            child.layout(0, height, child.getMeasuredWidth(),height +  child.getMeasuredHeight());
//            height += child.getMeasuredHeight();

    }
}
