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
	
	private float radius = 20;
	
	private final float maxAngularSpeed = 0.04f;
	private float angle;
	private float angularSpeed;

	public Player(World world) {
		shapeRenderer = new ShapeRenderer();
		BodyDef bodyDef = new BodyDef();  
		bodyDef.type = BodyType.DynamicBody;  
		bodyDef.position.set(10,60);  
		body = world.createBody(bodyDef);  
		CircleShape dynamicCircle = new CircleShape();
		body.setUserData(this);
		dynamicCircle.setRadius(UnitConverter.PixelToMeters(radius));  
		FixtureDef fixtureDef = new FixtureDef();  
		fixtureDef.shape = dynamicCircle;  
		fixtureDef.density = 2f;  
		fixtureDef.friction = 0.0f;  
		fixtureDef.restitution = 1;  
		body.createFixture(fixtureDef); 
		body.setUserData(this);
		
		setAngle(0.0f);
		setAngularSpeed(0.0f);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		shapeRenderer.setProjectionMatrix(getStage().getCamera().combined);
		batch.end();
			shapeRenderer.begin(ShapeType.FilledCircle);
			shapeRenderer.setColor(shapeFillColor);
			shapeRenderer.filledCircle(getX(),getY(), radius );
			shapeRenderer.end();
		batch.begin();
	}

	@Override
	public float getX() {
		return UnitConverter.MetersToPixels(body.getPosition().x) ;
	}

	@Override
	public float getY() {
		return UnitConverter.MetersToPixels(body.getPosition().y);
	}
	
	public void setX(float x) {
		body.setTransform(UnitConverter.PixelToMeters(x), body.getPosition().y, body.getAngle());
	}
	
	public void setY(float y) {
		body.setTransform(body.getPosition().x, UnitConverter.PixelToMeters(y), body.getAngle());
	}
	
	public Body getBody() {
		return body;
	}

	public float getMaxAngularSpeed() {
		return maxAngularSpeed;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getAngularSpeed() {
		return angularSpeed;
	}

	public void setAngularSpeed(float angularSpeed) {
		this.angularSpeed = angularSpeed;
	}
	
	public void speedUp(float direction) {
		angularSpeed += direction * 0.001f;
		if(Math.abs(angularSpeed) > maxAngularSpeed)
			angularSpeed = maxAngularSpeed * direction;
	}
	public void speedDown() {
		angularSpeed *= 0.9f;
	}
}
