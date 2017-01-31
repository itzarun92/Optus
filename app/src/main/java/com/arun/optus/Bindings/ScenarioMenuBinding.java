package com.arun.optus.Bindings;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import com.arun.optus.MapDetailsActivity;
import com.arun.optus.R;
import com.arun.optus.WidgetsActivity;

public class ScenarioMenuBinding extends BaseObservable {

    Context context;
    public ScenarioMenuBinding(Context context)
    {
        this.context = context;
    }

    public void onClickHandler(View v) {
        Intent transitionIntent;
        switch (v.getId()){
            case R.id.btn_scenario1:
                transitionIntent= new Intent(context,WidgetsActivity.class);
                context.startActivity(transitionIntent);
                break;
            case R.id.btn_scenario2:
                transitionIntent= new Intent(context,MapDetailsActivity.class);
                context.startActivity(transitionIntent);
                break;
        }
    }
}
