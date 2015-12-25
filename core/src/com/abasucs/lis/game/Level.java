package com.abasucs.lis.game;

import com.abasucs.lis.Constants;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abasucs on 25.12.2015.
 */
public class Level
{
    public Planet[] planets;
    public Map<String, Integer> resources= new HashMap<String, Integer>();

    public void setupResources()
    {
        for(int i = 0;i< Constants.resources.length;i++)
        {
            resources.put(Constants.resources[i], 0);
        }
    }
}
