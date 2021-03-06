package com.abasucs.lis.menu;


import com.abasucs.lis.Constants;
import com.abasucs.lis.Main;
import com.abasucs.lis.Settings;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;


/**
 * Created by Abascus on 26.12.2015.
 */
public class SettingsScreen extends InputListener implements Screen
{

    Main game;
    Stage stage;
    Screen parent;

    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;

    Label emptyLabel;
    TextButton saveButton;
    TextButton cancelButton;

    TextField width;
    TextField height;
    SelectBox<String> resolution;

    CheckBox fullscreen;
    CheckBox vSync;
    CheckBox showFPS;


    public SettingsScreen(Main instance, Screen p)
    {
        game = instance;
        batch = new SpriteBatch();
        parent = p;
    }

    float mult = 1.8f;

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0f, 0f, 0.3f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        batch.begin();

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
    }
    public boolean handle(Event e)
    {
        if ((e instanceof ChangeEvent))
        {
           ChangeEvent event = (ChangeEvent)e;
            if (e.getTarget().getName().equals("resolutionSelectBox"))
            {
                String[] s = resolution.getSelected().split("x");
                width.setText(s[0]);
            }
            return true;
        }
        else
        {
           return super.handle(e);
        }

    }
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
    {
        return true;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();
        if (name.equals("save") && saveButton.isChecked())
        {
            //TODO read&applie Settings
            Settings.setFullscreen(fullscreen.isChecked());
            Settings.setVSync(vSync.isChecked());
            Settings.setShowFPS(showFPS.isChecked());
            try
            {
                Settings.setScreenSize(Integer.parseInt(width.getText()), Integer.parseInt(height.getText()));
            } catch (Exception e)
            {
            }
            game.setScreen(parent);
        }
        else if (name.equals("cancel") && cancelButton.isChecked())
        {
            game.setScreen(parent);
        }
    }


    public void addUI()
    {
        float unitWidth = stage.getWidth() / 10;
        float unitHeight = stage.getHeight() / 10;

        Table table = new Table();
        table.center();

        emptyLabel = new Label("", UIHelper.uiSkin);
        emptyLabel.setWidth(unitWidth * 3f);

        resolution = new SelectBox<String>(UIHelper.getSBStyle(28));
        resolution.setItems(Constants.resolutions);
        resolution.setName("resolutionSelectBox");
        resolution.addListener(this);

        width = new TextField(Gdx.graphics.getWidth() + "", UIHelper.getTFStyle(30));
        width.setName("width");
        height = new TextField(Gdx.graphics.getWidth() + "", UIHelper.getTFStyle(30));
        height.setName("height");

        vSync = new CheckBox("Enable vSync", UIHelper.getCBStyle(28));
        vSync.setName("vSync");

        fullscreen = new CheckBox("Enable Fullscreen", UIHelper.getCBStyle(28));
        fullscreen.setName("fullscreen");

        showFPS = new CheckBox("Show FPS", UIHelper.getCBStyle(28));
        showFPS.setName("showFPS");


        saveButton = UIHelper.genButton("Save", "save", unitWidth * 3f, unitHeight, unitWidth * 7, 0, 38, false);
        saveButton.align(Align.right);
        saveButton.addListener(this);
        cancelButton = UIHelper.genButton("Cancel", "cancel", unitWidth * 3f, unitHeight, 0, 0, 38, false);
        cancelButton.align(Align.left);
        cancelButton.addListener(this);


        table.add(emptyLabel).width(unitWidth * 10).height(unitHeight * 2.8f);
        table.row();
        table.add(resolution).width(unitWidth * 3f).height(unitHeight * 1f);
        table.row();
        table.add(width).width(unitWidth * 1f).height(unitHeight * 1f);
        table.add(height).width(unitWidth * 1f).height(unitHeight * 1f);
        table.row();
        table.add(showFPS).width(unitWidth * 3f).height(unitHeight * 1f);
        table.row();
        table.add(vSync).width(unitWidth * 3f).height(unitHeight * 1f);
        table.row();
        table.add(fullscreen).width(unitWidth * 3f).height(unitHeight * 1f);
        table.row();


        table.setPosition(unitWidth * 5f, unitHeight * 5f);
        stage.addActor(table);
        stage.addActor(saveButton);
        stage.addActor(cancelButton);
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
