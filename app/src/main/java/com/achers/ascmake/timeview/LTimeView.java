package com.achers.ascmake.timeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.achers.ascmake.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Create on 2018/1/3 14:44
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Version: 1.2.3
 */
public class LTimeView extends View {

    /**
     * 下面属性可以设置
     */
    private int mColorTextDef;      // 默认文本的颜色 <!--设置流程线完成颜色-->
    private int mColorTextNo;      // 默认文本的颜色 <!--设置流程线未完成颜色-->
    private int mColorLineTextDef;          // 线段和圆圈颜色  <!--设置流程线断开颜色-->  透明

    private int mColorLineDef;      // 默认文本的颜色 <!--设置流程线完成颜色-->
    private int mColorLineNo;      // 默认文本的颜色 <!--设置流程线未完成颜色-->

    private int mColorSelected;     //选中的字体和圆圈颜色
    private int mLineHight;         //基准线高度
//    private int mCircleHight;       //圆圈的高度（直径）
//    private int mCircleSelStroke;   //被选中圆圈（空心）的粗细
    private int mMarginTop;         //圆圈和文字之间的距离
    private String[] tabNames;      //需要绘制的文字
    private int mTextSize;          //文本的字体大小

    /**
     * 下面需要初始化后计算
     */
    private float splitLengh;       //每一段横线长度
    private int textStartY;         //文本绘制的Y轴坐标
    private List<Rect> mBounds;     //保存文本的量的结果

    private Paint mTextPaint;      //绘制文字的画笔    主色
    private Paint mLinePaint;      //绘制基准线的画笔
    private Paint mNoLinePaint;    //绘制流程中断的基准线的画笔  用灰色
    private Paint mNoNextPaint;    //绘制流程中断后的基准线的画笔  用透明


//    private Paint mCirclePaint;    //绘制基准线上灰色圆圈的画笔
//    private Paint mCircleSelPaint; //绘制被选中位置的蓝色圆圈的画笔

//    private boolean isSliding = false;  //手指是否在拖动
//    private float slidX, slidY;         //手指当前位置（相对于本控件左上角的坐标）
//    private int selectedIndex = 0;      //当前选中序号

    private Bitmap BitmaphanOrderTrue,BitmaphanOrderFalse,BitmaphanOrderFailed,BitmaphanOrderNO,
            BitmapNextTicketTrue,BitmapNextTicketFalse,BitmapNextTicketNO,BitmapOpenTicketTrue,
            BitmapOpenTicketFalse,BitmapOpenTicketNO,BitmapOpenTicketFailed,BitmapOrderTicketTrue,
            BitmapOrderTicketFalse,BitmapOrderTicketNO,BitmapOrderTicketFailed,BitmapSendAwardTrue,
            BitmapSendAwardFalse,BitmapSendAwardNO,BitmapSendAwardFailed,BitmapTicketFailed;
    private int girlBitWidth , girlBitHeight;

    private int status=0;
    private String STATE_INSTANCE="STATE_INSTANCE";
    private String LT_STATUS="LT_STATUS";

    public LTimeView(@NonNull Context context) {
        this(context,null);
    }

