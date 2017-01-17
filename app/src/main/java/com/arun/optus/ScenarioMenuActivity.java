package com.arun.optus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by root on 16/1/17.
 */

public class ScenarioMenuActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btn_scenario1,btn_scenario2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenario_menu);
        btn_scenario1=(Button)findViewById(R.id.btn_scenario1);
        btn_scenario2=(Button)findViewById(R.id.btn_scenario2);
        btn_scenario1.setOnClickListener(this);
        btn_scenario2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent transitionIntent;
        switch (v.getId()){
            case R.id.btn_scenario1:
                Log.d("test","senario  1");
                 transitionIntent= new Intent(this,WidgetsActivity.class);
                startActivity(transitionIntent);
                break;
            case R.id.btn_scenario2:
                Log.d("test","senario  2");
                 transitionIntent= new Intent(this,MapDetailsActivity.class);
                startActivity(transitionIntent);
                break;
        }
    }
}
