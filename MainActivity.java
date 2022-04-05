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

import android.widget.BaseAdapter;

import android.widget.GridView;

import android.widget.TextView;



public class MainActivity extends Activity {



    private TextView tvDate;

    private GridAdapter gridAdapter;

    private ArrayList<String> dayList;

    private GridView gridView;

    private Calendar mCal;



     @Override

     protected void onCreate(Bundle savedInstanceState) {

     super.onCreate(savedInstanceState);
     setContentView(R.layout.activity_main);


     tvDate = (TextView)findViewById(R.id.title);
     gridView = (GridView)findViewById(R.id.siso);


     // 오늘에 날짜를 세팅 해준다.

     long now = System.currentTimeMillis();
     final Date date = new Date(now);

     //연,월,일을 따로 저장

     final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
     final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
     final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);


     //현재 날짜 텍스트뷰에 뿌려줌

     tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));


     //gridview 요일 표시

     dayList = new ArrayList<String>();
     mCal = Calendar.getInstance();


     //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)

     mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
     int dayNum = mCal.get(Calendar.DAY_OF_WEEK);

     //1일 - 요일 매칭 시키기 위해 공백 add

     for (int i = 1; i < dayNum; i++) {
     dayList.add("");

     }
     setCalendarDate(mCal.get(Calendar.MONTH) + 1);


     gridAdapter = new GridAdapter(getApplicationContext(), dayList);
     gridView.setAdapter(gridAdapter);


     }



    private void setCalendarDate(int month) {

    mCal.set(Calendar.MONTH, month - 1);

    for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
    dayList.add("" + (i + 1));

    }

    }


     /* 그리드뷰 어댑터*/


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

                convertView = inflater.inflate(R.layout.activity_main, parent, false);
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