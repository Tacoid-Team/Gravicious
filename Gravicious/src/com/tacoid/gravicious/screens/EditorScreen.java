package com.tacoid.gravicious.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.Level;
import com.tacoid.gravicious.elements.LevelElement;
import com.tacoid.gravicious.elements.Planet;
import com.tacoid.gravicious.elements.Star;
import com.tacoid.gravicious.elements.Sun;


public class EditorScreen extends GameScreen {
	
	private static EditorScreen instance = null;
	
	public static EditorScreen getInstance() {
		
		if(instance == null) {
			instance = new EditorScreen();
		}
		
		return instance;
	}
	
	private Level level = null;
	private LevelElement selectedElement = null;
	private Selector selector;
	private Table tableL;
	
	private class Selector extends Actor {
	    private Color shapeFillColor = new Color(1.0f, 0.0f, 0.0f ,1.0f);
	    public ShapeRenderer shapeRenderer;

	    public Selector() {
	        shapeRenderer = new ShapeRenderer();
	    }

	    @Override
	    public void draw(SpriteBatch batch, float parentAlpha) {
	    	if(selectedElement!=null) {
	    		batch.end();
	    			shapeRenderer.begin(ShapeType.FilledCircle);
	    				shapeRenderer.setColor(shapeFillColor);
	    				shapeRenderer.filledCircle(selectedElement.getX(), selectedElement.getY(), 10);
	    			shapeRenderer.end();
	    		batch.begin();
	    	}
	    }
	}
	
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
	
	private class DeleteButton extends TextButton {
		public DeleteButton() {
			super("Delete", Gravicious.getInstance().globalSkin);
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if(selectedElement != null) {
						level.removeElement(selectedElement);
						selectedElement = null;
						selector.setVisible(false);
					}
				}
			});
		}
	}

	private EditorScreen() {
		super();

		level = new Level();
		
		tableL = new Table();
		tableL.setFillParent(true);
		tableL.left().bottom();
		tableL.add(new ElementButton(Planet.class));
		tableL.add(new ElementButton(Sun.class));
		tableL.add(new ElementButton(Star.class));
		tableL.add(new DeleteButton());
		
		selector = new Selector();	
		
		refreshStage();
	}
	
	void refreshStage() {
		stage.clear();
		stage.addActor(level);
		stage.addActor(selector);
		stage.addActor(tableL);
		if(selectedElement != null) {
			if(selectedElement.getWidget() != null) {
				stage.addActor(selectedElement.getWidget());
			}
		}
	}
	

	public void setSelectedElement(LevelElement selectedElement) {
		this.selectedElement = selectedElement;
		System.out.println("Element " + selectedElement.getName() + " selected.");
		selector.setVisible(true);
		refreshStage();
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

}
