package com.mygdx.engine.entity;

import java.util.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.stream.Collectors;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class EntityManager {
	
	private List<Entity> entities;
	
	public EntityManager() {
		entities = new ArrayList<Entity>();
	}
	
	/**
	 * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
	 * @param <T> -- type must be a subclass of Entity
	 * @param entityCount -- the number of new entities to create (E.g 5)
	 * @param c -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
	 * @param texture -- path to texture. (E.g "badlogic.png")
	 * @param x -- x coordinate of obj (E.g 1.0f)
	 * @param y -- y coordinate of obj (E.g 1.0f)
	 * @param speed -- speed of obj (E.g 1.0f)
	 * @param type -- type of obj (E.g "monster")
	 */
	public <T extends Entity> void createEntity(int entityCount, Class<T> c, String texture, float x, float y, float speed, String type) {
		for(int i=0; i<entityCount; i++) {
			try {
				// Constructor arguments expected in the concrete class
				Constructor<T> constructor = c.getDeclaredConstructor(String.class, float.class, float.class, float.class, String.class);
				entities.add(constructor.newInstance(texture, x, y, speed, type));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("EntityManager.java: Error occurred when creating new entity.");
			}
		}
	}
	
	/**
	 * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
	 * @param <T> -- type must be a subclass of Entity
	 * @param entityCount -- the number of new entities to create (E.g 5)
	 * @param c -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
	 * @param x -- x coordinate of obj (E.g 1.0f)
	 * @param y -- y coordinate of obj (E.g 1.0f)
	 * @param speed -- speed of obj (E.g 1.0f)
	 * @param type -- type of obj (E.g "monster")
	 */
	public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y, float speed, String type) {
		for(int i=0; i<entityCount; i++) {
			try {
				// Constructor arguments expected in the concrete class
				Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class, float.class, String.class);
				entities.add(constructor.newInstance(x, y, speed, type));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("EntityManager.java: Error occurred when creating new entity.");
			}
		}
	}
	
	/**
	 * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
	 * @param <T> -- type must be a subclass of Entity
	 * @param entityCount -- the number of new entities to create (E.g 5)
	 * @param c -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
	 * @param x -- x coordinate of obj (E.g 1.0f)
	 * @param y -- y coordinate of obj (E.g 1.0f)
	 * @param type -- type of obj (E.g "monster")
	 */
	public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y, String type) {
		for(int i=0; i<entityCount; i++) {
			try {
				// Constructor arguments expected in the concrete class
				Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class, String.class);
				entities.add(constructor.newInstance(x, y, type));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("EntityManager.java: Error occurred when creating new entity.");
			}
		}
	}
	
	/**
	 * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
	 * @param <T> -- type must be a subclass of Entity
	 * @param entityCount -- the number of new entities to create (E.g 5)
	 * @param c -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
	 * @param x -- x coordinate of obj (E.g 1.0f)
	 * @param y -- y coordinate of obj (E.g 1.0f)
	 * @param speed -- speed of obj (E.g 1.0f)
	 */
	public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y, float speed) {
		for(int i=0; i<entityCount; i++) {
			try {
				// Constructor arguments expected in the concrete class
				Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class, float.class);
				entities.add(constructor.newInstance(x, y, speed));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("EntityManager.java: Error occurred when creating new entity.");
			}
		}
	}
	
	/**
	 * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
	 * @param <T> -- type must be a subclass of Entity
	 * @param entityCount -- the number of new entities to create (E.g 5)
	 * @param c -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
	 * @param x -- x coordinate of obj (E.g 1.0f)
	 * @param y -- y coordinate of obj (E.g 1.0f)
	 */
	public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y) {
		for(int i=0; i<entityCount; i++) {
			try {
				// Constructor arguments expected in the concrete class
				Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class);
				entities.add(constructor.newInstance(x, y));
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("EntityManager.java: Error occurred when creating new entity.");
			}
		}
	}
	
	/**
	 * Adds an entity object into the manager
	 * @param entity -- entity object to be added into the manager<br>
	 * preferred usage createEntity(new Entity()) or createEntity(entityObjName)
	 */
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	
	/**
	 * Replaces an old entity object, with a new entity object<br>
	 * @param newEntity -- object that replaces the old entity in the manager
	 * @param oldEntity -- object to be replaced in the manager
	 */
	public void updateEntity(Entity newEntity, Entity oldEntity) {
		for(int i=0; i<entities.size(); i++)
			if(oldEntity == entities.get(i))
				entities.set(i, newEntity);
	}
	
	/**
	 * Removes entity from manager
	 * @param entity -- The entity object to remove from the manager
	 */
	public void removeEntity(Entity entity) {
		for(int i=0; i<entities.size(); i++)
			if(entity == entities.get(i))
				entities.remove(i);
	}
	
	/**
	 * Get all entities in the manager
	 * @return List&lt;Entity&gt;
	 */
	public List<Entity> getEntities() {
		return entities;
	}
	
	/**
	 * Get entities of specified type that is within range of a target position of your choosing
	 * @param type -- entity type (E.g "monster")
	 * @param targetPosition -- position of a target x,y coordinate
	 * @param range -- range of detection (E.g 3.0f)
	 * @return List&lt;Entity&gt;
	 */
	public List<Entity> getEntities(String type, Vector2 targetPosition, float range) {
		List<Entity> retList = new ArrayList<Entity>();
		// filter for entities that matches the type
		List<Entity> filteredEntities = this.entities.stream()
				.filter(e -> e.getType() == type)
				.collect(Collectors.toList());
		// calculate distance of filtered entities from target position
		for(Entity e: filteredEntities) {
				Vector2 currEntPosition = e.getVector2();
				float distanceToTarget = currEntPosition.dst(targetPosition);
				if(distanceToTarget <= range)
					retList.add(e);
		}
			
		return retList;
	}
	
	/**
	 * Get all entities within range of a target position of your choosing
	 * @param targetPosition -- position of a target x,y coordinate
	 * @param range -- range of detection (E.g 3.0f)
	 * @return List&lt;Entity&gt;
	 */
	public List<Entity> getEntities(Vector2 targetPosition, float range) {
		List<Entity> retList = new ArrayList<Entity>();
		
		for(Entity e: this.entities) {
			Vector2 entityPosition = e.getVector2();
			float distanceToTarget = entityPosition.dst(targetPosition);
			if(distanceToTarget <= range)
				retList.add(e);
		}
			
		return retList;
	}
	
	/**
	 * Get all entities of specified type
	 * @param type -- entity type (E.g "monster")
	 * @return List&lt;Entity&gt;
	 */
	public List<Entity> getEntities(String type) {
		List<Entity> retList = new ArrayList<Entity>();
		for(Entity e: this.entities)
			if(e.getType() == type)
				retList.add(e);
		return retList;
	}
	
	/**
	 * Draws all entities in the manager
	 * @param batch -- SpriteBatch object
	 */
	public void draw(SpriteBatch batch) {
		for(Entity entity: entities)
			entity.draw(batch);
	}
	
	/**
	 * Draws only entities of specified type
	 * @param batch -- SpriteBatch object
	 * @param type -- entity type (E.g "monster")
	 */
	public void draw(SpriteBatch batch, String type) {
		for(Entity entity: entities)
			if(entity.getType() == type)
				entity.draw(batch);
	}
	
	/**
	 * Draws only entities that are within range of a specified target position
	 * @param batch -- SpriteBatch object
	 * @param targetPosition -- position of a target x,y coordinate
	 * @param range -- range of detection (E.g 3.0f)
	 */
	public void draw(SpriteBatch batch, Vector2 targetPosition, float range) {
		for(Entity entity: entities) {
			Vector2 currEntPos = entity.getVector2();
			if(currEntPos.dst(targetPosition) <= range)
				entity.draw(batch);
		}
	}
	
	/**
	 * Move all entities
	 */
	public void move() {
		for(Entity entity: entities)
			entity.move();
	}
}
