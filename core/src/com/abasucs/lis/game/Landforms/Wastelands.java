package com.abasucs.lis.game.Landforms;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Abascus on 25.12.2015.
 */
public class Wastelands extends Landform
{
    boolean hasTrampoline;
    Body hitbox;

    public Wastelands(int p)
    {
        super(Type.WASTELANDS, p,0,0);
    }

    @Override
    public void render(float delta, Matrix4 projMatrix)
    {
//TODO
    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
