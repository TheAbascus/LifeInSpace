package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.abasucs.lis.game.Landforms.Landform;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;

/**
 * Created by Abasucs on 24.12.2015.
 */
public class Planet
{
    public float x = 0;
    public float y = 0;
    public float rocketPos = 0;
    public Landform[] landforms;

    public void construct(World world)
    {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(x, y));

        Body groundBody = world.createBody(groundBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(Constants.PLANETRADIUS);
        groundBody.createFixture(circle, 0.0f);
        circle.dispose();

        float rX = (float) (x + (Constants.PLANETRADIUS+Constants.ROCKETHEIGHT/2) * Math.cos(Math.toRadians(rocketPos)));
        float rY = (float) (y + (Constants.PLANETRADIUS+Constants.ROCKETHEIGHT/2) * Math.sin(Math.toRadians(rocketPos)));


        BodyDef rocket = new BodyDef();
        rocket.position.set(new Vector2(rX, rY));
        Body rocketBody = world.createBody(rocket);
        PolygonShape rocketBox = new PolygonShape();
        rocketBox.setAsBox(Constants.ROCKETHEIGHT, Constants.ROCKETSIZE);
        rocketBody.createFixture(rocketBox, 0.0f);
        rocketBody.setTransform(rX, rY, (float) Math.toRadians(rocketPos));
        rocketBody.setFixedRotation(true);
        rocketBox.dispose();

        for (int i = 0; i < landforms.length; i++)
        {
            landforms[i].construct(world, x, y);
        }

    }

    public void render(float delta, Matrix4 projMatrix)
    {
        if (landforms != null)
        {
            for (int i = 0; i < landforms.length; i++)
            {
                landforms[i].render(delta, projMatrix);
            }
        }
    }

    public void addLandform(Landform p)
    {
        if (landforms == null)
        {
            landforms = new Landform[1];
        }
        else
        {
            landforms = Arrays.copyOf(landforms, landforms.length + 1);
        }
        landforms[landforms.length - 1] = p;
    }
}
