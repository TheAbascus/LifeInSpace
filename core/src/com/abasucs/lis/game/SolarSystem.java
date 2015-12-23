package com.abasucs.lis.game;

import com.abasucs.lis.Constants;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

/**
 * Created by Jannik on 23.12.2015.
 */
public class SolarSystem
{
    com.badlogic.gdx.physics.box2d.World world;
    Box2DDebugRenderer debugRenderer;

    public SolarSystem()
    {
        Box2D.init();
        world = new com.badlogic.gdx.physics.box2d.World(new Vector2(0, -100), true);
        debugRenderer = new Box2DDebugRenderer();


            // First we create a body definition
        BodyDef bodyDef = new BodyDef();
            // We set our body to dynamic, for something like ground which doesn't move we would set it to StaticBody
        bodyDef.type = BodyDef.BodyType.DynamicBody;
            // Set our body's starting position in the world
        bodyDef.position.set(500, 500);

// Create our body in the world using our body definition
        Body body = world.createBody(bodyDef);

// Create a circle shape and set its radius to 6
        CircleShape circle = new CircleShape();
        circle.setRadius(100f);

// Create a fixture definition to apply our shape to
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = circle;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.4f;
        fixtureDef.restitution = 0.6f; // Make it bounce a little bit

// Create our fixture and attach it to the body
        Fixture fixture = body.createFixture(fixtureDef);

// Remember to dispose of any shapes after you're done with them!
// BodyDef and FixtureDef don't need disposing, but shapes do.
        circle.dispose();

        BodyDef groundBodyDef =new BodyDef();
        groundBodyDef.position.set(new Vector2(0, 10));

        Body groundBody = world.createBody(groundBodyDef);

        PolygonShape groundBox = new PolygonShape();
        groundBox.setAsBox(1000, 10.0f);
        groundBody.createFixture(groundBox, 0.0f);
        groundBox.dispose();
        addPlanet(300,300,100);
        addPlanet(600,300,100);
    }

    public void addPlanet(float x, float y, float r)
    {

        BodyDef groundBodyDef =new BodyDef();
        groundBodyDef.position.set(new Vector2(x, y));

        Body groundBody = world.createBody(groundBodyDef);

        CircleShape circle = new CircleShape();
        circle.setRadius(r);
        groundBody.createFixture(circle, 0.0f);
        circle.dispose();
    }

    private float accumulator = 0;

    public void tick(float delta)
    {
        float frameTime = Math.min(delta, 0.25f);
        accumulator += frameTime;
        while (accumulator >= Constants.TIME_STEP)
        {
            world.step(Constants.TIME_STEP, Constants.VELOCITY_ITERATIONS, Constants.POSITION_ITERATIONS);
            accumulator -= Constants.TIME_STEP;
        }
    }

    public void render(OrthographicCamera camera)
    {
        debugRenderer.render(world, camera.combined);
    }
}
