package com.tacoid.gravicious.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tacoid.gravicious.elements.Star;

public class StarActor extends ElementActor {

	private ShapeRenderer shapeRenderer;
	private Color shapeFillColor = new Color(0.50f, 0.50f, 1.0f, 0.0f);
	
	public StarActor(Star element) {
		super(element);
		shapeRenderer = new ShapeRenderer();
        setX(100);
        setY(100);
	}
	
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
    	batch.end();
	    	shapeRenderer.begin(ShapeType.FilledRectangle);
		    	shapeRenderer.setColor(shapeFillColor);
		    	shapeRenderer.filledRect(getX()-30, getY()-30, 60, 60);
		    shapeRenderer.end();
	    batch.begin();
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;
		
		double distance = x*x + y*y;
		if (distance <= 50*50){
		    return this;
		} else {
			return null;
		}
    }

}
