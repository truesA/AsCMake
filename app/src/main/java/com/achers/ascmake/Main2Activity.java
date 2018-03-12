package com.achers.ascmake;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnTouchListener {

    private LinearLayout linearLayout;
    private GestureDetector mGestureDetector;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        linearLayout=findViewById(R.id.ll);
        linearLayout.setOnTouchListener(this);

        mGestureDetector = new GestureDetector(this, new MyOnGestureListener());
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        getActionName(event.getAction());
        mGestureDetector.onTouchEvent(event);
                    // 一定要返回true，不然获取不到完整的事件
                     return true;
    }

    private String getActionName(int action) {
                String name = "";
             switch (action) {
                     case MotionEvent.ACTION_DOWN: {
                       name = "ACTION_DOWN";
                       break;
                           }
                        case MotionEvent.ACTION_MOVE: {
                               name = "ACTION_MOVE";
                               break;
                          }
                     case MotionEvent.ACTION_UP: {
                                 name = "ACTION_UP";
                               break;
                          }
                      default:
                           break;
                 }
        Log.i(getClass().getName(), "name-----"+name );
        return name;
            }


    class MyOnGestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
          public boolean onSingleTapUp(MotionEvent e) {
                       Log.i(getClass().getName(), "onSingleTapUp-----" + getActionName(e.getAction()));
                       return false;
                   }

        @Override
         public void onLongPress(MotionEvent e) {
                         Log.i(getClass().getName(), "onLongPress-----" + getActionName(e.getAction()));
                     }
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE
                       && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                   // Fling left
                    Toast.makeText(Main2Activity.this, "Fling Left", Toast.LENGTH_SHORT).show();
              } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE
                      && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                     // Fling right
                     Toast.makeText(Main2Activity.this, "Fling Right", Toast.LENGTH_SHORT).show();
                    }
            return true;
        }
    }
    private static final int FLING_MIN_DISTANCE = 20;//mListView  滑动最小距离
    private static final int FLING_MIN_VELOCITY = 200;//mListView 滑动最大速度

}
