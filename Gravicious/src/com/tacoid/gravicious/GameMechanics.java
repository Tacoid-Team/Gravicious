package com.tacoid.gravicious;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tacoid.gravicious.elements.GravitationalElement;
import com.tacoid.gravicious.elements.Planet;

public class GameMechanics implements ContactListener{
	
	public enum GameState {
		IDLE,
		PAUSED,
		RUNNING
	};
	
	public enum PlayerState {
		WALKING,
		FLYING
	}
	
	private Level level;
	private Player player;
	
	private GameState gameState;
	private PlayerState playerState;
	private GravitationalElement walkedElement;
	
	public GameMechanics() {
		
		gameState = GameState.IDLE;
		
	}
	
	public void init(Level level) {
		this.level = level;
		player = new Player(level.getWorld());
		player.setX(400);
		player.setY(400);
		gameState = GameState.RUNNING;
		playerState = PlayerState.FLYING;
		level.getWorld().setContactListener(this);
	}
	
	public void start() {
		gameState = GameState.RUNNING;
	}
	
	public void update(float delta) {
		
		if(gameState == GameState.RUNNING) {
			level.update(delta);
			if(playerState == PlayerState.FLYING) {
				level.getWorld().step(delta, 4, 4);
			} else {
				
			}
		}
	}
	
	public void jump() {
		
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public Player getPlayer() {
		return player;
	}

	@Override
	public void beginContact(Contact contact) {
		Body bodyA = contact.getFixtureA().getBody();
		Body bodyB = contact.getFixtureB().getBody();

		if (bodyA.getUserData() instanceof Planet
				|| bodyB.getUserData() instanceof Planet) {
			System.out.println("Grounded");
			Planet planet;
			if(bodyA.getUserData() instanceof Planet) {
				planet = (Planet) bodyA.getUserData();
			} else {
				planet = (Planet) bodyB.getUserData();
			}
			
			playerState = PlayerState.WALKING;
			walkedElement = planet;
		}
	}

	@Override
	public void endContact(Contact contact) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub
		
	}

}
