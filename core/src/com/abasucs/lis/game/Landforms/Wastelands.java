package com.abasucs.lis.game.Landforms;

/**
 * Created by Abascus on 25.12.2015.
 */
public class Wastelands extends  Landform
{
    public Wastelands(Type t, int p, int maxR, int currentR)
    {
        super(t, p, maxR, currentR);
    }

    @Override
    public void render(float delta)
    {
//TODO
    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
