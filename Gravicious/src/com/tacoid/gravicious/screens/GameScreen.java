package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.GameMechanics;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.Level;

public class GameScreen extends AbstractGameScreen implements GestureListener {

	private static GameScreen instance = null;

	public static GameScreen getInstance() {

		if(instance == null) {
			instance = new GameScreen();
		}

		return instance;
	}

	private class EditorButton extends TextButton {
		public EditorButton() {
			super("Editor", Gravicious.getInstance().globalSkin);
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					EditorScreen screen = EditorScreen.getInstance();
					screen.init();
					//screen.setLevel(game.getLevel());
					Gravicious.getInstance().setScreen(screen);
				}
			});
		}
	}
	
	private GameMechanics game;
	private Table tableTL;
	private Box2DDebugRenderer debugRenderer;

	GameScreen() {
		debugRenderer = new Box2DDebugRenderer();
		game = new GameMechanics();
		tableTL = new Table();
		tableTL.setFillParent(true);
		tableTL.left().top();
		tableTL.add(new EditorButton());

		InputMultiplexer im = new InputMultiplexer(new GestureDetector(this), stage); // Order matters here!
		Gdx.input.setInputProcessor(im);
	}

	public void setLevel(Level level) {
		game.init(level);
		refreshStage();
	}

	private void refreshStage() {
		stage.clear();
		stage.addActor(game.getLevel());
		stage.addActor(game.getPlayer());
		stage.addActor(tableTL);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init() {
		InputMultiplexer im = new InputMultiplexer(new GestureDetector(this), stage); // Order matters here!
		Gdx.input.setInputProcessor(im);
	}

	@Override
	public void renderScreen(float delta) {
		game.update(delta);
		debugRenderer.render(game.getLevel().getWorld(), stage.getCamera().combined);
	}

	@Override
	public boolean isEditor() {
		return false;
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		game.jump();
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean zoom(float initialDistance, float distance) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2,
			Vector2 pointer1, Vector2 pointer2) {
		// TODO Auto-generated method stub
		return false;
	}
}