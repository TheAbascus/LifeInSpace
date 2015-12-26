package com.abasucs.lis.game;

import com.abasucs.lis.game.Landforms.Landform;

import java.util.Arrays;

/**
 * Created by Abasucs on 24.12.2015.
 */
public class Planet
{
    public float x, y, rocketPos;
    public Landform[] landforms;

    public void addLandform(Landform p)
    {
        if(landforms == null)
        {
            landforms = new Landform[1];
        }
        else
        {
            landforms = Arrays.copyOf(landforms, landforms.length + 1);
        }
        landforms[landforms.length-1] = p;
    }
}
