package com.abasucs.lis.game.Landforms;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Abascus on 26.12.2015.
 */
public class GoldMountain extends IronMountain
{
    public GoldMountain(int p, int maxR, int currentR)
    {
        super(Type.GOLD_MOUNTAIN, p, maxR, currentR);
    }
}
