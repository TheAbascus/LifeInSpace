package com.abasucs.lis.game;

import com.abasucs.lis.Constants;

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

    public Level()
    {
        setupResources();
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
        for(int i = 0;i< Constants.resources.length;i++)
        {
            resources.put(Constants.resources[i], 0);
            quest.put(Constants.resources[i], 0);
        }
    }
}
