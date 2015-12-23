package com.abasucs.lis.menu;


import com.abasucs.lis.Main;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

	
	Label emptyLabel;
	TextButton openScan;
	TextButton openTransfer;
	TextButton openLog;
	TextButton openSettings;
	ImageButton showQR;
	Texture QRTexture;
	

	public MainMenuScreen(Main instance)
	{
		game = instance;

		batch = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.1f, 0.824f, 0.867f, 0f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(delta);
		batch.begin();
		stage.draw();
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		if(stage == null)
		{
			stage = new Stage(viewport);
		}
		stage.clear();
		viewport.update(width, height);
		Gdx.input.setInputProcessor(stage);
		addUI();
	}

	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		String name = event.getListenerActor().getName();

		if(name.equals("showQR")||name.equals("scan")||name.equals("transfer")||name.equals("log")||name.equals("settings"))
		{
			return true;
		}
		return false;
	}

	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		String name = event.getListenerActor().getName();
		if(name.equals("showQR"))
		{
		}
		else if(name.equals("scan"))
		{
		}
		else if(name.equals("transfer"))
		{
			//game.setScreen(new TransferScreen(game));
		}
	}


	public void addUI()
	{
		float unitWidth = stage.getWidth()/10;
		float unitHeight = stage.getHeight()/10;

		Table table = new Table();
		table.center();
		
		emptyLabel = new Label("",UIHelper.uiSkin);
		if(Main.PLATFORM==Main.PLATFORM_ANDROID||Main.PLATFORM==Main.PLATFORM_IOS)
		{
			openScan = UIHelper.genButton("QR Überweisung", "scan", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38);
			openScan.addListener(this);
		}
		openTransfer = UIHelper.genButton("Manuelle Überweisung","transfer", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38);
		openTransfer.addListener(this);
		openLog = UIHelper.genButton("Verlauf","log", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38);
		openLog.addListener(this);
		openSettings = UIHelper.genButton("Einstellungen","settings", unitWidth * 2.8f, unitHeight * 2.5f, 0, 0, 38);
		openSettings.addListener(this);
		if(QRTexture!=null)
		{
			showQR = new ImageButton(new TextureRegionDrawable(new TextureRegion(QRTexture)));
			showQR.setName("showQR");
			showQR.addListener(this);
		}

		if(Main.PLATFORM==Main.PLATFORM_ANDROID||Main.PLATFORM==Main.PLATFORM_IOS)
		{
			table.add(openScan).width(unitWidth * 7f).height(unitHeight * 1f);
			table.row();

		table.add(emptyLabel).width(unitWidth*7f).height(unitHeight * 0.7f);
		table.row();
		}
		table.add(openTransfer).width(unitWidth*7f).height(unitHeight * 1f);
		table.row();
		table.add(emptyLabel).width(unitWidth*7f).height(unitHeight * 0.7f);
		table.row();
		table.add(openLog).width(unitWidth*7f).height(unitHeight * 1f);
		table.row();
		table.add(emptyLabel).width(unitWidth*7f).height(unitHeight * 0.7f);
		table.row();
		table.add(openSettings).width(unitWidth*7f).height(unitHeight * 1f);
		table.row();
		table.add(emptyLabel).width(unitWidth*7f).height(unitHeight * 0.7f);
		table.row();
		if(QRTexture!=null)
		{
			table.add(showQR).width(unitWidth * 1f).height(unitWidth * 1f).align(Align.right);
			table.row();
		}
		table.setPosition(unitWidth * 5f, unitHeight * 5f);
		stage.addActor(table);
		stage.setDebugAll(true);
	}

	@Override
	public void show(){
		cam = new OrthographicCamera(1000, 1000);
		viewport = new ScreenViewport(cam);
		cam.update();
		cam.position.set(cam.viewportWidth/2, cam.viewportHeight/2, 0);
		batch.setProjectionMatrix(cam.combined);
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void dispose()
	{
		batch.dispose();
		stage.dispose();
	}
}
