package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.abasucs.lis.Util;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Abascus on 30.12.2015.
 */
public class Player
{
    Body playerBody;
    float pos;
    float jumpHeight;
    String currentLandform;

    public void construct(World world, float pX, float pY)
    {

        float rX = (float) (pX + (Constants.PLANETRADIUS + Constants.PLAYERHEIGHT / 2) * Math.cos(Math.toRadians(pos)));
        float rY = (float) (pY + (Constants.PLANETRADIUS + Constants.PLAYERHEIGHT / 2) * Math.sin(Math.toRadians(pos)));

        BodyDef player = new BodyDef();
        player.type = BodyDef.BodyType.DynamicBody;
        player.position.set(new Vector2(rX, rY));
        playerBody = world.createBody(player);
        PolygonShape playerBox = new PolygonShape();
        playerBox.setAsBox(Constants.PLAYERHEIGHT, Constants.PLAYERSIZE);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = playerBox;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.6f;
        Fixture fixture = playerBody.createFixture(fixtureDef);
        fixture.setUserData("PLAYER");
        playerBody.setTransform(rX, rY, (float) Math.toRadians(pos));
        playerBody.setFixedRotation(true);
        playerBody.setUserData("PLAYER");
        playerBox.dispose();

    }

    public void enterPlanet(Vector2 point, float pX, float pY)
    {
        currentLandform="";
        pos = (float)Math.toDegrees(Math.atan2(playerBody.getPosition().y - pY,playerBody.getPosition().x - pX));
        jumpHeight = Constants.PLAYERMAXJUMP;

    }

    public void updatePos(float delta, float pX, float pY, boolean isFlying)
    {
        if(jumpHeight>0&&!isFlying)
        {
            jumpHeight-=delta*Constants.PLANETGRAV;
        }
        else if(!isFlying)
        {
            jumpHeight=0;
        }
        float rX = (float) (pX + (Constants.PLANETRADIUS + Constants.PLAYERHEIGHT / 2+jumpHeight) * Math.cos(Math.toRadians(pos)));
        float rY = (float) (pY + (Constants.PLANETRADIUS + Constants.PLAYERHEIGHT / 2+jumpHeight) * Math.sin(Math.toRadians(pos)));

        Vector2 vel = new Vector2(rX,rY).sub(playerBody.getPosition()).nor().scl((float)(Constants.PLAYERSPEED*Constants.DEGSIZE*2));
        if((int)rX==(int)playerBody.getPosition().x&&(int)rY==(int)playerBody.getPosition().y)
        {
            vel = new Vector2(0,0);
        }
        playerBody.setTransform(playerBody.getPosition().x, playerBody.getPosition().y, (float) Math.toRadians(pos));
        playerBody.setLinearVelocity(vel);
    }
}
