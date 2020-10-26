package com.example.ayashome.Fragments;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ayashome.R;

import java.util.ArrayList;
import java.util.List;

import ahmed.easyslider.EasySlider;
import ahmed.easyslider.SliderItem;

public class hostalfragment extends AppCompatActivity {


    private EasySlider easySlider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hostalselect);

        easySlider = easySlider.findViewById(R.id.SliderId);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem("title1",R.drawable.habi));
        sliderItems.add(new SliderItem("title2",R.drawable.habi2camas));
        sliderItems.add(new SliderItem("title3",R.drawable.habi2camas2));
        sliderItems.add(new SliderItem("title4", R.drawable.piscina));
        easySlider.setPages(sliderItems);





    }



}