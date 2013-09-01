package com.tacoid.gravicious;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.tacoid.gravicious.elements.LevelElement;

public class Level extends Group{
	private Map<String, LevelElement> elements;

	private World world;

	public Level() {
		elements = new HashMap<String, LevelElement>();
		world = new World(new Vector2(0f,-100f), false);
	}

	public void addElement(LevelElement element) {
		element.setLevel(this);
		elements.put(element.getName(), element);
		System.out.println("Element " + element.getName() + " added.");
		refreshGroup();
	}

	public void removeElement(LevelElement element) {
		if(element != null) {
			element.setLevel(null);
			elements.remove(element.getName());
			System.out.println("Element " + element.getName() + " removed.");
			refreshGroup();
		}
	}

	public void update(float delta) {
		for(LevelElement e : elements.values()) {
			e.update(delta);
		}
	}

	public void refreshGroup() {
		this.clear();
		for(LevelElement e : elements.values()) {
			this.addActor(e.getActor());
		}	
	}

	public World getWorld() {
		return world;
	}
}
