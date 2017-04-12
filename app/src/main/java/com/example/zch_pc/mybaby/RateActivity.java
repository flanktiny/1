package com.example.zch_pc.mybaby;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by ZCH_PC on 2017/4/11.
 */

public class RateActivity extends AppCompatActivity implements View.OnClickListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //返回按钮
        Button rate_back =(Button)findViewById(R.id.back_rate);
        //分享按钮
        Button rate_share = (Button)findViewById(R.id.rate_share);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.back_rate:
                RateActivity.this.finish();
                break;
            case R.id.rate_share:
                Intent intent_share = new Intent();
                intent_share.setClass(RateActivity.this,ShareActivity.class);
                RateActivity.this.startActivity(intent_share);
                break;
            default:
                break;
        }
    }
}
