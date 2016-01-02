package com.abasucs.lis.menu;

import com.abasucs.lis.Constants;
import com.abasucs.lis.Main;
import com.abasucs.lis.Settings;
import com.abasucs.lis.game.Level;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Jannik on 23.12.2015.
 */
public class GameScreen extends InputListener implements Screen
{
    Main game;
    Stage stage;
    Level level;

    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;
    float Gwidth, Gheight;

    Texture back_1;

    TextButton titleLabel;
    Label emptyLabel;
    TextButton openSettings;
    TextButton abortMission;

    Label FPS;
    Label timeLeft;

    boolean isRunning = true;


    public GameScreen(Main instance, Level l)
    {
        game = instance;

        batch = new SpriteBatch();
        back_1 = new Texture("menu/badlogic.jpg");
        level = l;
    }

    @Override
    public void render(float delta)
    {
        Gwidth = stage.getWidth();
        Gheight = stage.getHeight();
        FPS.setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        timeLeft.setText((int) (level.timeLeft / 60) + ":" + level.timeLeft % 60);

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE))
        {
            isRunning = !isRunning;
            stage.clear();
            addUI();

        }

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        batch.begin();
        UIHelper.renderStarField(delta, batch, Gwidth, Gheight);
        level.render(delta, isRunning, batch, cam.combined);
        batch.end();
        stage.draw();

    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();

        return name.equals("settings") || name.equals("abortMission");
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();
        if (name.equals("settings") && openSettings.isChecked())
        {
           //TODO fix bug game.setScreen(new SettingsScreen(game, this));
        }
        else if (name.equals("abortMission") && abortMission.isChecked())
        {
        }
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

        if (!isRunning)
        {
            Table table = new Table();
            table.center();
            titleLabel = UIHelper.genButton(level.name, "", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
            emptyLabel = new Label("", UIHelper.uiSkin);
            openSettings = UIHelper.genButton("Settings", "settings", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
            openSettings.addListener(this);
            abortMission = UIHelper.genButton("Abort Mission", "abortMission", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
            abortMission.addListener(this);

            table.add(titleLabel).width(unitWidth * 5f).height(unitHeight * 0.7f);
            table.row();
            table.add(emptyLabel).width(unitWidth).height(unitHeight * 3f);
            table.row();
            table.add(openSettings).width(unitWidth * 7f).height(unitHeight * 1f);
            table.row();
            table.add(emptyLabel).width(unitWidth * 10).height(unitHeight * 1f);
            table.row();
            table.add(abortMission).width(unitWidth * 3f).height(unitHeight * 1f);
            table.row();

            table.setPosition(unitWidth * 5f, unitHeight * 5f);
            stage.addActor(table);
        }


        FPS = new Label("FPS: " + Gdx.graphics.getFramesPerSecond(), UIHelper.getLStyle(38, 0));
        FPS.setPosition(0, unitHeight * 10, Align.topLeft);

        timeLeft = new Label((int) (level.timeLeft / 60) + ":" + level.timeLeft % 60, UIHelper.getLStyle(38, 0));
        timeLeft.setPosition(unitWidth * 10, unitHeight * 10, Align.topRight);

        if(Settings.showFPS){
            stage.addActor(FPS);
        }

        if (level.timeLeft != -1){
            stage.addActor(timeLeft);
        }
    }

    @Override
    public void show()
    {
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        cam = new OrthographicCamera(Constants.WORLDX, Constants.WORLDY);
        viewport = new ScreenViewport(cam);
        cam.update();
        cam.position.set(cam.viewportWidth, cam.viewportHeight, 0);


        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void hide()
    {
     //   dispose();
    }

    @Override
    public void pause()
    {
        isRunning = false;
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
