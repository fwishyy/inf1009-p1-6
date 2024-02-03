package com.mygdx.engine.entity;

import java.util.*;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	
	private List<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	public void createEntity(Entity entity) {
		entities.add(entity);
	}
	
	public void updateEntity() {
		
	}
	
	public void removeEntity() {
		
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public void draw(SpriteBatch batch) {
		for(Entity entity: entities) {
			entity.draw(batch);
		}
	}
}
