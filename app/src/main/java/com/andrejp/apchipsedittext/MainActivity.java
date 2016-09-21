package com.andrejp.apchipsedittext;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringChipsTextInputLayout chipsView = (StringChipsTextInputLayout) findViewById(R.id.chips_view);
        chipsView.setItems(Arrays.asList("chips1", "chips2", "chips3"));
    }
}
