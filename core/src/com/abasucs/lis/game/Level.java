package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abasucs on 25.12.2015.
 */
public class Level implements ContactListener
{
    public String name;
    public Planet[] planets;
    public Map<String, Integer> resources = new HashMap<String, Integer>();
    public Map<String, Integer> quest = new HashMap<String, Integer>();
    public Player player;
    public int playerPlanet;

    com.badlogic.gdx.physics.box2d.World world;
    Box2DDebugRenderer debugRenderer;
    private float tickAccumulator = 0;


    public Level()
    {
        setupResources();
        Box2D.init();
        world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer(true, true, false, true, false, true);
        world.setContactListener(this);
        debug();
    }

    public void debug()
    {

    }


    @Override
    public void beginContact(Contact contact)
    {
        System.out.println("A: " + contact.getFixtureA().getUserData() + " B: " + contact.getFixtureB().getUserData());
    }

    @Override
    public void endContact(Contact contact)
    {
    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold)
    {
    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse)
    {
    }

    public void postLoad()
    {
        player.construct(world, planets[playerPlanet - 1].x, planets[playerPlanet - 1].y);
    }

    public void tick(float delta)
    {
        if (Gdx.input.isKeyPressed(Input.Keys.A))
        {
            player.pos+=delta*Constants.PLAYERSPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D))
        {
            player.pos-=delta*Constants.PLAYERSPEED;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)&&player.jumpHeight<Constants.PLAYERMAXJUMP)
        {
            player.jumpHeight += delta*Constants.PLAYERJETSPEED;
        }
        player.updatePos(delta, planets[playerPlanet - 1].x, planets[playerPlanet - 1].y,Gdx.input.isKeyPressed(Input.Keys.SPACE));
    }

    public void render(float delta, Batch batch, Matrix4 projMatrix)
    {
        tick(delta);
        float frameTime = Math.min(delta, 0.25f);
        tickAccumulator += frameTime;
        while (tickAccumulator >= Constants.TIME_STEP)
        {
            world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
            tickAccumulator -= Constants.TIME_STEP;
        }

        if (planets != null)
        {
            for (int i = 0; i < planets.length; i++)
            {
                planets[i].render(delta, batch, projMatrix);
            }
        }

        debugRenderer.render(world, projMatrix);
    }

    public void addQuest(String id, int amount)
    {
        if (quest.containsKey(id))
        {
            quest.put(id, quest.get(id) + amount);
        }
    }

    public void addResource(String id, int amount)
    {
        if (resources.containsKey(id))
        {
            resources.put(id, resources.get(id) + amount);
        }
    }

    public void addPlanet(Planet p)
    {
        p.construct(world);
        if (planets == null)
        {
            planets = new Planet[1];
        }
        else
        {
            planets = Arrays.copyOf(planets, planets.length + 1);
        }
        planets[planets.length - 1] = p;


    }

    public void setupResources()
    {
        for (int i = 0; i < Constants.RESOURCES.length; i++)
        {
            resources.put(Constants.RESOURCES[i], 0);
            quest.put(Constants.RESOURCES[i], 0);
        }
    }

}