    public LTimeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LTimeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context,attrs);
        initPaint();
    }

    /**
     * 数据状态保存
     * @return
     */
    @Nullable
    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle =new Bundle();
        bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
        bundle.putInt(LT_STATUS, status);

        return bundle;

    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
            this.status = bundle.getInt(LT_STATUS);
        } else {
            super.onRestoreInstanceState(state);
        }
    }

    /**
     *
     */
    private void initAttrs(Context context,AttributeSet attrs) {
        TypedArray ta =context.obtainStyledAttributes(attrs, R.styleable.LTimeView);
        mColorTextDef=ta.getColor(R.styleable.LTimeView_color_text_state,getResources().getColor(R.color.green_master));
        mColorTextNo=ta.getColor(R.styleable.LTimeView_color_text_normal,getResources().getColor(R.color.grey_ba));
        mColorLineTextDef=ta.getColor(R.styleable.LTimeView_color_line_text_close, Color.TRANSPARENT);

        mColorLineDef=ta.getColor(R.styleable.LTimeView_color_line_state,getResources().getColor(R.color.green_master));
        mColorLineNo=ta.getColor(R.styleable.LTimeView_color_line_normal,getResources().getColor(R.color.grey_ba));

        mLineHight=ta.getDimensionPixelSize(R.styleable.LTimeView_lintHight,5);

        mMarginTop=ta.getDimensionPixelSize(R.styleable.LTimeView_mMarginTop,50);

        mTextSize=ta.getDimensionPixelSize(R.styleable.LTimeView_android_textSize,45);

        CharSequence[] names = ta.getTextArray(R.styleable.LTimeView_tabNames);
        if(null == names || names.length<=0){
            tabNames = new String[]{"下单","待支付","待出票","待开奖","待派奖"};

           // tabNames = new String[]{"发单","未支付","支付成功 ","订单取消","出票成功","部分出票成功","出票失败","未中奖","已中奖"};

        }else {
            tabNames = new String[names.length];
            for (int i = 0; i < names.length; i++) {
                CharSequence name = names[i];
                tabNames[i] = name.toString();


            }
        }

        ta.recycle();
    }

    /**
     * 初始话画笔
     */
    private void initPaint() {
        mTextPaint =new Paint();
        mTextPaint.setColor(mColorTextDef);
        mTextPaint.setStyle(Paint.Style.FILL);//设置填充
        mTextPaint.setStrokeWidth(mLineHight);//笔宽像素
        mTextPaint.setAntiAlias(true);//锯齿不显示
        mTextPaint.setStrokeWidth(3);
        mTextPaint.setTextSize(mTextSize);

        mLinePaint =new Paint();
        mLinePaint.setColor(mColorLineDef);
        mLinePaint.setStyle(Paint.Style.FILL);//设置填充
        mLinePaint.setStrokeWidth(mLineHight);//笔宽像素
        mLinePaint.setAntiAlias(true);//锯齿不显示

        mNoLinePaint=new Paint();
        mNoLinePaint.setColor(mColorTextNo);
        mNoLinePaint.setStyle(Paint.Style.FILL);//设置填充
        mNoLinePaint.setStrokeWidth(mLineHight);//笔宽像素
        mNoLinePaint.setAntiAlias(true);//锯齿不显示

        mNoNextPaint =new Paint();
        mNoNextPaint.setColor(mColorLineTextDef);
        mNoNextPaint.setStyle(Paint.Style.FILL);//设置填充
        mNoNextPaint.setStrokeWidth(mLineHight);//笔宽像素
        mNoNextPaint.setAntiAlias(true);//锯齿不显示

        measureText();

        measureBitmap();
    }
  //  private Rect bitmapSrcRect,bitmapDesRect;

    /**
     * 测量图片
     */
    private void measureBitmap() {
        BitmaphanOrderTrue = ((BitmapDrawable)getResources().getDrawable(R.drawable.has_orders_true)).getBitmap();
        girlBitWidth = BitmaphanOrderTrue.getWidth();
        girlBitHeight = BitmaphanOrderTrue.getHeight();

//        bitmapSrcRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
//        bitmapDesRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
    }

    /**
     * 测量文字
     *
     */
    private void measureText() {
        mBounds = new ArrayList<>();
        for(String name : tabNames){
            Rect mBound = new Rect();
            mTextPaint.getTextBounds(name, 0, name.length(), mBound);
            mBounds.add(mBound);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);   //获取宽的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); //获取高的模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //获取宽的尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); //获取高的尺寸
        int width;
        int height ;
        if (widthMode == MeasureSpec.EXACTLY) {
            //如果match_parent或者具体的值，直接赋值
            width = widthSize;
        } else {
            //如果是wrap_content，我们要得到控件需要多大的尺寸
            width = widthSize;
        }
        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            float textHeight = mBounds.get(0).height();
            height = (int) (textHeight + girlBitHeight + mMarginTop);
//            Log.v(TAG, "文本的高度:"+textHeight + "控件的高度："+height);
        }
        //保存测量宽度和测量高度
        setMeasuredDimension(width, height);


        measureLines(width,girlBitWidth);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.e("onSizeChanged","onSizeChanged");
        initConstant();


    }

    private  int linwidth;
    /**
     * 画等份线
     * @param width
     * @param girlBitWidth
     */
    private void measureLines(int width, int girlBitWidth) {
        Log.e("tabNames.length",tabNames.length+"");
         linwidth =(width-girlBitWidth*tabNames.length)/(tabNames.length-1);
    }

    private void initConstant(){
        int lineLengh = getWidth() - getPaddingLeft() - getPaddingRight() - girlBitHeight;
        splitLengh = lineLengh/(tabNames.length-1);
        // FontMetrics对象
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        textStartY = getHeight() - (int)fontMetrics.bottom;    //baseLine的位置
//        textStartY = mCircleHight + mMarginTop + getPaddingTop();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画灰色基准线
     //   canvas.drawLine(girlBitHeight/2, girlBitHeight/2, getWidth()-girlBitHeight/2,girlBitHeight/2 , mLinePaint);

//        girlSrcRect = new Rect(0, 0, girlBitWidth, girlBitHeight);
//        girlDesRect = new Rect(0, 0, girlBitWidth, girlBitHeight);

//        for(int i = 0; i<tabNames.length; i++) {
       //     float centerX = girlBitHeight / 2 + (i * splitLengh);
//            //float cx, float cy, float radius, @NonNull Paint paint
//            //画基准线上灰色小圆圈
////            Log.v(TAG, "画圆：X:"+centerX+"  Y:"+centerY);
//            //     canvas.drawCircle(centerX, centerY,mCircleHight/2,mCirclePaint);
//            if(i==0) {
//                canvas.drawBitmap(BitmaphanOrderTrue, bitmapDesRect, bitmapDesRect, null);
//            }else{
//                canvas.drawBitmap(BitmaphanOrderTrue, (girlBitWidth+linwidth)*i, 0, null);
//            }
//        }
        drawBitmapTextHasOrder(canvas,status);

        drawBitmapOrderTicket(canvas,status);

        drawBitmapNextTicket(canvas,status);

        drawBitmapOpenTicket(canvas,status);

        drawBitmapSendAward(canvas,status);

        drawTextTab(canvas);

        drawDefultTimeView(canvas);

        Log.e("ondraw","ondraw");
    }

    /**
     * 画默认订单流程
     * @param canvas
     */
    private void drawDefultTimeView(Canvas canvas) {

    }

    /**
     * 画状态文字
     * @param canvas
     */
    private void drawTextTab(Canvas canvas) {

    }

    /**
     *  0 未支付 1 支付成功 2 订单取消 3 出票成功 4 部分出票成功 5 出票失败 6 未中奖 7 中奖
     */
    /**
     * 画下单的图标和lins 下单未选false  下单成功true  下单失败failed  下单不可选no
     * @param canvas
     * @param status
     */
    private void drawBitmapTextHasOrder(Canvas canvas,int status){

            if (status==2){
                canvas.drawLine(girlBitWidth, girlBitHeight / 2, girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);
            }else{
                canvas.drawLine(girlBitWidth, girlBitHeight / 2, girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);
            }

            canvas.drawBitmap(BitmaphanOrderTrue, 0, 0, null);

            int textWidth=getTextWidth(mTextPaint,"发起");
            canvas.drawText("发起", girlBitWidth/2-textWidth/2, textStartY, mTextPaint);


    }


    /**
     * 画接单的图标和lins  接单未选false 接单成功true 接单失败failed 接单不可选no
     * @param canvas
     * @param status  0 未支付 1 支付成功 2 订单取消 3 出票成功 4 部分出票成功 5 出票失败 6 未中奖 7 中奖
     */

    private void drawBitmapOrderTicket(Canvas canvas,int status){
        //X 起点坐标
        int orderTicket=girlBitWidth+linwidth;

            if (status==0){  //未支付 0
            BitmapOrderTicketFalse=((BitmapDrawable)getResources().getDrawable(R.drawable.orders_tickers_false)).getBitmap();
          //  canvas.drawLine(orderTicket, girlBitHeight / 2, orderTicket * 2, girlBitHeight / 2, mLinePaint);
            canvas.drawBitmap(BitmapOrderTicketFalse, orderTicket, 0, null);

                int textWidth=getTextWidth(mTextPaint,"未支付");
                canvas.drawText("未支付", orderTicket+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);

        }else if (status==2) {//订单取消 3

                BitmapOrderTicketFailed = ((BitmapDrawable) getResources().getDrawable(R.drawable.orders_tickers_failed)).getBitmap();
          //      canvas.drawLine(orderTicket, girlBitHeight / 2, orderTicket * 2, girlBitHeight / 2, mNoLinePaint);
                canvas.drawBitmap(BitmapOrderTicketFailed, orderTicket, 0, null);
                int textWidth = getTextWidth(mTextPaint, "订单取消");
                canvas.drawText("订单取消", orderTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);
//        }else if (status==4||status==5||status==6){

        }
        else{

                BitmapOrderTicketTrue=((BitmapDrawable)getResources().getDrawable(R.drawable.orders_tickers_true)).getBitmap();
                canvas.drawLine(orderTicket,girlBitHeight/2,orderTicket*2,girlBitHeight/2,mLinePaint);
                canvas.drawBitmap(BitmapOrderTicketTrue, orderTicket, 0, null);

                int textWidth=getTextWidth(mTextPaint,"支付成功");
                canvas.drawText("支付成功", orderTicket+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);

            }

    }

    /**
     * 待出票图标和lins 待出票未选false 出票成功true 出票失败failed 出票不可选no  待出票 已出票 出票失败
     * @param canvas
     * @param status 0 未支付 1 支付成功 2 订单取消     3 出票成功 4 部分出票成功 5 出票失败     6 未中奖 7 中奖
     */
    private void drawBitmapNextTicket(Canvas canvas,int status){
        //X 起点坐标
        int nextTicket=(girlBitWidth+linwidth)*2;

            if(status==0||status==1){  //待出票

            BitmapNextTicketFalse=((BitmapDrawable)getResources().getDrawable(R.drawable.next_tickets_false)).getBitmap();

            //canvas.drawLine(nextTicket,girlBitHeight/2,nextTicket+girlBitWidth+linwidth,girlBitHeight/2,mNoLinePaint);

            canvas.drawBitmap(BitmapNextTicketFalse, nextTicket, 0, null);

            if (status==1){
                int textWidth=getTextWidth(mTextPaint,"待出票");
                canvas.drawText("待出票", nextTicket+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);
            }

        }else if (status==5){  //出票失败
            BitmapTicketFailed=((BitmapDrawable)getResources().getDrawable(R.drawable.ticket_failed_failed)).getBitmap();

           // canvas.drawLine(nextTicket,girlBitHeight/2,nextTicket+girlBitWidth+linwidth,girlBitHeight/2,mLinePaint);

            canvas.drawBitmap(BitmapTicketFailed, nextTicket, 0, null);

            int textWidth=getTextWidth(mTextPaint,"出票失败");
            canvas.drawText("出票失败", nextTicket+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);
        }else if (status==3) {

                BitmapNextTicketTrue = ((BitmapDrawable) getResources().getDrawable(R.drawable.next_tickets_true)).getBitmap();

                canvas.drawLine(nextTicket, girlBitHeight / 2, nextTicket + girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);

                canvas.drawBitmap(BitmapNextTicketTrue, nextTicket, 0, null);

                int textWidth = getTextWidth(mTextPaint, "出票成功");
                canvas.drawText("出票成功", nextTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);

            }else if (status==4){ //部分出票成功 5

                BitmapNextTicketTrue = ((BitmapDrawable) getResources().getDrawable(R.drawable.next_tickets_true)).getBitmap();

                canvas.drawLine(nextTicket, girlBitHeight / 2, nextTicket + girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);

                canvas.drawBitmap(BitmapNextTicketTrue, nextTicket, 0, null);

                int textWidth = getTextWidth(mTextPaint, "部分出票成功");
                canvas.drawText("部分出票成功", nextTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);

        }else if (status==6||status==7||status==8){
                BitmapNextTicketTrue = ((BitmapDrawable) getResources().getDrawable(R.drawable.next_tickets_true)).getBitmap();

                canvas.drawLine(nextTicket, girlBitHeight / 2, nextTicket + girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);

                canvas.drawBitmap(BitmapNextTicketTrue, nextTicket, 0, null);

                int textWidth = getTextWidth(mTextPaint, "已出票");
                canvas.drawText("已出票", nextTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);
            }else {
                BitmapNextTicketNO=((BitmapDrawable)getResources().getDrawable(R.drawable.next_tickets_no)).getBitmap();


                  canvas.drawBitmap(BitmapNextTicketNO, nextTicket, 0, null);

            }

    }

    /**
     * 开奖图标和lins  待开奖未选false 已开奖成功true 开奖失败failed 开奖不可选no 待开奖 已开奖 “无”
     * @param canvas
     * @param status  0 未支付 1 支付成功 2 订单取消   3 出票成功 4 部分出票成功 5 出票失败    6 未中奖 7 中奖
     */
    private void drawBitmapOpenTicket(Canvas canvas,int status){
        //X 起点坐标
        int openTicket=(girlBitWidth+linwidth)*3;

        if (status==3||status==4){
            BitmapOrderTicketFalse=((BitmapDrawable)getResources().getDrawable(R.drawable.open_tickers_false)).getBitmap();

          //  canvas.drawLine(openTicket,girlBitHeight/2,openTicket+girlBitWidth+linwidth,girlBitHeight/2,mNoLinePaint);

            canvas.drawBitmap(BitmapOrderTicketFalse, openTicket, 0, null);

            int textWidth = getTextWidth(mTextPaint,"待开奖");

            canvas.drawText("待开奖", openTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);



        }else if (status==6||status==7) {

            BitmapOpenTicketTrue = ((BitmapDrawable) getResources().getDrawable(R.drawable.open_tickers_true)).getBitmap();

            canvas.drawLine(openTicket, girlBitHeight / 2, openTicket + girlBitWidth + linwidth, girlBitHeight / 2, mLinePaint);

            canvas.drawBitmap(BitmapOpenTicketTrue, openTicket, 0, null);

            int textWidth = getTextWidth(mTextPaint, "已开奖");

            canvas.drawText("已开奖", openTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);

        }else if (status==0||status==1){
            BitmapOpenTicketFalse=((BitmapDrawable)getResources().getDrawable(R.drawable.open_tickers_false)).getBitmap();

            canvas.drawBitmap(BitmapOpenTicketFalse, openTicket, 0, null);


        }else {
            BitmapOpenTicketNO=((BitmapDrawable)getResources().getDrawable(R.drawable.open_tickers_no)).getBitmap();

     //       canvas.drawLine(openTicket,girlBitHeight/2,openTicket+girlBitWidth+linwidth,girlBitHeight/2,mNoLinePaint);

            canvas.drawBitmap(BitmapOpenTicketNO, openTicket, 0, null);

//            int textWidth = getTextWidth(mTextPaint,"已开奖");
//
//            canvas.drawText("已开奖", openTicket + girlBitWidth / 2 - textWidth / 2, textStartY, mTextPaint);
        }


    }
    /**
     * 派奖图标和lins  已派奖true  已派奖false  已派奖no  未中奖      已派奖  未中奖 “无”
     * @param canvas
     * @param status 0 未支付 1 支付成功 2 订单取消   3 出票成功 4 部分出票成功 5 出票失败    6 未中奖 7 中奖
     */
    private void drawBitmapSendAward(Canvas canvas,int status){
        //X 起点坐标
        int sendAward=(girlBitWidth+linwidth)*4;
        if (status==0||status==1||status==3||status==4) {
            BitmapSendAwardFalse = ((BitmapDrawable) getResources().getDrawable(R.drawable.send_award_false)).getBitmap();

            canvas.drawBitmap(BitmapSendAwardFalse, sendAward, 0, null);
//        }else if (status==4) {
//
//

        }else  if (status==6||status==7){

            BitmapSendAwardTrue=((BitmapDrawable)getResources().getDrawable(R.drawable.send_award_true)).getBitmap();

            canvas.drawBitmap(BitmapSendAwardTrue, sendAward, 0, null);

            if (status==6){
                int textWidth=getTextWidth(mTextPaint,"未中奖");
                canvas.drawText("未中奖", sendAward+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);
            }else{
                int textWidth=getTextWidth(mTextPaint,"已中奖");
                canvas.drawText("已中奖", sendAward+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);
            }

        }else{
                BitmapSendAwardNO=((BitmapDrawable)getResources().getDrawable(R.drawable.send_award_no)).getBitmap();

            canvas.drawBitmap(BitmapSendAwardNO, sendAward, 0, null);

//            int textWidth=getTextWidth(mTextPaint,tabNames[4]);
//            canvas.drawText(tabNames[4], sendAward+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);
            }

//        BitmapSendAwardTrue=((BitmapDrawable)getResources().getDrawable(R.drawable.send_award_true)).getBitmap();
//
//        canvas.drawBitmap(BitmapNextTicketTrue, sendAward, 0, null);
//
//        int textWidth=getTextWidth(mTextPaint,tabNames[4]);
//        canvas.drawText(tabNames[4], sendAward+girlBitWidth/2-textWidth/2, textStartY, mTextPaint);

    }


    /**
     * 计算文字宽度
     * @param paint
     * @param str
     * @return
     */
    public static int getTextWidth(Paint paint, String str) {
        int iRet = 0;
        if (str != null && str.length() > 0) {
            int len = str.length();
            float[] widths = new float[len];
            paint.getTextWidths(str, widths);
            for (int j = 0; j < len; j++) {
                iRet += (int) Math.ceil(widths[j]);
            }
        }
        return iRet;
    }


    /**
     * 设置状态
     * @param statuss
     */
    public void setStatus(int statuss){
        status=statuss;

        invalidate();
    }
}
