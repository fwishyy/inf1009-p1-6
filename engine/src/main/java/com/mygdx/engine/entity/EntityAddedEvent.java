package com.mygdx.engine.entity;

import com.mygdx.engine.utils.Event;

public class EntityAddedEvent extends Event {
    private Entity entity;

    public EntityAddedEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
