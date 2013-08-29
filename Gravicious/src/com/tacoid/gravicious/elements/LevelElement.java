package com.tacoid.gravicious.elements;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tacoid.gravicious.Level;
import com.tacoid.gravicious.actors.ElementActor;

public abstract class LevelElement {
	static private int id = 0;
	
	protected ElementActor actor;
	private String name;

	private Level level;
	
	protected LevelElement(String name) {
		this.name = name+"_"+id;
		id++;
	}
	
	public abstract void update(float delta);
	public abstract Table getWidget();

	public void setActor(ElementActor a) {
		actor = a;
	}
	
	public ElementActor getActor() {
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
		actor.createBody(level.getWorld());
	}
	
	public Level getLevel() {
		return level;
	}
	
	
}
