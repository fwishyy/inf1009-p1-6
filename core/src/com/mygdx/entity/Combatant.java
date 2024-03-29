package com.mygdx.entity;

import com.badlogic.gdx.math.Vector2;

public interface Combatant {
    void takeDamage(float damage);

    void attack();

    void setTarget(Vector2 entity);
}
