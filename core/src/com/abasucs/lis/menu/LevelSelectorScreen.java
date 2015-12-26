package com.abasucs.lis.menu;

import com.abasucs.lis.Constants;
import com.abasucs.lis.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abascus on 26.12.2015.
 */
public class LevelSelectorScreen extends InputListener implements Screen
{

    Main game;
    Stage stage;

    SpriteBatch batch;
    OrthographicCamera cam;
    Viewport viewport;
    float Gwidth, Gheight;

    Texture back_1, back_2, back_3;
    float timer_1, timer_2, timer_3;


    Label emptyLabel;
    TextButton exitButton;
    Map<String, Realm> realms = new HashMap<String, Realm>();


    public LevelSelectorScreen(Main instance)
    {
        game = instance;

        batch = new SpriteBatch();
        back_1 = new Texture("menu/back_1.png");
        back_2 = new Texture("menu/back_2.png");
        back_3 = new Texture("menu/back_3.png");
    }

    float mult = 1.8f;

    @Override
    public void render(float delta)
    {
        Gwidth = stage.getWidth();
        Gheight = stage.getHeight();

        timer_1 += delta;
        timer_2 += delta;
        timer_3 += delta;

        timer_1 = timer_1 % 16;
        timer_2 = timer_2 % 20;
        timer_3 = timer_3 % 24;

        Gdx.gl.glClearColor(0f, 0f, 0f, 0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        batch.begin();

        batch.draw(back_1, timer_1 * (Gwidth / 16) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24) - 2 * Gwidth, -Gheight, Gwidth, Gheight * mult);

        batch.draw(back_1, timer_1 * (Gwidth / 16) - Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20) - Gwidth, -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24) - Gwidth, -Gheight, Gwidth, Gheight * mult);

        batch.draw(back_1, timer_1 * (Gwidth / 16), -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_2, timer_2 * (Gwidth / 20), -Gheight, Gwidth, Gheight * mult);
        batch.draw(back_3, timer_3 * (Gwidth / 24), -Gheight, Gwidth, Gheight * mult);

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

        if (name.equals("playFreeRoam") || name.equals("playArcade") || name.equals("settings") || name.equals("credits") || name.equals("exit"))
        {
            return true;
        }
        return false;
    }

    public void touchUp(InputEvent event, float x, float y, int pointer, int button)
    {
        String name = event.getListenerActor().getName();
        if (name.equals("exit") && exitButton.isChecked())
        {
            Gdx.app.exit();
        }
    }


    public void addUI()
    {
        float unitWidth = stage.getWidth() / 10;
        float unitHeight = stage.getHeight() / 10;

        Table table = new Table();
        table.align(Align.top);

        emptyLabel = new Label("", UIHelper.uiSkin);
        exitButton = UIHelper.genButton("X", "exit", unitWidth, unitHeight, 0, 0, 38, false);
        exitButton.addListener(this);

        table.add(exitButton).width(unitWidth / 3).height(unitHeight).align(Align.topRight);
        table.row();
        table.add(emptyLabel).width(unitWidth * 10).height(unitHeight * 0.5f);
        table.row();

        FileHandle handle = Gdx.files.internal(Constants.levelPath);
        if (handle.isDirectory())
        {
            FileHandle[] dirs = handle.list();
            for (int i = 0; i < dirs.length; i++)
            {
                Label l = new Label(dirs[i].name().substring(2), UIHelper.getLStyle(38));
                table.add(l).width(unitWidth / 3).height(unitHeight);
                table.row();
                Realm r = new Realm(dirs[i].name(), l);
                HorizontalGroup group = new HorizontalGroup();
                FileHandle[] files = dirs[i].list();
                int j;
                for (j = 0; j < files.length; j++)
                {
                    TextButton b = UIHelper.genButton(files[j].nameWithoutExtension(), dirs[i].name() + "/" + files[j].name(), unitHeight / 2, unitHeight / 2, 0, 0, 28, true);
                    b.padLeft(unitHeight/3);
                    group.addActor(b);
                    r.levels.put(files[j].name(), b);
                }
                table.add(group).width(unitHeight / 2 * j).height(unitHeight / 2);
                table.row();
                realms.put(dirs[i].name(), r);
            }
        }


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
