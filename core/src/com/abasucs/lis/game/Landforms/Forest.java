package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Abascus on 25.12.2015.
 */
public class Forest extends Landform
{
    public Forest(int p, int maxR, int currentR)
    {
        super(Type.FOREST, p, maxR, currentR);
    }

    @Override
    public void render(float delta, Camera camera)
    {
//TODO
    }

    @Override
    public boolean onClick()
    {
//TODO
        return false;
    }
}
