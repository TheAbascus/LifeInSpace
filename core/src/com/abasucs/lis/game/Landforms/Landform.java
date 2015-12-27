package com.abasucs.lis.game.Landforms;

import com.badlogic.gdx.graphics.Camera;
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
                return new IronMontain(p, maxR, currentR);
            case CATTLE_MEADOW:
                return new CattleMeadow(p, maxR, currentR);
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
        CATTLE_MEADOW,
        DESERT,
        MEADOW,
        CORNFIELD,
        PLASMA_SPRING
    }
}
