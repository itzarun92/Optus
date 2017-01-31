package com.arun.optus.Bindings;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.android.databinding.library.baseAdapters.BR;
import com.arun.optus.R;


public class WidgetsBinding extends BaseObservable {
    private String selectedBgColor;
    @Bindable
    public String getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(String selectedItem) {
        this.selectedItem = selectedItem;
        notifyPropertyChanged(BR.selectedItem);
    }

    private String selectedItem;
    public WidgetsBinding(Context context)
    {
        setSelectedItem(context.getResources().getString(R.string.initial_selected_item_msg));
    }
    @Bindable
    public String getSelectedBgColor() {
    return selectedBgColor;
    }


    private void setSelectedBgColor(String color){
        this.selectedBgColor=color;
        notifyPropertyChanged(BR.selectedBgColor);
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
