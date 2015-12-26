package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;

/**
 * Created by Abascus on 26.12.2015.
 */
public class IronMontain extends Landform
{
    public IronMontain(int p, int maxR, int currentR)
    {
        super(Type.IRON_MOUNTAIN, p, maxR, currentR);
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
