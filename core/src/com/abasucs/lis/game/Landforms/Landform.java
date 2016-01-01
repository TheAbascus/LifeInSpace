package com.abasucs.lis.game.Landforms;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
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

    public void construct(World world, float pX, float pY)
    {
        float x = (float) (pX + (Constants.PLANETRADIUS+Constants.LANDFORMHEIGHT/2) * Math.cos(Math.toRadians(pos)));
        float y = (float) (pY + (Constants.PLANETRADIUS+Constants.LANDFORMHEIGHT/2) * Math.sin(Math.toRadians(pos)));

        BodyDef bottom = new BodyDef();
        bottom.position.set(new Vector2(x, y));
        Body bottomBody = world.createBody(bottom);
        PolygonShape bottomBox = new PolygonShape();
        bottomBox.setAsBox(Constants.LANDFORMHEIGHT, Constants.LANDFORMSIZE);
        Fixture fixture = bottomBody.createFixture(bottomBox, 0.0f);
        fixture.setSensor(true);
        fixture.setUserData(type.toString());
        bottomBody.setTransform(x, y, (float) Math.toRadians(pos));
        bottomBody.setFixedRotation(true);
        bottomBox.dispose();
    }

    public abstract boolean onClick();

    public static Landform getLandform(String t, int p, int maxR, int currentR)
    {
        switch (Type.valueOf(t))
        {
            case WASTELANDS:
                return new Wastelands(p);
            case FOREST:
                return new Forest(p, maxR, currentR);
            case IRONMOUNTAIN:
                return new IronMountain(p, maxR, currentR);
            case GOLDMOUNTAIN:
                return new GoldMountain(p, maxR, currentR);
            case MEADOW:
                return new Meadow(p, maxR, currentR);
            case CORNFIELD:
                return new Cornfield(p, maxR, currentR);
            case CATTLEMEADOW:
                return new CattleMeadow(p, maxR, currentR);
            case DESERT:
                return new Desert(p, maxR, currentR);
            case PLASMASPRING:
                return new PlasmaSpring(p, maxR, currentR);
            case LAKE:
                return new Lake(p, maxR, currentR);
        }
        return new Wastelands(p);
    }


    public enum Type
    {
        WASTELANDS,
        FOREST,
        IRONMOUNTAIN,
        GOLDMOUNTAIN,
        MEADOW,
        CORNFIELD,
        CATTLEMEADOW,
        DESERT,
        PLASMASPRING,
        LAKE
    }
}
