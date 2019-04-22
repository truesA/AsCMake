package com.achers.ascmake.arout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created on 2019/2/11 14:13
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
public class SlideBackView extends View {
    String TAG = "SlideBackView";

    Path path, arrowPath;
    Paint paint, arrowPaint;

    float controlX = 0;//曲线的控制点

    String backViewColor = "#B3000000";

    float backViewHeight = 0;
    public static float width = 40;
    public static float height = 260;

    int offset;

    float controlOver=dp2px(40);

    public SlideBackView(Context context) {
        this(context, null);
    }

    public SlideBackView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideBackView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        backViewHeight = dp2px(260);

        path = new Path();
        arrowPath = new Path();

        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(Color.parseColor(backViewColor));
        paint.setStrokeWidth(1);

        arrowPaint = new Paint();
        arrowPaint.setAntiAlias(true);
        arrowPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        arrowPaint.setColor(Color.WHITE);
        arrowPaint.setStrokeWidth(5);
        arrowPaint.setStrokeCap(Paint.Cap.ROUND);

        setAlpha(0);
    }
    private float arrowSize = 10; // 箭头图标大小
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画外面的东西
        float x1 = SlideBackActivity.screenWidth - SlideBackActivity.screenWidth * offset;

        path.reset();
        path.moveTo(x1, dp2px(40));
        path.quadTo(x1, backViewHeight / 4, Math.abs(x1 - controlX / 3), backViewHeight * 3 / 8);

        path.quadTo(Math.abs(x1 - controlX * 5 / 8), backViewHeight / 2, Math.abs(x1 - controlX / 3), backViewHeight * 5 / 8);

        path.quadTo(x1, backViewHeight * 6 / 8, x1, backViewHeight-dp2px(40));
        canvas.drawPath(path, paint);

        //画里面的箭头
        float x2 = Math.abs(SlideBackActivity.screenWidth - SlideBackActivity.screenWidth * offset - controlX / 6);

        arrowPath.reset();
        arrowPath.moveTo(x2 + (dp2px(5) * (controlX / (SlideBackActivity.screenWidth / 4f))), backViewHeight * 15.5f / 32);
        arrowPath.lineTo(x2, backViewHeight * 16f / 32);
        arrowPath.moveTo(x2, backViewHeight * 16f / 32);
        arrowPath.lineTo(x2 + (dp2px(5) * (controlX / (SlideBackActivity.screenWidth / 4f))), backViewHeight * 16.5f / 32);
        canvas.drawPath(arrowPath, arrowPaint);

        // 箭头是先直线由短变长再折弯变成箭头状的
        // 依据当前拉动距离和最大拉动距离计算箭头大小值
        // 大小到一定值后开始折弯，计算箭头角度值
//        float arrowZoom = controlX / (SlideBackActivity.screenWidth / 6); // 箭头大小变化率
//        float arrowAngle = arrowZoom < 0.75f ? 0 : (arrowZoom - 0.75f) * 2; // 箭头角度变化率
//        // 结合箭头大小值与箭头角度值设置折线路径
//        arrowPath.moveTo(controlX / 4 + (arrowSize * arrowAngle), backViewHeight / 2 - (arrowZoom * arrowSize));
//        arrowPath.lineTo(controlX / 4 - (arrowSize * arrowAngle), backViewHeight / 2);
//        arrowPath.lineTo(controlX / 4 + (arrowSize * arrowAngle), backViewHeight / 2 + (arrowZoom * arrowSize));
//        canvas.drawPath(arrowPath, arrowPaint);

        setAlpha(controlX / (SlideBackActivity.screenWidth / 6));
    }
    //判断动画状态
    public boolean getAnimatorEnd(){
        return  animatorEnd;
    }

    public void updateControlPoint(float controlX, int offset) {
        Log.d(TAG, "updateControlPoint: " + controlX);
        this.controlX = controlX;
        this.offset = 1 - offset;
        invalidate();
    }
    //结束动画是否结束
    private boolean animatorEnd=true;

    public void updateControlPointUp(int offset,Boolean overBack){
        if (overBack){
            updateControlPoint(0,offset);
            return;
        }
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(controlX, 0);
        valueAnimator.setDuration(200);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentValue = (float) animation.getAnimatedValue();
                updateControlPoint(currentValue,offset);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter(){
            @Override
            public void onAnimationStart(Animator animation) {
                Log.d(TAG, "updateControlAnimationStart");
                animatorEnd=false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d(TAG, "updateControlAnimationStartEnd");
                animatorEnd=true;

            }
        });
        valueAnimator.start();
    }

    public float dp2px(final float dpValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return dpValue * scale + 0.5f;
    }
}
