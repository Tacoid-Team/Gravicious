package com.tacoid.gravicious;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Player extends Actor {
	ShapeRenderer shapeRenderer;
	private Color shapeFillColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
	private Body body;
	
	private float radius = 2;

	public Player(World world) {
		shapeRenderer = new ShapeRenderer();
		BodyDef bodyDef = new BodyDef();  
		bodyDef.type = BodyType.DynamicBody;  
		bodyDef.position.set(10,60);  
		body = world.createBody(bodyDef);  
		CircleShape dynamicCircle = new CircleShape();
		body.setUserData(this);
		dynamicCircle.setRadius(radius);  
		FixtureDef fixtureDef = new FixtureDef();  
		fixtureDef.shape = dynamicCircle;  
		fixtureDef.density = 2f;  
		fixtureDef.friction = 0.0f;  
		fixtureDef.restitution = 1;  
		body.createFixture(fixtureDef); 
		body.setUserData(this);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
		batch.end();
			shapeRenderer.begin(ShapeType.FilledCircle);
			shapeRenderer.setColor(shapeFillColor);
			shapeRenderer.filledCircle(this.getX(), this.getY(), radius * 10);
			shapeRenderer.end();
		batch.begin();
	}

	@Override
	public float getX() {
		return body.getPosition().x * 10;
	}

	@Override
	public float getY() {
		return body.getPosition().y * 10;
	}
	
	public void setX(float x) {
		body.setTransform(x / 10, body.getPosition().y, body.getAngle());
	}
	
	public void setY(float y) {
		body.setTransform(body.getPosition().x, y / 10, body.getAngle());
	}
	
	public Body getBody() {
		return body;
	}
}
