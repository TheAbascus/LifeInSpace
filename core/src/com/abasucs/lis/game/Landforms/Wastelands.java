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
    public void construct(World world, float pX, float pY)
    {
        float x = (float) (pX + (Constants.PLANETRADIUS+Constants.FORESTHEIGHT/2) * Math.cos(Math.toRadians(pos)));
        float y = (float) (pY + (Constants.PLANETRADIUS+Constants.FORESTHEIGHT/2) * Math.sin(Math.toRadians(pos)));

        BodyDef bottom = new BodyDef();
        bottom.position.set(new Vector2(x, y));
       hitbox = world.createBody(bottom);
        PolygonShape bottomBox = new PolygonShape();
        bottomBox.setAsBox(Constants.FORESTHEIGHT, Constants.LANDFORMSIZE);
        Fixture fixture= hitbox.createFixture(bottomBox, 0.0f);
        fixture.setSensor(true);
        hitbox.setTransform(x, y, (float) Math.toRadians(pos));
        hitbox.setFixedRotation(true);
        hitbox.setUserData("WASTELANDS_HITBOX");
        bottomBox.dispose();
    }

    @Override
    public boolean onClick()
    {
        return false;
    }
}
