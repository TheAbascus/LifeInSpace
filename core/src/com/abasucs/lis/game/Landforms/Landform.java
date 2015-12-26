package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;

public abstract class Landform
{
    public Type type;
    public boolean hasWorker = false;
    public int pos, size, maxRes, currentRes;


    public Landform(Type t, int p, int maxR, int currentR)
    {
        type = t;
        pos = p % 360;
        maxRes = maxR;
        currentRes = currentR;
    }

    public abstract void render(float delta, Camera camera);

    public abstract boolean onClick();

    public static Landform getLandform(String t, int p, int maxR, int currentR)
    {
        switch (Type.valueOf(t))
        {
            case WASTELANDS:
                return new Wastelands(p);
            case FOREST:
                return new Forest(p, maxR, currentR);
            case IRON_MOUNTAIN:
                return new IronMontain(p, maxR, currentR);
            //TODO add others


        }
        return new Wastelands(p);
    }


    public enum Type
    {
        WASTELANDS,
        FOREST,
        IRON_MOUNTAIN,
        GOLD_MOUNTAIN,
        DESERT,
        MEADOW,
        CORNFIELD,
        CATTLE_MEADOW,
        PLASMA_SPRING
    }
}
