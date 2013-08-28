package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.input.GestureDetector.GestureListener;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.Level;
import com.tacoid.gravicious.LevelElement;
import com.tacoid.gravicious.Planet;
import com.tacoid.gravicious.Sun;


public class EditorScreen extends GameScreen implements GestureListener{
	
	private static EditorScreen instance = null;
	
	public static EditorScreen getInstance() {
		
		if(instance == null) {
			instance = new EditorScreen();
		}
		
		return instance;
	}
	
	private Level level = null;
	
	/* Classe générique de bouton pour créer un nouvel élément de niveau */
	private class ElementButton extends TextButton{
		
		final Class<? extends LevelElement> spawned_class;

		public ElementButton(Class <? extends LevelElement> c) {
			super(c.getName().substring(c.getName().lastIndexOf('.')+1), Gravicious.getInstance().globalSkin);
			
			this.spawned_class = c;
			
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					
					LevelElement element;
					try {
						element = spawned_class.newInstance();
						level.addElement(element);
					} catch (InstantiationException e1) {
						e1.printStackTrace();
					} catch (IllegalAccessException e1) {
						e1.printStackTrace();
					}
					
				}
			});
		}
	}

	private EditorScreen() {
		super();
		
		InputMultiplexer im = new InputMultiplexer(new GestureDetector(this), stage);
		Gdx.input.setInputProcessor(im);
		level = new Level();
		Table tableL = new Table();
		tableL.setFillParent(true);
		tableL.left().bottom();
		tableL.add(new ElementButton(Planet.class));
		tableL.add(new ElementButton(Sun.class));
		
		Table tableR = new Table();
		tableR.setFillParent(true);
		tableR.right().bottom();
		tableR.add(new TextButton("+", Gravicious.getInstance().globalSkin));
		tableR.add(new TextButton("-", Gravicious.getInstance().globalSkin));
		stage.addActor(level);
		stage.addActor(tableL);
		stage.addActor(tableR);
		
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
		
		
	}

	@Override
	public void renderScreen(float delta) {

	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean tap(float x, float y, int count, int button) {
		if(count == 2) {
			level.removeElement(level.getSelectedElement());
		}
		return false;
	}

	@Override
	public boolean longPress(float x, float y) {

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
