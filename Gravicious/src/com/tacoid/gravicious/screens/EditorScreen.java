package com.tacoid.gravicious.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
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
import com.tacoid.gravicious.elements.Start;
import com.tacoid.gravicious.elements.Sun;


public class EditorScreen extends AbstractGameScreen {

	private static EditorScreen instance = null;
	Box2DDebugRenderer debugRenderer;  

	public static EditorScreen getInstance() {

		if(instance == null) {
			instance = new EditorScreen();
		}

		return instance;
	}

	private Level level = null;
	private LevelElement selectedElement = null;
	private Selector selector;
	private Table tableBL;
	private Table tableTL;

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

	/* Classe g�n�rique de bouton pour cr�er un nouvel �l�ment de niveau */
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

	private class TestButton extends TextButton {
		public TestButton() {
			super("Test", Gravicious.getInstance().globalSkin);
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					GameScreen screen = GameScreen.getInstance();
					screen.init();
					screen.setLevel(level);
					Gravicious.getInstance().setScreen(screen);
				}
			});
		}
	}

	private EditorScreen() {
		super();

		level = new Level();

		tableBL = new Table();
		tableBL.setFillParent(true);
		tableBL.left().bottom();
		tableBL.add(new ElementButton(Start.class));
		tableBL.add(new ElementButton(Planet.class));
		tableBL.add(new ElementButton(Sun.class));
		tableBL.add(new ElementButton(Star.class));
		tableBL.add(new DeleteButton());

		tableTL = new Table();
		tableTL.setFillParent(true);
		tableTL.left().top();
		tableTL.add(new TestButton());

		selector = new Selector();	

		debugRenderer = new Box2DDebugRenderer();

		refreshStage();
	}

	void refreshStage() {
		stage.clear();
		stage.addActor(level);
		stage.addActor(selector);
		stage.addActor(tableBL);
		stage.addActor(tableTL);
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
		Gdx.input.setInputProcessor(stage);
		refreshStage();
	}

	@Override
	public void renderScreen(float delta) {

	}

	public void setLevel(Level level) {
		this.level = level;
	}

	@Override
	public boolean isEditor() {
		return true;
	}

}
