package com.zengyan.yanlibrary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.zengyan.yanlibrary.Adapters.CommonAdapter;
import com.zengyan.yanlibrary.Adapters.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.normal_dialog)
    Button normalBtn;

    @BindView(R.id.show_layout)
    RelativeLayout show_layout;

    @BindView(R.id.framelayout)
    FrameLayout framelayout;

    private List<String> data_list = new ArrayList<>();

    @BindView(R.id.spinner)
    Spinner spinner;

    @BindView(R.id.spinner_pop)
    TextView spinner_pop;

    Animation showAn, hideAn;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        showAn = AnimationUtils.loadAnimation(this, R.anim.zoom_sheet_show);
        hideAn = AnimationUtils.loadAnimation(this, R.anim.zoom_sheet_hide);
        show_layout.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                System.out.println("####Focus" + hasFocus);
                if (!hasFocus) {
                    show_layout.startAnimation(hideAn);
                    show_layout.setVisibility(View.GONE);
                }
            }
        });

        framelayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_layout.startAnimation(hideAn);
                show_layout.setVisibility(View.GONE);
                framelayout.setVisibility(View.GONE);
            }
        });
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show_layout.setVisibility(View.VISIBLE);
                show_layout.startAnimation(showAn);
                framelayout.setVisibility(View.VISIBLE);
                show_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
            }
        });
        for (int i = 0; i < 8; i++) {
            data_list.add("item" + i);
        }

        final SpinnerPopWindow spinnerPopWindow = new SpinnerPopWindow<String>(
                MainActivity.this,
                R.layout.spinner_layout,
                new SpinnerPopWindow.OnClickItem<String>() {
                    @Override
                    public void onClickItem(String s) {
                        spinner_pop.setText(s);
                    }
                },
                new CommonAdapter<String>(MainActivity.this, R.layout.spinner_item, data_list) {
                    @Override
                    protected void convert(ViewHolder holder, String s, int position) {
                        holder.setText(R.id.item_text, s);
                    }
                });

        spinner_pop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinnerPopWindow.showAsDropDown(spinner_pop);
            }
        });

    }


}
