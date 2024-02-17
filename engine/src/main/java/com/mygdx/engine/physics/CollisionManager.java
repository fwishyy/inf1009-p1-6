package com.mygdx.engine.physics;

import java.util.*;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.core.Manager;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;
import com.mygdx.engine.entity.*;

public class CollisionManager extends Manager {
	
	List<Collider> colliderList;
	
    public CollisionManager() {
        addCollisionListener(new EventListener<CollisionEvent>() {
            @Override
            public void onSignal(Signal<CollisionEvent> signal, CollisionEvent e) {
                resolveCollision(e);
            }
        });
        
        colliderList = new ArrayList<Collider>();
    }
    
    private void resolveCollision(CollisionEvent e) {
        //TODO: resolve collisions here
//    	System.out.println("A: " + e.getEntityA().getType() + " B: " + e.getEntityB().getType());
    	e.getEntityA().collide(e.getEntityB().getCollider());
    	e.getEntityB().collide(e.getEntityA().getCollider());
    	
    }
    
    public void addCollider(Collider col) {
    	colliderList.add(col);
    }
    
    public void addCollider(Entity entity) {
    	colliderList.add(entity.getCollider());
    }
    
    public void removeCollider(Collider col) {
    	for(int i=0; i < colliderList.size(); i++) {
    		if(col == colliderList.get(i))
    			colliderList.remove(i);
    	}
    }
    
    public void removeCollider(Entity entity) {
    	for(int i=0; i < colliderList.size(); i++) {
    		if(entity.getCollider() == colliderList.get(i))
    			colliderList.remove(i);
    	}
    }
    
    public void updateCollider(Collider _old, Collider _new) {
    	for(int i=0; i < colliderList.size(); i++) {
    		if(_old == colliderList.get(i))
    			colliderList.set(i, _new);
    	}
    }
    
    public void updateCollider(Entity _old, Entity _new) {
    	for(int i=0; i < colliderList.size(); i++) {
    		if(_old.getCollider() == colliderList.get(i))
    			colliderList.set(i, _new.getCollider());
    	}
    }
    
    public void update() {
    	for(Collider curr: colliderList) {
    		for(Collider other: colliderList) {
    			if(curr == other)
    				continue;
    			if(curr.isCollide(other.getEntity())){
//    				System.out.println(curr.getEntity().getType() + ", " + other.getEntity().getType());
    				curr.onCollide(other.getEntity());
    			}
    		}
    	}
    }
}
