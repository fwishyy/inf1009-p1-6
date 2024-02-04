package com.mygdx.engine.behaviour;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.entity.Entity;

public class BehaviourManager {
	
	private List<Entity> entities = new ArrayList<Entity>();
	private List<Entity> monsters = new ArrayList<Entity>();
	private Entity player = null;
	private List<Entity> environments = new ArrayList<Entity>();
	
	public BehaviourManager(List<Entity> entities) {
		this.entities = entities;
		setBehaviour(entities);
	}
	
	public BehaviourManager(Entity entity) {
		setBehaviour(entity, entity.getType());
	}
	
	public void updateMovement() {
		monsterMovement1();
	}
	
	public void setBehaviour(Entity entity, String type) {
		
		this.entities.add(entity);
		
		for(Entity e: this.entities) {
			switch(e.getType()) {
				case "player":
					this.player = e;
				case "monster":
					this.monsters.add(e);
				default:
					this.environments.add(e);
			}
		}
	}
	
	public void setBehaviour(List<Entity> entities) {
		
		for(Entity e: entities) {
			switch(e.getType()) {
				case "player":
					this.player = e;
				case "monster":
					this.monsters.add(e);
				default:
					this.environments.add(e);
			}
		}
	}
	
	private void monsterMovement1() {
		for(Entity monster: monsters) {
			Vector2 v2_monster = monster.getVector2();
			Vector2 v2_player = this.player.getVector2();
			Vector2 dir = new Vector2();
			dir.x = v2_monster.x - v2_player.x;
			dir.y = v2_monster.y - v2_player.y;
			dir = dir.nor();
			monster.getVector2().sub(dir.x * monster.getSpeed() * Gdx.graphics.getDeltaTime(), dir.y * monster.getSpeed() * Gdx.graphics.getDeltaTime());
			
			
		}
	}
}
