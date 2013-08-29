package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GLCommon;
import com.badlogic.gdx.scenes.scene2d.Stage;


public abstract class GameScreen implements Screen {
	
	protected final int VIRTUAL_WIDTH = 1280;
	protected final int VIRTUAL_HEIGHT = 768;
	protected Stage stage;
	
	protected GameScreen() {
		stage = new Stage(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(),
				false);
		stage.setViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, false);
		stage.getCamera().position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
		Gdx.input.setInputProcessor(stage);
	}

	public abstract void init();
	public abstract void renderScreen(float delta);
	public abstract boolean isEditor();

	@Override
	public void render(float delta) {
		renderScreen(delta);
		draw(delta);
	}
	
	private void draw(float delta) {
		GLCommon gl = Gdx.gl;
		
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0.4f, 0.4f, 0.8f, 1);

		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int arg0, int arg1) {
		stage.setViewport(VIRTUAL_WIDTH, VIRTUAL_HEIGHT, false);
		stage.getCamera().position.set(VIRTUAL_WIDTH / 2, VIRTUAL_HEIGHT / 2, 0);
	}
}
