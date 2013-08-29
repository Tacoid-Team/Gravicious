package com.tacoid.gravicious;

public class GameMechanics {
	
	public enum GameState {
		IDLE,
		PAUSED,
		RUNNING
	};
	
	private Level level;
	private Player player;
	private GameState gameState;
	
	public GameMechanics() {
		player = new Player();
		gameState = GameState.IDLE;
	}
	
	public void init(Level level) {
		this.level = level;
		player.setX(400);
		player.setY(400);
		gameState = GameState.IDLE;
	}
	
	public void start() {
		gameState = GameState.RUNNING;
	}
	
	public void update(float delta) {
		if(gameState == GameState.RUNNING) {
			level.update(delta);
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

}
