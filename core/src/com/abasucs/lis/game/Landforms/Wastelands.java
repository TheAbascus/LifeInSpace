package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;

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
    public void render(float delta, Matrix4 projMatrix)
    {
//TODO
    }

    @Override
    public void construct(World world, float pX, float pY)
    {

    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
