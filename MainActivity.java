package com.example.myapplication;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

import java.util.Calendar;

import java.util.Date;

import java.util.List;

import java.util.Locale;



import android.app.Activity;

import android.content.Context;

import android.os.Bundle;

import android.view.LayoutInflater;

import android.view.View;

import android.view.View.OnClickListener;

import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;

import android.widget.GridView;

import android.widget.TextView;



public class MainActivity extends Activity {

    private TextView title;
    private GridAdapter gridAdapter;
    private ArrayList<String> dayList;
    private GridView gridView;
    private Calendar mCal;
    private Calendar sCal;


     @Override

     protected void onCreate(Bundle savedInstanceState) {

     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);

     title = (TextView)findViewById(R.id.title);
     mCal = Calendar.getInstance();

     //현재 날짜 텍스트뷰에 뿌려줌
     title.setText(mCal.get(Calendar.YEAR) + "/" +mCal.get(Calendar.MONTH));

     dayList = new ArrayList<String>();


     //이번달 1일 무슨요일인지 판단 sCal.set(Year,Month,Day)
     sCal=Calendar.getInstance();
     sCal.set(mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH) - 1, 1);
     int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

     //1일 - 요일 매칭 시키기 위해 공백 add
     for (int i = 1; i < dayNum; i++) {
     dayList.add("");

     }

     //setCalendarDate(mCal.get(Calendar.MONTH) + 1);
     sCal.set(Calendar.MONTH, mCal.get(Calendar.MONTH) - 1);

     for (int i = 0; i < sCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
             dayList.add("" + (i + 1));
         }


     gridAdapter = new GridAdapter(getApplicationContext(), dayList);
     gridView.setAdapter(gridAdapter);

     }


    private class GridAdapter extends BaseAdapter {

        private final List<String> list;
        private final LayoutInflater inflater;

        public GridAdapter(Context context, List<String> list) {

            this.list = list;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }


        @Override

        public int getCount() {
            return list.size();

        }

        @Override

        public String getItem(int position) {
            return list.get(position);

        }


        @Override

        public long getItemId(int position) {
            return position;

        }

        @Override

        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;


            if (convertView == null) {

                convertView = inflater.inflate(R.layout.day, parent, false);
                holder = new ViewHolder();

                holder.tvItemGridView = (TextView)convertView.findViewById(R.id.day);

                convertView.setTag(holder);

            } else {
                holder = (ViewHolder)convertView.getTag();

            }

            holder.tvItemGridView.setText("" + getItem(position));


            //해당 날짜 텍스트 컬러,배경 변경

            mCal = Calendar.getInstance();
            //오늘 day 가져옴

            Integer today = mCal.get(Calendar.DAY_OF_MONTH);
            String sToday = String.valueOf(today);

           /* if (sToday.equals(getItem(position))) { //오늘 day 텍스트 컬러 변경
                holder.tvItemGridView.setTextColor(getResources().getColor(R.color.colorPrimaryDark));

            }*/
            return convertView;

        }
    }

    private class ViewHolder {
        TextView tvItemGridView;

    }

}