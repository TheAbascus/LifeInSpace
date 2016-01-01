package com.abasucs.lis.menu;


import com.abasucs.lis.Main;
import com.badlogic.gdx.Gdx;
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


public class MainMenuScreen extends InputListener implements Screen
{

    Main game;
    Stage stage;

    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;
    float Gwidth, Gheight;




    Label emptyLabel;
    TextButton exitButton;
    TextButton startFreeRoam;
    TextButton startArcade;
    TextButton openSettings;
    TextButton openCredits;


    public MainMenuScreen(Main instance)
    {
        game = instance;

        batch = new SpriteBatch();

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

        UIHelper.renderStarField(delta, batch, Gwidth, Gheight);

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
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
        addUI();
        Gwidth = stage.getWidth();
        Gheight = stage.getHeight();
    }

    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();

        if (name.equals("playFreeRoam") || name.equals("playArcade") || name.equals("settings") || name.equals("credits")|| name.equals("exit"))
        {
            return true;
        }
        return false;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();
        if (name.equals("playFreeRoam") && startFreeRoam.isChecked())
        {
            game.setScreen(new LevelSelectorScreen(game));
        }
        else if (name.equals("playArcade") && startArcade.isChecked())
        {
        }
        else if (name.equals("settings") && openSettings.isChecked())
        {
            game.setScreen(new SettingsScreen(game, this));
        }
        else if (name.equals("credits") && openCredits.isChecked())
        {
        }
        else if (name.equals("exit") && exitButton.isChecked())
        {
            Gdx.app.exit();
        }
    }


    public void addUI()
    {
        float unitWidth = stage.getWidth() / 10;
        float unitHeight = stage.getHeight() / 10;

        Table table = new Table();
        table.center();

        emptyLabel = new Label("", UIHelper.uiSkin);
        exitButton = UIHelper.genButton("X", "exit", unitWidth, unitHeight, 0, 0, 38, false);
        exitButton.addListener(this);
        startFreeRoam = UIHelper.genButton("Start Free Roam Mode", "playFreeRoam", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
        startFreeRoam.addListener(this);
        startArcade = UIHelper.genButton("Start Arcade Mode", "playArcade", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
        startArcade.addListener(this);
        openSettings = UIHelper.genButton("Settings", "settings", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
        openSettings.addListener(this);
        openCredits = UIHelper.genButton("Credits", "credits", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38, false);
        openCredits.addListener(this);

        table.add(exitButton).width(unitWidth / 3).height(unitHeight).align(Align.right);
        table.row();
        table.add(startFreeRoam).width(unitWidth * 7f).height(unitHeight * 1f);
        table.row();
        table.add(emptyLabel).width(unitWidth).height(unitHeight * 0.7f);
        table.row();
        table.add(startArcade).width(unitWidth * 7f).height(unitHeight * 1f);
        table.row();
        table.add(emptyLabel).width(unitWidth).height(unitHeight * 0.7f);
        table.row();
        table.add(openSettings).width(unitWidth * 7f).height(unitHeight * 1f);
        table.row();
        table.add(emptyLabel).width(unitWidth*10).height(unitHeight * 3.6f);
        table.row();
        table.add(openCredits).width(unitWidth * 3f).height(unitHeight * 1f).align(Align.right);
        table.row();

        table.setPosition(unitWidth * 5f, unitHeight * 5f);
        stage.addActor(table);
    }

    @Override
    public void show()
    {
        cam = new OrthographicCamera(1000, 1000);
        viewport = new ScreenViewport(cam);
        cam.update();
        cam.position.set(cam.viewportWidth, cam.viewportHeight, 0);
        batch.setProjectionMatrix(cam.combined);
    }

    @Override
    public void hide()
    {
//        dispose();
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
