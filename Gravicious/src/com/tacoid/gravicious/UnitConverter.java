package com.tacoid.gravicious;

import com.badlogic.gdx.math.Vector2;

public class UnitConverter {
	private final static float PTM_RATIO = 40.0f;
	
	public static float PixelToMeters(float pixels) {
		return pixels / PTM_RATIO;
	}
	
	public static Vector2 PixelToMetersV(Vector2 pixels) {
		Vector2 v = pixels.cpy();
		return v.div(PTM_RATIO);
	}
	
	public static float MetersToPixels(float meters) {
		return meters * PTM_RATIO;
	}
	
	public static Vector2 MetersToPixelsV(Vector2 meters) {
		Vector2 v = meters.cpy();
		return v.mul(PTM_RATIO);
	}
		
			
	private UnitConverter() {
	}

}
