package com.zengyan.yanbottomdialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by ZengYan on 2017/5/8.
 * Email : 810989881@qq.com
 */

public abstract class YTopDialog extends Dialog {

    protected View rootView;
    private FrameLayout childView;
    private Context mContext;
    private int mLayoutId;


    public YTopDialog(@NonNull Context context, final int layoutId, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.mLayoutId = layoutId;
    }

    protected abstract void initViews(ViewHolder viewHolder);

    @Override
    protected void onStart() {
        super.onStart();

        ViewTreeObserver observer = childView.getViewTreeObserver();
        ViewTreeObserver.OnGlobalLayoutListener listener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                childView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                Window dialogWindow = getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                int height = childView.getHeight();
                lp.height = height;
                dialogWindow.setAttributes(lp);
                dialogWindow.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP);
            }
        };

        observer.addOnGlobalLayoutListener(listener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rootView = getLayoutInflater().inflate(R.layout.y_rootview_dialog_layout, null);
        childView = (FrameLayout) rootView.findViewById(R.id.content_view);
        View contentView = getLayoutInflater().inflate(mLayoutId, null);
        childView.addView(contentView);
        ViewHolder holder = new ViewHolder(contentView);
        initViews(holder);
        getWindow().setContentView(rootView);
        this.setCanceledOnTouchOutside(false);
    }

}
