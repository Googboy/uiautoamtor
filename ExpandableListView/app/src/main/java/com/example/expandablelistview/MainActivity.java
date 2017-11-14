package com.example.expandablelistview;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.security.acl.Group;
import java.util.ArrayList;

public class MainActivity extends Activity {
    private ArrayList<Group> gData = null;
    private ArrayList<ArrayList<ClipData.Item>> iData = null;
    private ArrayList<ClipData.Item> lData = null;
    private Context mContext;
    private ExpandableListView exlist_lol;
    private MyBaseExpandableListAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = MainActivity.this;
        exlist_lol = findViewById(R.id.exlist_lol);

        gData = new ArrayList<Group>();
        iData = new ArrayList<ArrayList<ClipData.Item>>();
        //gData.add(Group("AD"))
        lData = new ArrayList<ClipData.Item>();

        lData.add(new ClipData.Item(R.drawable.timo,"timo"));
        iData.add(lData);

        myAdapter = new MyBaseExpandableListAdapter(gData,iData,mContext);
        exlist_lol.setAdapter(myAdapter);

        exlist_lol.setOnChildClickListener(new ExpandableListView.OnChildClickListener(){

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(mContext,"你点击了:"+iData.get(groupPosition).get(childPosition).getiName(),Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
