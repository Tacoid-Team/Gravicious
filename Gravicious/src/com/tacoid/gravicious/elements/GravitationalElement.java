package com.tacoid.gravicious.elements;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.Gravicious;

public abstract class GravitationalElement extends LevelElement {

	float radius = 100.0f;
	float force;
	
	Table table;
	
	private class RadiusButton extends TextButton {
		private float increment;
		public RadiusButton(float inc, String string) {
			super(string, Gravicious.getInstance().globalSkin);
			this.increment = inc;
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					radius+=increment;
				}
			});
		}
		
	}

	protected GravitationalElement(String name) {
		super(name);
		table = new Table();
		table.setFillParent(true);
		table.right().bottom();
		table.add(new RadiusButton(10, "+"));
		table.add(new RadiusButton(-10, "-"));
	}
	
	public float getRadius() {
		return radius;
	}

	@Override
	public Table getWidget() {
		return table;
	}

}
