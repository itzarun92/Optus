package com.arun.optus;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 16/1/17.
 */

public class WidgetsActivity extends AppCompatActivity implements View.OnClickListener{

    private RecyclerView horizontal_recycler_view;
    private ArrayList<String> scrollMenuList;
    private HorizontalAdapter horizontalAdapter;
    private TextView selectedItem;
    private LinearLayout ll_background;
    private Button blueBtn,redBtn,greenBtn;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widgets);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);
        selectedItem=(TextView)findViewById(R.id.selectedItem);
        ll_background=(LinearLayout)findViewById(R.id.ll_background);
        blueBtn=(Button)findViewById(R.id.blueBtn);
        redBtn=(Button)findViewById(R.id.redBtn);
        greenBtn=(Button)findViewById(R.id.greenBtn);
        blueBtn.setOnClickListener(this);
        redBtn.setOnClickListener(this);
        greenBtn.setOnClickListener(this);
        scrollMenuList=new ArrayList<>();
        scrollMenuList.add("Item 1");
        scrollMenuList.add("Item 2");
        scrollMenuList.add("Item 3");
        scrollMenuList.add("Item 4");
        scrollMenuList.add("Item 5");
        scrollMenuList.add("Item 6");
        scrollMenuList.add("Item 7");
        scrollMenuList.add("Item 8");
        scrollMenuList.add("Item 9");
        scrollMenuList.add("Item 10");
        horizontalAdapter=new HorizontalAdapter(scrollMenuList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(WidgetsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.blueBtn: ll_background.setBackgroundColor(Color.BLUE);
                break;
            case R.id.redBtn: ll_background.setBackgroundColor(Color.RED);
                break;
            case R.id.greenBtn: ll_background.setBackgroundColor(Color.GREEN);
                break;
        }
    }

    public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

        private List<String> horizontalList;

        public class MyViewHolder extends RecyclerView.ViewHolder {
            public TextView txtView;

            public MyViewHolder(View view) {
                super(view);
                txtView = (TextView) view.findViewById(R.id.txtView);

            }
        }


        public HorizontalAdapter(List<String> horizontalList) {
            this.horizontalList = horizontalList;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.scroll_item_view, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.txtView.setText(horizontalList.get(position));

            holder.txtView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(WidgetsActivity.this,holder.txtView.getText().toString(),Toast.LENGTH_SHORT).show();
                    selectedItem.setText(holder.txtView.getText().toString());
                }
            });
        }

        @Override
        public int getItemCount() {
            return horizontalList.size();
        }
    }
    private class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Bundle args = new Bundle();
            args.putInt("position", position);

PagerFragment pagerFragment=new PagerFragment();
            pagerFragment.setArguments(args);
            return pagerFragment ;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

}
