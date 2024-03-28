package com.mygdx.events;

import com.mygdx.engine.utils.Event;
import com.mygdx.entity.Character;

public class CharacterDeathEvent extends Event {
    private Character character;

    public CharacterDeathEvent(Character character) {
        this.character = character;
    }

    public Character getCharacter() {
        return character;
    }
}
