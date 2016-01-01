package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.math.Matrix4;

/**
 * Created by Abascus on 01.01.2016.
 */
public class Lake extends Landform
{
    public Lake(int p, int maxR, int currentR)
    {
        super(Type.LAKE, p, maxR, currentR);
    }

    @Override
    public void render(float delta, Matrix4 projMatrix)
    {

    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
