package com.tacoid.gravicious;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

public class Level extends Group{
	public Map<String, LevelElement> elements;
	
	public LevelElement selectedElement; /* Editor feature */
	private Selector selector;
	
	private class Selector extends Actor {
	    private Color shapeFillColor = new Color(1.0f, 0.0f, 0.0f ,1.0f);
	    public ShapeRenderer shapeRenderer;
	    private LevelElement e;

	    public Selector() {
	        shapeRenderer = new ShapeRenderer();
	    }
	    
	    public void setElement(LevelElement e) {
	    	this.e = e;
	    }

	    @Override
	    public void draw(SpriteBatch batch, float parentAlpha) {
	    	if(e!=null) {
		    	batch.end();
			    	shapeRenderer.begin(ShapeType.FilledCircle);
				    	shapeRenderer.setColor(shapeFillColor);
				    	shapeRenderer.filledCircle(e.getX(), e.getY(), 10);
				    shapeRenderer.end();
			    batch.begin();
	    	}
	    }
	}
	

	public Level() {
		elements = new HashMap<String, LevelElement>();
		selector = new Selector();
		selector.setVisible(false);
	}

	public void addElement(LevelElement element) {
		element.setLevel(this);
		elements.put(element.getName(), element);
		System.out.println("Element " + element.getName() + " added.");
		refreshGroup();
	}
	
	public void removeElement(LevelElement element) {
		if(element != null) {
			element.setLevel(null);
			elements.remove(element.getName());
			
			/* On part du principe que l'élément qu'on supprime est très certainement celui selectionné, si c'est pas vrai ben... tant pis :D */
			selector.setElement(null);
			selector.setVisible(false);
			System.out.println("Element " + element.getName() + " removed.");
			refreshGroup();
		}
	}
	
	public void refreshGroup() {
		this.clear();
		for(LevelElement e : elements.values()) {
			this.addActor(e.getActor());
		}	
		addActor(selector);
	}
	
	public LevelElement getSelectedElement() {
		return selectedElement;
	}

	public void setSelectedElement(LevelElement selectedElement) {
		this.selectedElement = selectedElement;
		System.out.println("Element " + selectedElement.getName() + " selected.");
		selector.setVisible(true);
		selector.setElement(selectedElement);
		
	}

}
