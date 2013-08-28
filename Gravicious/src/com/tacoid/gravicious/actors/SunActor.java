package com.tacoid.gravicious.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tacoid.gravicious.Sun;

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
    	batch.end();
	    	shapeRenderer.begin(ShapeType.FilledCircle);
		    	shapeRenderer.setColor(shapeFillColor);
		    	shapeRenderer.filledCircle(this.getX(), this.getY(), sun.getRadius());
		    shapeRenderer.end();
	    batch.begin();
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;
		
		double distance = x*x + y*y;
		if (distance <= sun.getRadius()*sun.getRadius()){
		    return this;
		} else {
			return null;
		}
    }
}
