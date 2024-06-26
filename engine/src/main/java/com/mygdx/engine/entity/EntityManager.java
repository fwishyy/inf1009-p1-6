package com.mygdx.engine.entity;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventBus;
import com.mygdx.engine.utils.EventListener;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager extends Manager {

    private LinkedHashMap<String, List<Entity>> entityMap;

    public EntityManager() {
        this.entityMap = new LinkedHashMap<>();
        this.entityMap = new LinkedHashMap<>();
        addEntityDisposedListener(new EventListener<EntityDisposedEvent>() {
            @Override
            public void onSignal(Event e) {
                handleEntityDisposed((EntityDisposedEvent) e);
            }
        });

        addEntityAddedListener(new EventListener<EntityAddedEvent>() {
            @Override
            public void onSignal(Event e) {
                handleEntityAdded((EntityAddedEvent) e);
            }
        });
    }

    public EntityManager(List<Entity> entityList, LinkedHashMap<String, List<Entity>> entityMap) {
        this();
        this.entityMap = entityMap;
    }

    /**
     * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
     *
     * @param <T>         -- type must be a subclass of Entity
     * @param entityCount -- the number of new entities to create (E.g 5)
     * @param c           -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
     * @param texture     -- path to texture. (E.g "badlogic.png")
     * @param x           -- x coordinate of obj (E.g 1.0f)
     * @param y           -- y coordinate of obj (E.g 1.0f)
     * @param type        -- type of obj (E.g "monster")
     */
    public <T extends Entity> void createEntity(int entityCount, Class<T> c, String texture, float x, float y, String type) {
        for (int i = 0; i < entityCount; i++) {
            try {
                // Constructor arguments expected in the concrete class
                Constructor<T> constructor = c.getDeclaredConstructor(String.class, float.class, float.class, String.class);
                Entity en = constructor.newInstance(texture, x, y, type);
                addEntity(en);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                     IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("EntityManager.java: Error occurred when creating new entity.");
            }
        }
    }

    /**
     * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
     *
     * @param <T>           -- type must be a subclass of Entity
     * @param entityCount   -- the number of new entities to create (E.g 5)
     * @param c             -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
     * @param texture       -- path to texture. (E.g "badlogic.png")
     * @param x             -- x coordinate of obj (E.g 1.0f)
     * @param y             -- y coordinate of obj (E.g 1.0f)
     * @param type          -- type of obj (E.g "monster")
     * @param frameCount    -- number of frames present in the picture
     * @param frameDuration -- Duration between each frame (in seconds)
     */
    public <T extends AnimatedEntity> void createEntity(int entityCount, Class<T> c, String texture, float x, float y, String type, int frameCountRow, int frameCountColumn, float frameDuration) {
        for (int i = 0; i < entityCount; i++) {
            try {
                // Constructor arguments expected in the concrete class
                Constructor<T> constructor = c.getDeclaredConstructor(String.class, float.class, float.class, String.class, int.class, int.class, float.class);
                Entity en = constructor.newInstance(texture, x, y, type, frameCountRow, frameCountColumn, frameDuration);
                addEntity(en);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                     IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("EntityManager.java: Error occurred when creating new entity.");
            }
        }
    }

    /**
     * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
     *
     * @param <T>         -- type must be a subclass of Entity
     * @param entityCount -- the number of new entities to create (E.g 5)
     * @param c           -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
     * @param x           -- x coordinate of obj (E.g 1.0f)
     * @param y           -- y coordinate of obj (E.g 1.0f)
     * @param type        -- type of obj (E.g "monster")
     */
    public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y, String type) {
        for (int i = 0; i < entityCount; i++) {
            try {
                // Constructor arguments expected in the concrete class
                Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class, String.class);
                Entity en = constructor.newInstance(x, y, type);
                addEntity(en);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                     IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("EntityManager.java: Error occurred when creating new entity.");
            }
        }
    }

    /**
     * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
     *
     * @param <T>         -- type must be a subclass of Entity
     * @param entityCount -- the number of new entities to create (E.g 5)
     * @param c           -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
     * @param x           -- x coordinate of obj (E.g 1.0f)
     * @param y           -- y coordinate of obj (E.g 1.0f)
     */
    public <T extends Entity> void createEntity(int entityCount, Class<T> c, float x, float y) {
        for (int i = 0; i < entityCount; i++) {
            try {
                // Constructor arguments expected in the concrete class
                Constructor<T> constructor = c.getDeclaredConstructor(float.class, float.class);
                Entity en = constructor.newInstance(x, y);
                addEntity(en);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                     IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("EntityManager.java: Error occurred when creating new entity.");
            }
        }
    }

    /**
     * Creates a new entity on concrete class created via reflection. Concrete class must be able to handle the arguments given for the constructor
     *
     * @param <T>         -- type must be a subclass of Entity
     * @param entityCount -- the number of new entities to create (E.g 5)
     * @param c           -- the concrete class that inherits from Entity Class to be created and added to the manager (E.g Player.class)
     * @param texture     -- path to texture. (E.g "badlogic.png")
     */
    public <T extends Entity> void createEntity(int entityCount, Class<T> c, String texture) {
        for (int i = 0; i < entityCount; i++) {
            try {
                // Constructor arguments expected in the concrete class
                Constructor<T> constructor = c.getDeclaredConstructor(String.class);
                Entity en = constructor.newInstance(texture);
                addEntity(en);
            } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException |
                     IllegalArgumentException | InvocationTargetException e) {
                e.printStackTrace();
                System.out.println("EntityManager.java: Error occurred when creating new entity.");
            }
        }
    }

    /**
     * Adds an entity object into the manager
     *
     * @param entity -- entity object to be added into the manager<br>
     *               preferred usage createEntity(new Entity()) or createEntity(entityObjName)
     */
    public void addEntity(Entity entity) {
        this.entityMap.putIfAbsent(entity.getType(), new ArrayList<>());
        this.entityMap.get(entity.getType()).add(entity);
//        System.out.println("added " + entity.getType());
    }

    /**
     * Replaces an old entity object, with a new entity object<br>
     *
     * @param newEntity -- object that replaces the old entity in the manager
     * @param oldEntity -- object to be replaced in the manager
     */
    public void replaceEntity(Entity newEntity, Entity oldEntity) {
        if (newEntity.getType() != oldEntity.getType())
            throw new IllegalArgumentException("new entity and old entity types must be the same");


        disposeEntities(oldEntity.getType());
        this.entityMap.remove(oldEntity.getType());
        addEntity(newEntity);
    }

    /**
     * Removes entity from manager
     *
     * @param entity -- The entity object to remove from the manager
     */
    public void removeEntity(Entity entity) {
        this.entityMap.get(entity.getType()).remove(entity);
    }

    /**
     * Get all entities in the manager
     *
     * @return List&lt;Entity&gt;
     */
    public List<Entity> getEntities() {
        return getAllEntities();
    }

    /**
     * Get entities of specified type that is within range of a target position of your choosing
     *
     * @param type           -- entity type (E.g "monster")
     * @param targetPosition -- position of a target x,y coordinate
     * @param range          -- range of detection (E.g 3.0f)
     * @return List&lt;Entity&gt;
     */
    public List<Entity> getEntities(String type, Vector2 targetPosition, float range) {
        List<Entity> retList = new ArrayList<Entity>();
        // filter for entities that matches the type
        List<Entity> filteredEntities = this.entityMap.get(type);
        // calculate distance of filtered entities from target position
        for (Entity e : filteredEntities) {
            Vector2 currEntPosition = e.getVector2();
            float distanceToTarget = currEntPosition.dst(targetPosition);
            if (distanceToTarget <= range) retList.add(e);
        }

        return retList;
    }

    /**
     * Get all entities within range of a target position of your choosing
     *
     * @param targetPosition -- position of a target x,y coordinate
     * @param range          -- range of detection (E.g 3.0f)
     * @return List&lt;Entity&gt;
     */
    public List<Entity> getEntities(Vector2 targetPosition, float range) {
        List<Entity> retList = new ArrayList<>();

        for (Entity e : getAllEntities()) {
            Vector2 entityPosition = e.getVector2();
            float distanceToTarget = entityPosition.dst(targetPosition);
            if (distanceToTarget <= range) retList.add(e);
        }

        return retList;
    }

    /**
     * Get all entities of specified type
     *
     * @param type -- entity type (E.g "monster")
     * @return List&lt;Entity&gt;
     */
    public List<Entity> getEntities(String type) {
        return entityMap.get(type);
    }

    /**
     * Entity with a unique tag will be stored as the first element in the list.
     * Optionally, this also allows user to retrieve the first
     * stored instance of that entity if there is more than 1.
     *
     * @param type -- entity type (E.g "monster")
     * @return Entity
     */
    public Entity getEntity(String type) {
        return entityMap.get(type).get(0);
    }

    /**
     * Draws all entities in the manager
     *
     * @param batch -- SpriteBatch object
     */
    public void draw(SpriteBatch batch, ShapeRenderer shape) {
        for (Entity entity : getAllEntities()) {
            entity.draw(batch, shape);
        }

    }

    /**
     * Draws only entities of specified type
     *
     * @param batch -- SpriteBatch object
     * @param type  -- entity type (E.g "monster")
     */
    public void draw(SpriteBatch batch, ShapeRenderer shape, String type) {
        List<Entity> entityList = this.entityMap.get(type);
        for (Entity entity : entityList)
            if (entity.getType() == type)
//                if (!playAnimation(entity, batch))
                entity.draw(batch, shape);
    }

    /**
     * Draws only entities that are within range of a specified target position
     *
     * @param batch          -- SpriteBatch object
     * @param targetPosition -- position of a target x,y coordinate
     * @param range          -- range of detection (E.g 3.0f)
     */
    public void draw(SpriteBatch batch, ShapeRenderer shape, Vector2 targetPosition, float range) {

        for (Entity entity : getAllEntities()) {
            Vector2 currEntPos = entity.getVector2();
            if (currEntPos.dst(targetPosition) <= range)
//                if (!playAnimation(entity, batch))
                entity.draw(batch, shape);
        }
    }

    private void processEntityEvents() {
        EventBus.processEvents(EntityDisposedEvent.class);
        EventBus.processEvents(EntityAddedEvent.class);
    }

    /**
     * Calls all update functionality of all subclasses
     */
    public void update() {
        // resolve added and disposed events first, so we don't have floating references
        processEntityEvents();
        List<Entity> entityList = getAllEntities();
        for (Entity entity : entityList) {
            entity.update();
        }
    }

    private void handleEntityDisposed(EntityDisposedEvent e) {
        Entity disposedEntity = e.getEntity();
        for (List<Entity> entityList : entityMap.values()) {
            entityList.remove(disposedEntity);
        }
    }

    private void handleEntityAdded(EntityAddedEvent e) {
        addEntity(e.getEntity());
    }

    public void dispose() {
        disposeAllEntities();
        this.entityMap.clear();
    }

    public void dispose(String type) {
        disposeEntities(type);
        this.entityMap.remove(type);
    }

    public void dispose(String type, Entity entity) {
        disposeEntities(type, entity);
        this.entityMap.get(type).remove(entity);
    }

    private List<Entity> getAllEntities() {
        List<Entity> combinedList = entityMap.values().stream().flatMap(List::stream).collect(Collectors.toList());

        return combinedList;
    }

    private void disposeAllEntities() {
        List<Entity> entityList = getAllEntities();
        for (Entity entity : entityList) {
            entity.dispose();
        }
    }

    private void disposeEntities(String type) {
        List<Entity> entityList = this.entityMap.get(type);
        for (Entity entity : entityList) {
            entity.dispose();
        }
    }

    private void disposeEntities(String type, Entity entity) {
        List<Entity> entityList = this.entityMap.get(type);
        for (Entity e : entityList) {
            e.dispose();
        }
    }
}