package com.tacoid.gravicious.elements;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tacoid.gravicious.actors.StarActor;

public class Star extends LevelElement {

	public Star() {
		super("star");
		super.setActor(new StarActor(this));
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public Table getWidget() {
		// TODO Auto-generated method stub
		return null;
	}

}
