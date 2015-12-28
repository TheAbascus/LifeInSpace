package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.World;

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

    public abstract void render(float delta, Matrix4 projMatrix);

    public abstract void construct(World world, float pX, float pY);

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
                return new IronMountain(p, maxR, currentR);
            case GOLD_MOUNTAIN:
                return new GoldMountain(p, maxR, currentR);
            case MEADOW:
                return new Meadow(p, maxR, currentR);
            case CORNFIELD:
                return new Cornfield(p, maxR, currentR);
            case CATTLE_MEADOW:
                return new CattleMeadow(p, maxR, currentR);
            case DESERT:
                return new Desert(p, maxR, currentR);
        }
        return new Wastelands(p);
    }


    public enum Type
    {
        WASTELANDS,
        FOREST,
        IRON_MOUNTAIN,
        GOLD_MOUNTAIN,
        MEADOW,
        CORNFIELD,
        CATTLE_MEADOW,
        DESERT,
        PLASMA_SPRING,
        LAKE
    }
}
