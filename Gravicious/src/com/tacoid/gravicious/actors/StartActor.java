package com.tacoid.gravicious.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.elements.Start;
import com.tacoid.gravicious.screens.AbstractGameScreen;

public class StartActor extends ElementActor {
	private ShapeRenderer shapeRenderer;
	private Color shapeFillColor = new Color(0.20f, 1.00f, 0.2f, 1.0f);

	public StartActor(Start element) {
		super(element);
		shapeRenderer = new ShapeRenderer();
		setX(100);
		setY(100);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
		batch.end();
			shapeRenderer.begin(ShapeType.Circle);
				shapeRenderer.setColor(shapeFillColor);
				shapeRenderer.circle(getX(), getY(), 20);
			shapeRenderer.end();
		batch.begin();
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		AbstractGameScreen screen = (AbstractGameScreen)Gravicious.getInstance().getScreen();

		if(!screen.isEditor()) return null;
		if (touchable && this.getTouchable() != Touchable.enabled) return null;

		double distance = x*x + y*y;
		if (distance <= 20*20){
			return this;
		} else {
			return null;
		}
	}

	@Override
	public void createBody(World world) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBody() {
		// TODO Auto-generated method stub

	}
}
