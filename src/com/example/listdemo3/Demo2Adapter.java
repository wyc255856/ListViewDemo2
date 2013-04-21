package com.example.listdemo3;

import java.util.ArrayList;
import java.util.TreeSet;





import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Demo2Adapter extends BaseAdapter {

	 private static final int TYPE_ITEM = 0;
     private static final int TYPE_SEPARATOR = 1;
     private static final int TYPE_MAX_COUNT = TYPE_SEPARATOR + 1;

     private ArrayList mData = new ArrayList();
     private LayoutInflater mInflater;

     private TreeSet mSeparatorsSet = new TreeSet();
    public Demo2Adapter(Context c) {
        mInflater = (LayoutInflater)c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final String item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addSeparatorItem(final String item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position).toString();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        int type = getItemViewType(position);
        System.out.println("getView " + position + " " + convertView + " type = " + type);
        TextView textView = null;
        if (convertView == null) {
        	
            switch (type) {
                case TYPE_ITEM:
                    convertView = mInflater.inflate(R.layout.demo2_item1, null);
                   textView = (TextView)convertView.findViewById(R.id.demo2_textView1);
                    break;
                case TYPE_SEPARATOR:
                    convertView = mInflater.inflate(R.layout.demo2_item2, null);
                  textView = (TextView)convertView.findViewById(R.id.demo2_textView2);
                    break;
            }
            convertView.setTag(textView);
        } else {
            textView = (TextView)convertView.getTag();
        }
      textView.setText(mData.get(position).toString());
        return convertView;
    }

}


