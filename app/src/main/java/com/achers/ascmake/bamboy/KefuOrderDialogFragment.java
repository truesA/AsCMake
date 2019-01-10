package com.achers.ascmake.bamboy;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.achers.ascmake.R;
import com.achers.ascmake.bamboy.util.UtilBitmap;
import com.achers.ascmake.bamboy.util.UtilBlurBitmap;

import java.io.ByteArrayOutputStream;

/**
 * author：lhm on 2018/4/12 23:15
 * <p>
 * email：3186834196@qq.com
 */
public class KefuOrderDialogFragment extends DialogFragment {

    private String schema;
    private Context context;
    private Bitmap bitmap;
    private OnUpdateListener mOnUpdateListener;

    public void setOnUpdateListener(OnUpdateListener onUpdateListener) {
        mOnUpdateListener = onUpdateListener;
    }

    public interface OnUpdateListener {
        void onUpdate(Dialog dialog);
    }

    public static KefuOrderDialogFragment newInstance(String schemas, Context context) {
        KefuOrderDialogFragment fragment = new KefuOrderDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("schema", schemas);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        schema = bundle.getString("schema");
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        View view = inflater.inflate(R.layout.order_dialog_share, null);
        ViewPager viewPager =view.findViewById(R.id.viewpage);

//        ImageView pic =view.findViewById(R.id.order_share_iv);
//        Bitmap bitmap = Swap.getInstance().getBitmap();
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
//        byte[] bytes=baos.toByteArray();
//
//        Glide.with(this)
//                .load(bytes)
//                .into(pic);
//
//        pic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Param param=new Param();
//                param.flag="1";
//                Navigation.toActivityWithObject(Path.showSingePic,param);
//            }
//        });

        //LinearLayout sendorder=view.findViewById(R.id.order_share_send);
//        sendorder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (mOnUpdateListener!=null){
//                    mOnUpdateListener.onUpdate(getDialog());
//                }
////                RxBus.getInstance().post(new EventModel(EventId.updateOrderValue,schema));
////                finish();
//            }
//        });

        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        Window win = getDialog().getWindow();
        // 将图片处理成模糊
        win.setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics( dm );

        WindowManager.LayoutParams params = win.getAttributes();
        params.gravity =  Gravity.CENTER;
        // 使用ViewGroup.LayoutParams，以便Dialog 宽度充满整个屏幕
        params.width =  ViewGroup.LayoutParams.WRAP_CONTENT;
        params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        win.setAttributes(params);
    }
}
