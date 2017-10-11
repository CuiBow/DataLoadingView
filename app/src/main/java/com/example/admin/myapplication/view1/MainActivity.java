package com.example.admin.myapplication.view1;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.admin.myapplication.R;
import com.example.admin.myapplication.view1.adapter.TestAdapter;
import com.example.mylibrary.view.baseView.dataShowLayout;
import com.example.mylibrary.view.type.ErrorType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private dataShowLayout layout;
    private RecyclerView rec;
    private TestAdapter adapter;
    private List<String> musicList=new ArrayList<String>();

    String errors=null;
    String netErrors=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        layout= (dataShowLayout) findViewById(R.id.views);
        rec= (RecyclerView) findViewById(R.id.rec);
        setRecSetting();
        errors="error";
        setAdapter(errors,netErrors);
        setLayoutBtnClick();
    }

    public void setRecSetting()
    {
        LinearLayoutManager line=new LinearLayoutManager(this);
        line.setOrientation(LinearLayoutManager.VERTICAL);
        rec.setLayoutManager(line);
        rec.setItemAnimator(new DefaultItemAnimator());
        //设置分割线
        // rec.addItemDecoration(new DividerItemDecoration(this,R.drawable.divider_bg));
    }
    public void setAdapter(final String error,final String netError)
    {
        layout.setStatus(ErrorType.LOADING);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (error!=null)
                {
                    layout.setStatus(ErrorType.ERROR);
                    errors=null;
                    netErrors="netError";
                }else
                {
                    if (netError!=null)
                    {
                        layout.setStatus(ErrorType.NETERROR);
                        errors=null;
                        netErrors=null;
                    }else
                    {
                        if (musicList.size()>0)
                        {
                            layout.setStatus(ErrorType.SUCCESS);
                            adapter=new TestAdapter(MainActivity.this,R.layout.item,musicList);
                            rec.setAdapter(adapter);
                        }else
                        {
                            for (int i=0;i<20;i++)
                            {
                                musicList.add((i+1)+"");
                            }
                            layout.setStatus(ErrorType.EMPTY);

                        }
                    }
                }


            }
        },3000);


    }

    private void setLayoutBtnClick()
    {
        layout.setBtnRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setAdapter(errors,netErrors);
            }
        });
    }

}
