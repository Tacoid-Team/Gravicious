package com.tacoid.gravicious;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tacoid.gravicious.screens.EditorScreen;

public class Gravicious extends Game {
	
	private static Gravicious instance = null;
	
	public Skin globalSkin;
	
	public static Gravicious getInstance() {
		if(instance == null) {
			instance = new Gravicious();
		}
		return instance;
	}
	
	private Gravicious(){
	}

	@Override
	public void create() {
		globalSkin = new Skin(Gdx.files.internal("data/uiskin.json"));
		this.setScreen(EditorScreen.getInstance());
	}
	
}
