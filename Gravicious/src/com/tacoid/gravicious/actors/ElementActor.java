package com.tacoid.gravicious.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.LevelElement;

public class ElementActor extends Actor {
	
	protected LevelElement element;
	
	public ElementActor(final LevelElement element) {
        this.addListener(new ClickListener() {
        	float xoff;
        	float yoff;
        	@Override
        	public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
        		if(element.getLevel()!=null) {
        			element.getLevel().setSelectedElement(element);
        		}
        	}
        	@Override
        	public boolean touchDown(InputEvent event, float x, float y,
        			int pointer, int button) {
        		xoff = x;
        		yoff = y;

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
