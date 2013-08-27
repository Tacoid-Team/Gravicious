package com.tacoid.gravicious;

import com.tacoid.gravicious.actors.PlanetActor;

public class Planet extends LevelElement {
	
	float radius = 100.0f;
	
	public Planet() {
		super("planet");
		super.setActor(new PlanetActor(this));
	}

	public float getRadius() {
		return radius;
	}

}
