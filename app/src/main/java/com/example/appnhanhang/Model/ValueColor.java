package com.example.appnhanhang.Model;

import java.util.ArrayList;

public class ValueColor {

    ArrayList<color> listColor;

    public ValueColor()
    {
        listColor = new ArrayList<>();
        color color1 = new color(53, 122, 188);
        color color2 = new color(191, 129, 233);
        color color3 = new color(24, 61, 32);
        color color4 = new color(148 , 222, 163);
        color color5 = new color(224, 67, 54);
        color color6 = new color(225, 235, 59);
        color color7 = new color(76, 175, 80);
        color color8 = new color(0, 188, 212);
        color color9 = new color(205, 220, 57);
        color color10 = new color(255, 152, 0);

        listColor.add(color1);
        listColor.add(color2);
        listColor.add(color3);
        listColor.add(color4);
        listColor.add(color5);
        listColor.add(color6);
        listColor.add(color7);
        listColor.add(color8);
        listColor.add(color9);
        listColor.add(color10);
    }
    public ValueColor(ArrayList<color> listColor) {
        this.listColor = listColor;
    }

    public color  getListColor(int positin) {
        return listColor.get(positin);
    }
}
