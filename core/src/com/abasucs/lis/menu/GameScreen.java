package com.abasucs.lis.menu;

import com.abasucs.lis.Constants;
import com.abasucs.lis.Main;
import com.abasucs.lis.game.Level;
import com.abasucs.lis.game.LevelIO;
import com.abasucs.lis.game.SolarSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Jannik on 23.12.2015.
 */
public class GameScreen implements Screen
{
    Main game;
    Stage stage;
    Level level;

    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;
    float Gwidth, Gheight;


    public GameScreen(Main instance, Level l)
    {
        game = instance;

        batch = new SpriteBatch();
       level = l;
    }

    @Override
    public void render(float delta)
    {
        Gwidth = stage.getWidth();
        Gheight = stage.getHeight();


        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        batch.begin();
        level.render(delta, cam);
        stage.draw();
        batch.end();
    }

    @Override
    public void resize(int width, int height)
    {
        if (stage == null)
        {
            stage = new Stage(viewport);
        }
        stage.clear();
        viewport.update(width, height);
        Gdx.input.setInputProcessor(stage);
        addUI();
        Gwidth = stage.getWidth();
        Gheight = stage.getHeight();
    }
    public void addUI()
    {
        float unitWidth = stage.getWidth() / 10;
        float unitHeight = stage.getHeight() / 10;

    }

    @Override
    public void show()
    {
        cam = new OrthographicCamera(Constants.WORLDX, Constants.WORLDY);
        viewport = new ScreenViewport(cam);
        cam.update();
        cam.position.set(cam.viewportWidth, cam.viewportHeight, 500);
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void hide()
    {
        dispose();
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        batch.dispose();
        stage.dispose();
    }
}
