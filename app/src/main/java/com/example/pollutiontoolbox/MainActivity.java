package com.example.pollutiontoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // adapted from https://developer.android.com/guide/topics/ui/controls/spinner
        Spinner spinner = (Spinner) findViewById(R.id.colorSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.color_options, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void generate(View v){
        try {
            Switch scene = findViewById(R.id.sceneSwitch);
            CheckBox air = findViewById(R.id.checkAir);
            CheckBox water = findViewById(R.id.checkWater);
            CheckBox land = findViewById(R.id.checkLand);
            RadioGroup inhabitants = findViewById(R.id.radioGroup);
            int inhabitantOption;
            if (inhabitants.getCheckedRadioButtonId() != -1){
                String selectedInhabitant = ((RadioButton) findViewById(inhabitants.getCheckedRadioButtonId())).getText().toString();
                inhabitantOption = -1;
                if (selectedInhabitant.equals(getString(R.string.radio_human_text)))
                    inhabitantOption = DisplayActivity.HUMAN;
                else if (selectedInhabitant.equals(getString(R.string.radio_alien_text)))
                    inhabitantOption = DisplayActivity.ALIEN;
                else if (selectedInhabitant.equals(getString(R.string.radio_pig_text)))
                    inhabitantOption = DisplayActivity.PIG;
            }
            else {
                inhabitantOption = -1;
            }
            Spinner colorScene = findViewById(R.id.colorSpinner);
            String colorOfScene = colorScene.getSelectedItem().toString();
            String[] colorsArray = getResources().getStringArray(R.array.color_options);
            int colorOption = -1;
            if (colorOfScene.equals(colorsArray[0]))
                colorOption = 0;
            else if (colorOfScene.equals(colorsArray[1]))
                colorOption = 1;
            else if (colorOfScene.equals(colorsArray[2]))
                colorOption = 2;
            Intent intent = new Intent(this, DisplayActivity.class);
            intent.putExtra(DisplayActivity.INCLUDE_AIR, air.isChecked());
            intent.putExtra(DisplayActivity.INCLUDE_LAND, land.isChecked());
            intent.putExtra(DisplayActivity.INCLUDE_WATER, water.isChecked());
            intent.putExtra(DisplayActivity.SCENE_IS_ON, scene.isChecked());
            intent.putExtra(DisplayActivity.SCENE_SELECT, colorOption);
            intent.putExtra(DisplayActivity.INHABITANT_TYPE, inhabitantOption);
            startActivity(intent);
        }
        catch (Exception e){
            Context context = getApplicationContext();
            String text = getString(R.string.error_message);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
}
