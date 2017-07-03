package com.zengyan.yanlibrary;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zengyan.yanlibrary.Adapters.CommonAdapter;

/**
 * Created by ZengYan on 2017/6/1.
 * Email : 810989881@qq.com
 *
 * @decs :
 */

public class YSpinner<T> extends RelativeLayout{

    private Context mContext;
    private View mView;
    private TextView mSpinnerText;
    private SpinnerPopWindow<T> mSpinnerPopWindow;
    private CommonAdapter<T> mCommonAdapter;
    private SpinnerPopWindow.OnClickItem<T> mOnClickItem;

    public YSpinner(Context context) {
        super(context);
    }

    public void setListener(CommonAdapter<T> commonAdapter, SpinnerPopWindow.OnClickItem<T> onClickItem) {
        this.mCommonAdapter = commonAdapter;
        this.mOnClickItem = onClickItem;
        mSpinnerPopWindow = new SpinnerPopWindow<T>(mContext, R.layout.spinner_layout, mOnClickItem, mCommonAdapter);
    }

    public YSpinner(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        mView=LayoutInflater.from(mContext).inflate(R.layout.spinner_select_item, this);
        mSpinnerText = (TextView) mView.findViewById(R.id.spinner_text);
        mSpinnerText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSpinnerPopWindow.showAsDropDown(mSpinnerText);
            }
        });
    }

}
