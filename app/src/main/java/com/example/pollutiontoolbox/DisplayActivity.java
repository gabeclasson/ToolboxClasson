package com.example.pollutiontoolbox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class DisplayActivity extends AppCompatActivity {
    public static final String INCLUDE_AIR = "includeAir";
    public static final String INCLUDE_LAND = "includeLand";
    public static final String INCLUDE_WATER = "includeWater";
    public static final String INHABITANT_TYPE = "inhabitantType";
    public static final String SCENE_SELECT = "sceneSelect";
    public static final String SCENE_IS_ON = "sceneIsOn";

    public static final int HUMAN = 0;
    public static final int ALIEN = 1;
    public static final int PIG = 2;

    public static final int FULL_COLOR = 0;
    public static final int BLACK_WHITE = 1;
    public static final int GREEN = 2;

    private boolean includeAir;
    private boolean includeLand;
    private boolean includeWater;
    private int inhabitant;
    private int colorScheme;
    private boolean sceneIsOn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Intent intent = getIntent();
        includeAir = intent.getBooleanExtra(INCLUDE_AIR, false);
        includeLand = intent.getBooleanExtra(INCLUDE_LAND, false);
        includeWater = intent.getBooleanExtra(INCLUDE_WATER, false);
        inhabitant = intent.getIntExtra(INHABITANT_TYPE, -1);
        colorScheme = intent.getIntExtra(SCENE_SELECT, -1);
        sceneIsOn = intent.getBooleanExtra(SCENE_IS_ON, false);

        if (!includeAir){
            findViewById(R.id.airPollution).setVisibility(View.INVISIBLE);
        }
        if (!includeLand){
            findViewById(R.id.landPollution).setVisibility(View.INVISIBLE);
        }
        if (!includeWater){
            findViewById(R.id.waterPollution).setVisibility(View.INVISIBLE);
        }
        ImageView inhabitant1 = findViewById(R.id.alien1);
        ImageView inhabitant2 = findViewById(R.id.alien2);
        ImageView inhabitant3 = findViewById(R.id.alien3);
        ImageView inhabitant4 = findViewById(R.id.alien4);
        int imageId = 0;
        if (inhabitant == HUMAN)
            imageId = R.drawable.person;
        else if (inhabitant == ALIEN)
            imageId = R.drawable.alien;
        else if (inhabitant == PIG)
            imageId = R.drawable.pig;
        else
            imageId = R.drawable.person;
        inhabitant1.setImageResource(imageId);
        inhabitant2.setImageResource(imageId);
        inhabitant3.setImageResource(imageId);
        inhabitant4.setImageResource(imageId);
    }

}
