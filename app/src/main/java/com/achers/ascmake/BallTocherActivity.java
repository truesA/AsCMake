package com.achers.ascmake;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class BallTocherActivity extends AppCompatActivity implements View.OnTouchListener {

    private static final String TAG = "BallTocherActivity";
    private TextView ball;
    private LinearLayout ball_ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball_tocher);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        init();
    }

    private void init() {
        ball=findViewById(R.id.tv_ball);
        ball_ll=findViewById(R.id.ll_ball);

       ball_ll.setOnTouchListener(this);
    }

    private Handler handler =new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
           switch (msg.what){
               case 1:
//                   ball.setOnClickListener(new View.OnClickListener() {
//                       @Override
//                       public void onClick(View view) {
                   Toast.makeText(BallTocherActivity.this,"ball",Toast.LENGTH_SHORT).show();
               //        }
              //     });
                   break;
           }
            return false;
        }
    });

    @Override
    public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d(TAG, "onTouch: ACTION_DOWN");
                       Message message=new Message();
                       message.what=1;
                       handler.sendMessage(message);

                        break;


                    /**
                     * layout(l,t,r,b) l Left position, relative to parent t Top position,
                     * relative to parent r Right position, relative to parent b Bottom
                     * position, relative to parent
                     * */
                    case MotionEvent.ACTION_MOVE:

                        break;
                    case MotionEvent.ACTION_UP:

                        Log.d(TAG, "onTouch: ACTION_UP");
                        break;
                }
                return true;

    }
}
