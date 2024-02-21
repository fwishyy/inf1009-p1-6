package com.mygdx.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.Entity;

public class SeekBehaviour implements Behaviour{
	
	private Entity target;
	private float speed;
	
	public SeekBehaviour(Entity target, float speed) {
		this.target = target;
		this.speed = speed;
	}
	
	@Override
	public void update(Entity entity, float deltaTime) {
		Vector2 currEntityPos = entity.getVector2();
		Vector2 dir = new Vector2();
		dir.x = currEntityPos.x - target.getVector2().x;
		dir.y = currEntityPos.y - target.getVector2().y;
		dir = dir.nor();
		entity.getVector2().sub(dir.x * this.speed * deltaTime, dir.y * this.speed * deltaTime);
	}
	
//	public void setTarget(Vector2 targetPos) {
//		this.getVector2().= targetPos; 
//	}
	
}
