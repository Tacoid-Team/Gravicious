package com.tacoid.gravicious;

import com.tacoid.gravicious.actors.SunActor;

public class Sun extends LevelElement {
		
	private float radius = 200.0f;
	
	public Sun() {
		super("sun");
		super.setActor(new SunActor(this));
	}

	public float getRadius() {
		return radius;
	}

}
