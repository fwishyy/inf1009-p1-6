package com.mygdx.mechanics.powerups;

import com.mygdx.entity.Player;

public abstract class PowerUp {
    protected String name;
    protected String description;

    public PowerUp() {

    }

    public PowerUp(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public abstract void activate(Player player);

}
