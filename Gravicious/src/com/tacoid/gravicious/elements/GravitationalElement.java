package com.tacoid.gravicious.elements;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tacoid.gravicious.Gravicious;

public abstract class GravitationalElement extends LevelElement {

	float influenceRadius = 100.0f;
	float gravity = 100.0f;


	float radius = 50.0f;

	Table table;

	private class RadiusButton extends TextButton {
		private float increment;
		public RadiusButton(float inc, String string, final Slider slider) {
			super(string, Gravicious.getInstance().globalSkin);
			this.increment = inc;
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setRadius(getRadius()+increment);
					slider.setValue(getRadius());
				}
			});
		}
	}

	private class RadiusSlider extends Slider {
		public RadiusSlider(float min, float max) {
			super(min, max, 1.0f, false, Gravicious.getInstance().globalSkin);
			addListener(new ChangeListener() {
				@Override
				public void changed (ChangeEvent event, Actor actor){
					setRadius(((Slider)actor).getValue());
				}
			});
		}
	}

	private class InfluenceButton extends TextButton {
		private float increment;
		public InfluenceButton(float inc, String string, final Slider slider) {
			super(string, Gravicious.getInstance().globalSkin);
			this.increment = inc;
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setInfluenceRadius(getInfluenceRadius()+increment);
					slider.setValue(getInfluenceRadius());
				}
			});
		}
	}

	private class InfluenceSlider extends Slider {
		public InfluenceSlider(float min, float max) {
			super(min, max, 1.0f, false, Gravicious.getInstance().globalSkin);
			addListener(new ChangeListener() {
				@Override
				public void changed (ChangeEvent event, Actor actor){
					setInfluenceRadius(((Slider)actor).getValue());
					setValue(getInfluenceRadius());
				}
			});
		}
	}
	
	protected GravitationalElement(String name) {
		super(name);
		table = new Table();
		table.setFillParent(true);
		table.right().bottom();
		table.add(new Label("radius", Gravicious.getInstance().globalSkin));
		RadiusSlider radius_slider = new RadiusSlider(40, 300);
		radius_slider.setValue(getRadius());
		table.add(radius_slider);
		table.add(new RadiusButton(-1, "-", radius_slider));
		table.add(new RadiusButton(1, "+", radius_slider));
		table.row();
		table.add(new Label("Influence", Gravicious.getInstance().globalSkin));
		InfluenceSlider influence_slider = new InfluenceSlider(getRadius(), getRadius()*8);
		influence_slider.setValue(getInfluenceRadius());
		table.add(influence_slider);
		table.add(new InfluenceButton(-1, "-", influence_slider));
		table.add(new InfluenceButton(1, "+", influence_slider));

	}

	private void setRadius(float radius) {
		if (radius > 0.0f)
			this.radius = radius;
		if(actor!=null)
			actor.updateBody();
	}

	public float getRadius() {
		return radius;
	}
	
	public float getInfluenceRadius() {
		return influenceRadius;
	}

	public void setInfluenceRadius(float influenceRadius) {
		if(influenceRadius >= this.radius)
			this.influenceRadius = influenceRadius;
		else 
			this.influenceRadius = this.radius;
	}
	

	public float getGravity() {
		return gravity;
	}


	@Override
	public Table getWidget() {
		return table;
	}
	
	public Vector2 computeAttraction(Vector2 pos, float mass) {
		float grav = getGravity(); /* XXX */
		Vector2 d = new Vector2(getX() - pos.x, getY() - pos.y);
		float force = mass * grav;
		d.nor();
		d.mul(force);
		return d;
	}

}
