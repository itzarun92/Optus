package com.arun.optus;

import android.databinding.DataBindingUtil;
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
import android.widget.TextView;

import com.arun.optus.Bindings.WidgetsBinding;
import com.arun.optus.databinding.ActivityWidgetsBinding;

import java.util.List;

/**
 * Created by root on 16/1/17.
 */

public class WidgetsActivity extends AppCompatActivity{

    private RecyclerView horizontal_recycler_view;
    private HorizontalAdapter horizontalAdapter;
    private static final int NUM_PAGES = 5;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private WidgetsBinding widgetsModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWidgetsBinding binding = DataBindingUtil.setContentView(this,  R.layout.activity_widgets);
        widgetsModel = new WidgetsBinding(WidgetsActivity.this);
        binding.setPresenter(widgetsModel);
        horizontal_recycler_view= (RecyclerView) findViewById(R.id.horizontal_recycler_view);


        horizontalAdapter=new HorizontalAdapter(widgetsModel.getRecyclerItemList());
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(WidgetsActivity.this, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);
        horizontal_recycler_view.setAdapter(horizontalAdapter);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
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
                    widgetsModel.setSelectedItem(holder.txtView.getText().toString());
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
