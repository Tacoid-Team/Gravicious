package com.tacoid.gravicious.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tacoid.gravicious.actors.StartActor;

public class Start extends LevelElement {

	public Start() {
		super("Start");
		super.setActor(new StartActor(this));
	}

	@Override
	public void update(float delta) {
	}

	@Override
	public Table getWidget() {
		return null;
	}

}
