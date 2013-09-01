package com.tacoid.gravicious.elements;

import com.tacoid.gravicious.actors.PlanetActor;

public class Planet extends GravitationalElement {

	public Planet() {
		super("planet");
		super.setActor(new PlanetActor(this));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}
}
