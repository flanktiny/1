package com.example.zch_pc.mybaby;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class FirstActivity extends AppCompatActivity {

    //list_view显示
    private ListView mListView;
    private ArrayList<ListItem> mlist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        //分享按钮
        Button first_share = (Button) findViewById(R.id.first_share);
        first_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_share = new Intent();
                intent_share.setClass(FirstActivity.this,ShareActivity.class);
                FirstActivity.this.startActivity(intent_share);
            }
        });


        //删除按钮
        Button delete_1 = (Button) findViewById(R.id.delete_1);
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.relative_1);
        delete_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                relativeLayout.setVisibility(View.GONE);
            }
        });

        //list_view显示
        mListView = (ListView) findViewById(R.id.list_1);
        Resources res = this.getResources();
        mlist = new ArrayList<FirstActivity.ListItem>();

        ListItem item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.rate_1), res.getDrawable(R.drawable.rate_image_1));
        item.setText("心率120/分", "4月1日17：44");
        mlist.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.tem_1), res.getDrawable(R.drawable.tem_image_1));
        item.setText("体温37.0℃", "4月1日17：44");
        mlist.add(item);

        item = new ListItem();
        item.setImage(res.getDrawable(R.drawable.record_1), res.getDrawable(R.drawable.record_image_1));
        item.setText("宝宝连续10天状态健康", "4月1日17：44");
        mlist.add(item);

        MainListViewAdapter adapter =new MainListViewAdapter();
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = null;
                switch (position){
                    case 0:
                        intent = new Intent();
                        intent.setClass(FirstActivity.this,RateActivity.class);
                        break;
                    case 1:
                        intent = new Intent();
                        intent.setClass(FirstActivity.this,TemActivity.class);
                        break;
                    case 2:
                        intent = new Intent();
                        intent.setClass(FirstActivity.this,RecordActivity.class);
                        break;
                    default:
                        break;
                }
                if(intent!=null){
                    FirstActivity.this.startActivity(intent);
                }
            }
        });


    }


    //定义ListView适配器
    class MainListViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mlist.size();
        }

        @Override
        public Object getItem(int position) {
            return mlist.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ListTtemView listTtemView;



            if(convertView==null){
                convertView = LayoutInflater.from(FirstActivity.this).inflate(R.layout.items,null);

                listTtemView=new ListTtemView();
                listTtemView.imageView_1 = (ImageView)convertView.findViewById(R.id.image_icon);
                listTtemView.imageView_2=(ImageView)convertView.findViewById(R.id.list_image);
                listTtemView.textView_1=(TextView)convertView.findViewById(R.id.list_title);
                listTtemView.textView_2=(TextView)convertView.findViewById(R.id.list_time);


                convertView.setTag(listTtemView);
            }else {
                listTtemView = (ListTtemView)convertView.getTag();
            }

            Drawable img_1 =mlist.get(position).getImage_1();
            Drawable img_2 =mlist.get(position).getImage_2();
            String list_title=mlist.get(position).getTitle();
            String list_time=mlist.get(position).getTime();


            listTtemView.imageView_1.setImageDrawable(img_1);
            listTtemView.imageView_2.setImageDrawable(img_2);
            listTtemView.textView_1.setText(list_title);
            listTtemView.textView_2.setText(list_time);

            if(img_1.getConstantState().equals(getResources().getDrawable(R.drawable.rate_1).getConstantState()))
            {
                listTtemView.textView_1.setTextColor(Color.rgb(228,0,79));
            }else if(img_1.getConstantState().equals(getResources().getDrawable(R.drawable.tem_1).getConstantState())){
                listTtemView.textView_1.setTextColor(Color.rgb(78,166,196));
            }else{
                listTtemView.textView_1.setTextColor(Color.rgb(245,215,101));
            }
            return convertView;
        }
    }


    //封装视图组件
    class ListTtemView {
        ImageView imageView_1;
        TextView textView_1;
        TextView textView_2;
        ImageView imageView_2;
    }

    //封装资源类
    class ListItem {
        private Drawable image_1;
        private Drawable image_2;
        private String list_title;
        private String list_time;

        public Drawable getImage_1() {
            return image_1;
        }

        public Drawable getImage_2() {
            return image_2;
        }

        public void setImage(Drawable image_1, Drawable image_2) {
            this.image_1 = image_1;
            this.image_2 = image_2;
        }

        public String getTitle() {
            return list_title;
        }

        public String getTime() {
            return list_time;
        }

        public void setText(String list_title, String list_time) {
            this.list_title = list_title;
            this.list_time = list_time;
        }
    }
}

