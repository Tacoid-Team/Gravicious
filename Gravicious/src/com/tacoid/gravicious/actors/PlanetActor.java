package com.tacoid.gravicious.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.tacoid.gravicious.elements.Planet;

public class PlanetActor extends ElementActor {
    public Rectangle area;
    public ShapeRenderer shapeRenderer;
    public Planet planet;

    public PlanetActor(Planet p) {
    	super(p);
        shapeRenderer = new ShapeRenderer();
        planet = p;
        setX(100);
        setY(100);

    }

    private Color getColorFromGravity() {
    	float scale = planet.getGravity() / 50.0f;
    	float r = 0.25f + 0.05f * scale;
    	float g = 0.61f - 0.30f * scale;
    	return new Color(r, g, 0.0f, 0.0f);
    }
    
    @Override
    public void draw(SpriteBatch batch, float parentAlpha) {
    	batch.end();
	    	shapeRenderer.begin(ShapeType.FilledCircle);
		    	shapeRenderer.setColor(getColorFromGravity());
		    	shapeRenderer.filledCircle(this.getX(), this.getY(), planet.getRadius());
		    shapeRenderer.end();
	    batch.begin();
    }
    
    @Override
    public Actor hit(float x, float y, boolean touchable) {
		if (touchable && this.getTouchable() != Touchable.enabled) return null;

	    double distance = x*x + y*y;
	    if (distance <= planet.getRadius()*planet.getRadius()){
	        return this;
	    } else {
	    	return null;
	    }
    }
}
