package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Abascus on 26.12.2015.
 */
public class PlasmaSpring extends Landform
{
    public PlasmaSpring(int p, int maxR, int currentR)
    {
        super(Type.PLASMA_SPRING, p, maxR, currentR);
    }

    @Override
    public void render(float delta, Camera camera)
    {

    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
