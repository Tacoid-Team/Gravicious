package com.tacoid.gravicious.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tacoid.gravicious.Gravicious;
import com.tacoid.gravicious.elements.Sun;
import com.tacoid.gravicious.screens.AbstractGameScreen;

public class SunActor extends ElementActor{
	private Color shapeFillColor = new Color(0.80f, 0.80f, 0.0f, 0.0f);
	public Rectangle area;
	public ShapeRenderer shapeRenderer;
	public Sun sun;

	public SunActor(Sun s) {
		super(s);
		shapeRenderer = new ShapeRenderer();
		sun = s;
		setX(100);
		setY(100);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
		batch.end();
			shapeRenderer.begin(ShapeType.FilledCircle);
				shapeRenderer.setColor(shapeFillColor);
				shapeRenderer.filledCircle(this.getX(), this.getY(), sun.getRadius());
			shapeRenderer.end();
			shapeRenderer.begin(ShapeType.Circle);
				shapeRenderer.setColor(Color.RED);
				shapeRenderer.circle(this.getX(), this.getY(), sun.getInfluenceRadius());
			shapeRenderer.end();
		batch.begin();
	}

	@Override
	public Actor hit(float x, float y, boolean touchable) {
		AbstractGameScreen screen = (AbstractGameScreen)Gravicious.getInstance().getScreen();

		if(!screen.isEditor()) return null;
		if (touchable && this.getTouchable() != Touchable.enabled) return null;

		double distance = x*x + y*y;
		if (distance <= sun.getRadius()*sun.getRadius()){
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
