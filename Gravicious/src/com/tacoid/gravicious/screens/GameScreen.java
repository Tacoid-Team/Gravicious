package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.GameMechanics;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.Level;

public class GameScreen extends AbstractGameScreen implements InputProcessor {

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
		
		//Gdx.input.setInputProcessor(this);
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
		//InputMultiplexer im = new InputMultiplexer(new GestureDetector(this), stage); // Order matters here!
		//Gdx.input.setInputProcessor(im);
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void renderScreen(float delta) {

		game.update(delta);
		debugRenderer.render(game.getLevel().getWorld(), stage.getCamera().combined);
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.DPAD_LEFT) {
			game.setLeftPressed(true);
		} else if(keycode == Keys.DPAD_RIGHT) {
			game.setRightPressed(true);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.DPAD_LEFT) {
			game.setLeftPressed(false);
		} else if(keycode == Keys.DPAD_RIGHT) {
			game.setRightPressed(false);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {	
		if(x<this.VIRTUAL_WIDTH/2)
			game.setLeftPressed(true);
		if(x>this.VIRTUAL_WIDTH/2)
			game.setRightPressed(true);
		return stage.touchDown(x, y, pointer, button);
	}

	@Override
	public boolean touchDragged(int x, int y, int arg2) {
		return stage.touchDragged(x, y, arg2);
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		//game.jump();
		if(x<this.VIRTUAL_WIDTH/2)
			game.setLeftPressed(false);
		if(x>this.VIRTUAL_WIDTH/2)
			game.setRightPressed(false);
		return stage.touchUp(x, y, pointer, button);
	}
	
	@Override
	public boolean mouseMoved(int x, int y) {
		return stage.mouseMoved(x, y);
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return stage.scrolled(amount);
	}

	@Override
	public boolean isEditor() {
		// TODO Auto-generated method stub
		return false;
	}
}