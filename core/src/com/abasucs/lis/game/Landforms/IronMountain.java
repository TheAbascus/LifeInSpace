package com.abasucs.lis.game.Landforms;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Abascus on 26.12.2015.
 */
public class IronMountain extends Landform
{
    boolean hasMine = false;
    Fixture bottomFixture;

    public IronMountain(int p, int maxR, int currentR)
    {
        this(Type.IRONMOUNTAIN, p, maxR, currentR);
    }

    public IronMountain(Type t, int p, int maxR, int currentR)
    {
        super(t, p, maxR, currentR);
    }

    @Override
    public void render(float delta, Matrix4 projMatrix)
    {

    }

    @Override
    public void construct(World world, float pX, float pY)
    {
        float x = (float) (pX + Constants.PLANETRADIUS * Math.cos(Math.toRadians(pos)));
        float y = (float) (pY + Constants.PLANETRADIUS * Math.sin(Math.toRadians(pos)));

        BodyDef bottom = new BodyDef();
        bottom.position.set(new Vector2(x, y));
        Body bottomBody = world.createBody(bottom);
        PolygonShape bottomBox = new PolygonShape();
        bottomBox.setAsBox(Constants.MOUNTAINHEIGHT, Constants.LANDFORMSIZE);
        bottomFixture = bottomBody.createFixture(bottomBox, 0.0f);
        bottomFixture.setUserData(type.toString() + "_BOTTOM");
        bottomBody.setTransform(x, y, (float) Math.toRadians(pos));
        bottomBody.setFixedRotation(true);
        bottomBox.dispose();

        x = (float) (pX + (Constants.PLANETRADIUS + Constants.MOUNTAINHEIGHT * 3 / 2) * Math.cos(Math.toRadians(pos)));
        y = (float) (pY + (Constants.PLANETRADIUS + Constants.MOUNTAINHEIGHT * 3 / 2) * Math.sin(Math.toRadians(pos)));

        BodyDef top = new BodyDef();
        top.position.set(new Vector2(x, y));
        Body topBody = world.createBody(top);
        PolygonShape topBox = new PolygonShape();
        topBox.setAsBox(Constants.MOUNTAINHEIGHT / 2, Constants.LANDFORMSIZE);
        Fixture topFixture = topBody.createFixture(topBox, 0.0f);
        topFixture.setUserData(type.toString() + "_TOP");
        topBody.setTransform(x, y, (float) Math.toRadians(pos));
        topBody.setFixedRotation(true);
        topBox.dispose();


    }

    public void buildMine()
    {
        hasMine = true;
        bottomFixture.setSensor(true);
    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
