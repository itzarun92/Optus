package com.arun.optus.Bindings;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.arun.optus.R;

import java.util.ArrayList;

/**
 * Created by root on 27/1/17.
 */

public class WidgetsBinding extends BaseObservable {
    private Context context;


    private String selectedBgColor;
    @Bindable
    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        notifyPropertyChanged(BR.selectedItem);
    }

    private String selectedItem="Nothing";
    public WidgetsBinding(Context context)
    {
        this.context = context;
    }
    @Bindable
    public String getSelectedBgColor() {
    return selectedBgColor;
    }


    private void setSelectedBgColor(String color){
        this.selectedBgColor=color;
        notifyPropertyChanged(BR.selectedBgColor);
    }
    public ArrayList<String> getRecyclerItemList() {
        ArrayList<String> scrollMenuList=new ArrayList<>();
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
        return scrollMenuList;
    }
    public void onClickHandler(View v) {
        switch (v.getId()){
            case R.id.blueBtn: setSelectedBgColor("blue");
                break;
            case R.id.redBtn: setSelectedBgColor("red");
                break;
            case R.id.greenBtn: setSelectedBgColor("green");
                break;
        }
    }
}
