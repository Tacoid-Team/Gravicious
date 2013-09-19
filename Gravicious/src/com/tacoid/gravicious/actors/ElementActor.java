package com.tacoid.gravicious.actors;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.elements.LevelElement;
import com.tacoid.gravicious.screens.EditorScreen;

public abstract class ElementActor extends Actor {

	protected LevelElement element;
	protected Body body;

	abstract public void createBody(World world);
	abstract public void updateBody();

	public ElementActor(final LevelElement element) {

		this.addListener(new ClickListener() {
			float xoff;
			float yoff;
			@Override
			public void touchUp (InputEvent event, float x, float y, int pointer, int button) {

			}
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				xoff = x;
				yoff = y;

				if(element.getLevel()!=null) {
					EditorScreen.getInstance().setSelectedElement(element);
				}

				return true;
			}

			@Override
			public void touchDragged(InputEvent event, float x, float y,
					int pointer) {
				setX(getX()-xoff+x);
				setY(getY()-yoff+y);
				super.touchDragged(event, x, y, pointer);
			}
		});
	}
}
