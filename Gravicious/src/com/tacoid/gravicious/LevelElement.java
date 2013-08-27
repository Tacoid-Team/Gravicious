package com.tacoid.gravicious;

import com.badlogic.gdx.scenes.scene2d.Actor;

public class LevelElement {
	static private int id = 0;
	
	private Actor actor;
	private String name;

	private Level level;
	
	LevelElement(String name) {
		this.name = name+"_"+id;
		id++;
	}

	public void setActor(Actor a) {
		actor = a;
	}
	
	public Actor getActor() {
		return actor;
	}
	
	public float getX() {
		return actor.getX();
	}
	
	public float getY() {
		return actor.getY();
	}
	
	public String getName() {
		return name;
	}
	
	public void setLevel(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	
}
