package com.tacoid.gravicious.elements;

import com.tacoid.gravicious.actors.SunActor;

public class Sun extends GravitationalElement {

	public Sun() {
		super("sun");
		super.setActor(new SunActor(this));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}
}
