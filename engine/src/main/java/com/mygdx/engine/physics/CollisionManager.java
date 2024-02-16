package com.mygdx.engine.physics;

import com.mygdx.engine.core.Manager;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.engine.utils.Signal;

public class CollisionManager extends Manager {
    public CollisionManager() {
        addCollisionListener(new EventListener<CollisionEvent>() {
            @Override
            public void onSignal(Signal<CollisionEvent> signal, CollisionEvent e) {
                resolveCollision(e);
            }
        });
    }

    private void resolveCollision(CollisionEvent e) {
        //TODO: resolve collisions here
    }
}
