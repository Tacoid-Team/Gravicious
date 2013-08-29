package com.tacoid.gravicious;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	ShapeRenderer shapeRenderer;
	private Color shapeFillColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);

	public Player() {
		shapeRenderer = new ShapeRenderer();
	}
	
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
    	batch.end();
	    	shapeRenderer.begin(ShapeType.FilledCircle);
		    	shapeRenderer.setColor(shapeFillColor);
		    	shapeRenderer.filledCircle(this.getX(), this.getY(), 20);
		    shapeRenderer.end();
	    batch.begin();
    }
	
}
