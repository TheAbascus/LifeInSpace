package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Abascus on 25.12.2015.
 */
public class Wastelands extends Landform
{
    public Wastelands(int p)
    {
        super(Type.WASTELANDS, p,0,0);
    }

    @Override
    public void render(float delta, Camera camera)
    {
//TODO
    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
