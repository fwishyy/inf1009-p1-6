package com.mygdx.engine.entity;

import com.badlogic.gdx.math.Rectangle;

public class Rect extends Rectangle {

    protected Rect() {
        super();
    }

    protected Rect(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    protected Rect(float width, float height) {
        super(0, 0, width, height);
    }

//	/**
//	 * Return: float[]<br>
//	 * Description: The array contains x,y coordinates Anti-clockwise direction starting from bottom left.
//	 */
//	protected float[] getCollisionBound() {
//		float bounds[] = {0,0,0,0};
//		bounds[0] = x - width/2; //start of width
//		bounds[1] = x + width/2; //end of width
//		bounds[2] = y - height/2; //start of height
//		bounds[3] = y + height/2; //end of height
//		return bounds;
//	}
//	

}
