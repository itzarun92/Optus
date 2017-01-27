package com.arun.optus;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.arun.optus.Bindings.ScenarioMenuBinding;
import com.arun.optus.databinding.ActivityScenarioMenuBinding;

/**
 * Created by root on 16/1/17.
 */

public class ScenarioMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityScenarioMenuBinding binding = DataBindingUtil.setContentView(this,  R.layout.activity_scenario_menu);
        ScenarioMenuBinding scenarioMenuModel = new ScenarioMenuBinding(ScenarioMenuActivity.this);
        binding.setPresenter(scenarioMenuModel);

    }



}
