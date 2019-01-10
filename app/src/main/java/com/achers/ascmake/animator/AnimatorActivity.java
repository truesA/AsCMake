package com.achers.ascmake.animator;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.achers.ascmake.R;

/**
 * ValueAnimator 类是先改变值，然后 手动赋值 给对象的属性从而实现动画；是 间接 对对象属性进行操作；
 ObjectAnimator 类是先改变值，然后 自动赋值 给对象的属性从而实现动画；是 直接 对对象属性进行操作；
 */
public class AnimatorActivity extends AppCompatActivity implements View.OnClickListener {

    private Button button;
    private TextView button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        button = findViewById(R.id.bt_change);
        button1 =findViewById(R.id.bt_change1);

        button.setOnClickListener(this);
        button1.setOnClickListener(v -> {

            // 创建动画作用对象：此处以Button为例
           // 透明度
//            ObjectAnimator animator = ObjectAnimator.ofFloat(button1, "alpha", 1f, 0f, 1f);
//            // 表示的是:
//            // 动画作用对象是mButton
//            // 动画作用的对象的属性是透明度alpha
//            // 动画效果是:常规 - 全透明 - 常规
//            animator.setDuration(5000);
//            animator.start();

            //旋转
//            ObjectAnimator animator =ObjectAnimator.ofFloat(button1,"rotation",0f,360f);
//            animator.setDuration(3000);
//            animator.start();


            //平移
//            float curTranslationX = button1.getTranslationX();
//            // 获得当前按钮的位置
//            ObjectAnimator animator = ObjectAnimator.ofFloat(button1, "translationX", curTranslationX, 300,curTranslationX);
//
//
//            // 表示的是:
//            // 动画作用对象是mButton
//            // 动画作用的对象的属性是X轴平移（在Y轴上平移同理，采用属性"translationY"
//            // 动画效果是:从当前位置平移到 x=1500 再平移到初始位置
//            animator.setDuration(5000);
//            animator.start();


            ObjectAnimator animator = ObjectAnimator.ofFloat(button1, "scaleX", 1f, 3f, 1f);
            // 表示的是:
            // 动画作用对象是mButton
            // 动画作用的对象的属性是X轴缩放
            // 动画效果是:放大到3倍,再缩小到初始大小
            animator.setDuration(5000);
            animator.start();



        });
    }

    @Override
    public void onClick(View v) {
        // 步骤1：设置属性数值的初始值 & 结束值
        ValueAnimator valueAnimator = ValueAnimator.ofInt(
                button.getLayoutParams().width, 500);
        // 初始值 = 当前按钮的宽度，此处在xml文件中设置为150
        // 结束值 = 500
        // ValueAnimator.ofInt()内置了整型估值器,直接采用默认的.不需要设置
        // 即默认设置了如何从初始值150 过渡到 结束值500

        // 步骤2：设置动画的播放各种属性
        valueAnimator.setDuration(2000);
        // 设置动画运行时长:2s
        // 步骤3：将属性数值手动赋值给对象的属性:此处是将 值 赋给 按钮的宽度
        // 设置更新监听器：即数值每次变化更新都会调用该方法
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // 获得每次变化后的属性值
                int currentValue= (int) animation.getAnimatedValue();

                // 输出每次变化后的属性值进行查看
                System.out.println(currentValue);



                // 每次值变化时，将值手动赋值给对象的属性
                // 即将每次变化后的值 赋 给按钮的宽度，这样就实现了按钮宽度属性的动态变化
                button.getLayoutParams().width=currentValue;
                // 步骤4：刷新视图，即重新绘制，从而实现动画效果
                button.requestLayout();
            }
        });
        // 启动动画
        valueAnimator.start();

    }

  //  ValueAnimator.ofObject（） 将初始值 以对象的形式 过渡到结束值
//    // 创建初始动画时的对象  & 结束动画时的对象
//    myObject object1 = new myObject();
//    myObject object2 = new myObject();
//
//    ValueAnimator anim = ValueAnimator.ofObject(new myObjectEvaluator(), object1, object2);
//    // 创建动画对象 & 设置参数
//    // 参数说明
//    // 参数1：自定义的估值器对象（TypeEvaluator 类型参数） - 下面会详细介绍
//    // 参数2：初始动画的对象
//    // 参数3：结束动画的对象
//    anim.setDuration(5000);
//    anim.start();


/**
 *     //估值器（TypeEvaluator） 介绍
 //     设置动画 如何从初始值 过渡到 结束值 的逻辑
 //    插值器（Interpolator）决定 值 的变化模式（匀速、加速blabla）
 //    估值器（TypeEvaluator）决定 值 的具体变化数值
 */

//自定义估值器（TypeEvaluator）来告知系统如何进行从 初始对象 过渡到 结束对象的逻辑

// 实现TypeEvaluator接口
//public class ObjectEvaluator implements TypeEvaluator{
//
//    // 复写evaluate（）
//// 在evaluate（）里写入对象动画过渡的逻辑
//    @Override
//    public Object evaluate(float fraction, Object startValue, Object endValue) {
//        // 参数说明
//        // fraction：表示动画完成度（根据它来计算当前动画的值）
//        // startValue、endValue：动画的初始值和结束值
//
//        ... // 写入对象动画过渡的逻辑
//
//        return value;
//        // 返回对象动画过渡的逻辑计算后的值
//    }

    /**
     * ObjectAnimator
     */
//ObjectAnimator anim = ObjectAnimator.ofFloat(Object object, String property, float ....values);

// ofFloat()作用有两个
// 1. 创建动画实例
// 2. 参数设置：参数说明如下
// Object object：需要操作的对象
// String property：需要操作的对象的属性
// float ....values：动画初始值 & 结束值（不固定长度）
// 若是两个参数a,b，则动画效果则是从属性的a值到b值
// 若是三个参数a,b,c，则则动画效果则是从属性的a值到b值再到c值
// 以此类推
// 至于如何从初始值 过渡到 结束值，同样是由估值器决定，此处ObjectAnimator.ofFloat（）是有系统内置的浮点型估值器FloatEvaluator，同ValueAnimator讲解
//
//anim.setDuration(500);
//    // 设置动画运行的时长
//
//        anim.setStartDelay(500);
//    // 设置动画延迟播放时间
//
//        anim.setRepeatCount(0);
//    // 设置动画重复播放次数 = 重放次数+1
//    // 动画播放次数 = infinite时,动画无限重复
//
//        anim.setRepeatMode(ValueAnimator.RESTART);
//    // 设置重复播放动画模式
//    // ValueAnimator.RESTART(默认):正序重放
//    // ValueAnimator.REVERSE:倒序回放
//
//animator.start();
//// 启动动画



}
