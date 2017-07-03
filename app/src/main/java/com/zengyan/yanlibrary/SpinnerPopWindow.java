package com.zengyan.yanlibrary;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.zengyan.yanlibrary.Adapters.CommonAdapter;
import com.zengyan.yanlibrary.Adapters.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZengYan on 2017/6/1.
 * Email : 810989881@qq.com
 *
 * @decs :
 */

public class SpinnerPopWindow<T> extends PopupWindow {

    private Context mContext;
    private RecyclerView mRecyclerView;
    private List<T> mData ;
    private CommonAdapter<T> commonAdapter;
    private int layoutResId;
    private OnClickItem<T> mOnClickItem;
    private  Animation showAn, hideAn;



    public SpinnerPopWindow(Context context, @LayoutRes int layoutResId, OnClickItem<T> onClickItem, CommonAdapter<T> commonAdapter)
    {
        super(context);
        this.mContext = context;
        this.commonAdapter = commonAdapter;
        this.mData = this.commonAdapter.getDatas();
        this.layoutResId = layoutResId;
        this.mOnClickItem = onClickItem;
//        showAn = AnimationUtils.loadAnimation(mContext, R.anim.zoom_sheet_show);
//        hideAn = AnimationUtils.loadAnimation(mContext, R.anim.zoom_sheet_hide);
        init();
    }

    interface OnClickItem<T>{
        void onClickItem(T t);
    }


    private void init()
    {
        View view = LayoutInflater.from(mContext).inflate(layoutResId, null);
        setContentView(view);
        setWidth(RelativeLayout.LayoutParams.WRAP_CONTENT);
        setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);

        setFocusable(true);
        ColorDrawable dw = new ColorDrawable(0x00);
        setBackgroundDrawable(dw);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        commonAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mOnClickItem.onClickItem(mData.get(position));
                dismiss();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

        mRecyclerView.setAdapter(commonAdapter);
        commonAdapter.notifyDataSetChanged();

    }




}
