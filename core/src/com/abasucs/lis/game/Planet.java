package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.abasucs.lis.game.Landforms.Landform;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

import java.util.Arrays;

/**
 * Created by Abasucs on 24.12.2015.
 */
public class Planet
{
    public float x, y, rocketPos;
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
    }


    public void render(float delta, Camera camera)
    {

        if (landforms != null)
        {
            for (int i = 0; i < landforms.length; i++)
            {
                landforms[i].render(delta, camera);
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
