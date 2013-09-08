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

	float M = 100f; // Constante universelle G * masse. Enfin, c'est complètement arbitraire hein.
	float gravity = 1.0f;
	float radius = 10.0f;

	Table table;

	private class RadiusButton extends TextButton {
		private float increment;
		public RadiusButton(float inc, String string) {
			super(string, Gravicious.getInstance().globalSkin);
			this.increment = inc;
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setRadius(getRadius()+increment);
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

	private class GravityButton extends TextButton {
		private float increment;
		public GravityButton(float inc, String string) {
			super(string, Gravicious.getInstance().globalSkin);
			this.increment = inc;
			addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setGravity(getGravity()+increment);
				}
			});
		}
	}

	private class GravitySlider extends Slider {
		public GravitySlider(float min, float max) {
			super(min, max, 1.0f, false, Gravicious.getInstance().globalSkin);
			addListener(new ChangeListener() {
				@Override
				public void changed (ChangeEvent event, Actor actor){
					setGravity(((Slider)actor).getValue());
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
		RadiusSlider radius_slider = new RadiusSlider(1, 30);
		radius_slider.setValue(getRadius());
		table.add(radius_slider);
		table.add(new RadiusButton(-1, "-"));
		table.add(new RadiusButton(1, "+"));
		table.row();
		table.add(new Label("gravity", Gravicious.getInstance().globalSkin));
		GravitySlider gravity_slider = new GravitySlider(1, 5);
		gravity_slider.setValue(getGravity());
		table.add(gravity_slider);
		table.add(new GravityButton(-1, "-"));
		table.add(new GravityButton(1, "+"));

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

	private void setGravity(float gravity) {
		if (gravity > 0.0f)
			this.gravity = gravity;  
	}

	public float getGravity() {
		return gravity;
	}
	
	public float getInfluenceRadius(float mass) {
		float s = 20f; // Force à partir de laquelle on passe l'attraction à 0.
		return (float) Math.sqrt(M * mass * gravity / s);
	}

	@Override
	public Table getWidget() {
		return table;
	}
	
	public Vector2 computeAttraction(Vector2 pos, float mass) {
		float grav = getGravity();
		Vector2 d = new Vector2(getX() / 10 - pos.x, getY() / 10 - pos.y);
		// XXX: j'aime pas devoir faire ces conversions partout. Je veux une méthode qui me donne direct le bon nombre.
		if(d.len() < getInfluenceRadius(mass)) {
			float force = M * mass * grav / d.len2();
//			System.out.println("f" + force);
			d.nor();
			d.mul(force);
//			System.out.println(d);
			return d;
		} else {
//			System.out.println("null");
			return null;
		}
	}

}
