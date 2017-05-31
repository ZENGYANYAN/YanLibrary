package com.zengyan.yanlibrary;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.zengyan.yanbottomdialog.ViewHolder;
import com.zengyan.yanbottomdialog.YBottomDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.string.no;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.normal_dialog)
    Button normalBtn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        normalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YBottomDialog dialog=new YBottomDialog(MainActivity.this,R.layout.normal_dialog) {
                    @Override
                    protected void initViews(ViewHolder viewHolder) {
                        viewHolder.setText(R.id.text, "aaaaaaaa");
                    }
                };
                dialog.show();
            }
        });


    }
}
