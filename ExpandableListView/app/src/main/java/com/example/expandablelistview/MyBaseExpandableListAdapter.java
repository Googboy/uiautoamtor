package com.example.expandablelistview;

import android.content.ClipData;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.security.acl.Group;
import java.util.ArrayList;

/**
 * Created by 潘硕 on 2017/11/14.
 */

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {
    private ArrayList<Group> gData;
    private ArrayList<ArrayList<ClipData.Item>> iData;
    private Context mContext;

    public MyBaseExpandableListAdapter(ArrayList<Group> gData, ArrayList<ArrayList<ClipData.Item>> iData,Context mContext){
        this.gData = gData;
        this.iData = iData;
        this.mContext = mContext;
    }
    @Override
    public int getGroupCount() {
        return gData.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return iData.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return gData.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return iData.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        ViewHolderGroup groupHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_group,parent,false);
            groupHolder = new ViewHolderGroup();
            groupHolder.tv_group_name = convertView.findViewById(R.id.tv_group_name);
            convertView.setTag(groupHolder);
        }else {
            groupHolder = (ViewHolderGroup) convertView.getTag();
        }
        groupHolder.tv_group_name.setText(gData.get(groupPosition).getName());
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ViewHolderItem itemHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_exlist_item,parent,false);
            itemHolder = new ViewHolderItem();
            itemHolder.img_icon = convertView.findViewById(R.id.img_icon);
            itemHolder.tv_name = convertView.findViewById(R.id.tv_name);
            convertView.setTag(itemHolder);
        }else {
            itemHolder = (ViewHolderItem) convertView.getTag();
        }
        itemHolder.img_icon.setImageResource(iData.get(groupPosition).get(childPosition).getiId());
        itemHolder.tv_name.setText(iData.get(groupPosition).get(childPosition).getiName());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private class ViewHolderGroup {
        private TextView tv_group_name;
    }

    private class ViewHolderItem {
        private ImageView img_icon;
        private TextView tv_name;
    }
}
