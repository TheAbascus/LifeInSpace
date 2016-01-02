package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.abasucs.lis.game.Landforms.Landform;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
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
    public int planetID;
    Body planetBody;
    Body rocketBody;
    Texture tex;


    public Planet()
    {
        tex = new Texture("inGame/world_mockup.png");
    }

    public void construct(World world)
    {
        BodyDef groundBodyDef = new BodyDef();
        groundBodyDef.position.set(new Vector2(x, y));

        planetBody = world.createBody(groundBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(Constants.PLANETRADIUS);
        Fixture fixture = planetBody.createFixture(circle, 0.0f);
        fixture.setUserData("PLANET_" + planetID);
        circle.dispose();

        BodyDef hitbox = new BodyDef();
        hitbox.position.set(new Vector2(x, y));

        planetBody = world.createBody(hitbox);

        CircleShape circleH = new CircleShape();
        circleH.setRadius(Constants.PLANETRADIUS + Constants.PLAYERMAXJUMP);
        Fixture fixtureH = planetBody.createFixture(circleH, 0.0f);
        fixtureH.setUserData("PH_"+planetID);
        fixtureH.setSensor(true);
        circleH.dispose();

        if(rocketPos!=-1)
        {
            float rX = (float) (x + (Constants.PLANETRADIUS + Constants.ROCKETHEIGHT / 2) * Math.cos(Math.toRadians(rocketPos)));
            float rY = (float) (y + (Constants.PLANETRADIUS + Constants.ROCKETHEIGHT / 2) * Math.sin(Math.toRadians(rocketPos)));


            BodyDef rocket = new BodyDef();
            rocket.position.set(new Vector2(rX, rY));
            rocketBody = world.createBody(rocket);
            PolygonShape rocketBox = new PolygonShape();
            rocketBox.setAsBox(Constants.ROCKETHEIGHT, Constants.ROCKETSIZE);
            Fixture rF = rocketBody.createFixture(rocketBox, 0.0f);
            rF.setUserData("ROCKET");
            rF.setSensor(true);
            rocketBody.setTransform(rX, rY, (float) Math.toRadians(rocketPos));
            rocketBody.setFixedRotation(true);
            rocketBody.setUserData("ROCKET");
            rocketBox.dispose();
        }

        for (Landform landform : landforms)
        {
            landform.construct(world, x, y);
        }

    }

    public void render(float delta, Batch batch, Matrix4 projMatrix)
    {
        batch.draw(tex, x-Constants.PLANETRADIUS, y-Constants.PLANETRADIUS,Constants.PLANETRADIUS*2,Constants.PLANETRADIUS*2); //TODO render?
        if (landforms != null)
        {
            for (Landform landform : landforms)
            {
                landform.render(delta, projMatrix);
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
