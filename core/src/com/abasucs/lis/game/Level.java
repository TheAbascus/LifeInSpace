package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abasucs on 25.12.2015.
 */
public class Level
{
    public String name;
    public Planet[] planets;
    public Map<String, Integer> resources= new HashMap<String, Integer>();
    public Map<String, Integer> quest= new HashMap<String, Integer>();
    com.badlogic.gdx.physics.box2d.World world;
    Box2DDebugRenderer debugRenderer;
    private float tickAccumulator = 0;


    public Level()
    {
        setupResources();
        Box2D.init();

        world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, 0), true);
        debugRenderer = new Box2DDebugRenderer();
    }

    public void render(float delta, Matrix4 projMatrix)
    {
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
                planets[i].render(delta, projMatrix);
            }
        }

        debugRenderer.render(world, projMatrix);
    }

    public void addQuest(String id, int amount)
    {
        if(quest.containsKey(id))
        {
            quest.put(id, quest.get(id) + amount);
        }
    }

    public void addResource(String id, int amount)
    {
        if(resources.containsKey(id))
        {
            resources.put(id, resources.get(id) + amount);
        }
    }

    public void addPlanet(Planet p)
    {
        p.construct(world);
        if(planets == null)
        {
            planets = new Planet[1];
        }
        else
        {
            planets = Arrays.copyOf(planets, planets.length+1);
        }
        planets[planets.length-1] = p;


    }

    public void setupResources()
    {
        for(int i = 0;i< Constants.RESOURCES.length;i++)
        {
            resources.put(Constants.RESOURCES[i], 0);
            quest.put(Constants.RESOURCES[i], 0);
        }
    }
}
