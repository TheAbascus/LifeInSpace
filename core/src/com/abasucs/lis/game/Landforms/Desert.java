package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Abascus on 26.12.2015.
 */
public class Desert extends Landform
{
    public Desert(int p, int maxR, int currentR)
    {
        super(Type.DESERT, p, maxR, currentR);
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
