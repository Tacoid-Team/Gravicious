package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.GameMechanics;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.Level;

public class PlayScreen extends GameScreen {
	
	private static PlayScreen instance = null;
	
	public static PlayScreen getInstance() {
		
		if(instance == null) {
			instance = new PlayScreen();
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
	
	PlayScreen() {
		debugRenderer = new Box2DDebugRenderer();
		game = new GameMechanics();
		tableTL = new Table();
		tableTL.setFillParent(true);
		tableTL.left().top();
		tableTL.add(new EditorButton());
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
		Gdx.input.setInputProcessor(stage);
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
}