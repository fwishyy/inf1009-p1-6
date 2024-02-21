package com.mygdx.engine.entity;

import com.mygdx.engine.utils.Event;

public class EntityDisposedEvent extends Event {
    private Entity entity;

    public EntityDisposedEvent(Entity entity) {
        this.entity = entity;
    }

    public Entity getEntity() {
        return entity;
    }
}
