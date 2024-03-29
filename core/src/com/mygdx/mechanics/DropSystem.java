package com.mygdx.mechanics;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.engine.entity.EntityAddedEvent;
import com.mygdx.engine.utils.Event;
import com.mygdx.engine.utils.EventListener;
import com.mygdx.entity.Enemy;
import com.mygdx.events.CharacterDeathEvent;
import com.mygdx.mechanics.pickups.HealingPotion;
import com.mygdx.mechanics.pickups.IncreaseMaxHealthPotion;

public class DropSystem {

    private EventListener<CharacterDeathEvent> characterDeathEventListener;

    public DropSystem() {
        this.characterDeathEventListener = new EventListener<CharacterDeathEvent>() {
            @Override
            public void onSignal(Event e) {
                CharacterDeathEvent characterDeathEvent = (CharacterDeathEvent) e;
                handleCharacterDeathEvent(characterDeathEvent);
            }
        };

        CharacterDeathEvent.addListener(CharacterDeathEvent.class, characterDeathEventListener);
    }

    private void handleCharacterDeathEvent(CharacterDeathEvent e) {
        if (e.getCharacter() instanceof Enemy) {
            Enemy enemy = (Enemy) e.getCharacter();
            potionDrop(enemy);
        }
    }

    private void potionDrop(Enemy enemy) {
        // check for a 1/5 chance to drop a potion
        if (MathUtils.random(1, 5) == 1) {
            // decide which potion to drop (50% chance each)
            boolean dropHealingPotion = MathUtils.randomBoolean();

            if (dropHealingPotion) {
                // create a health potion at the enemy location
                HealingPotion healingPotion = new HealingPotion(enemy.getVector2());
                EntityAddedEvent.addEvent(new EntityAddedEvent(healingPotion));
            } else {
                // create a max health potion at the enemy location
                IncreaseMaxHealthPotion maxHealingPotion = new IncreaseMaxHealthPotion(enemy.getVector2());
                EntityAddedEvent.addEvent(new EntityAddedEvent(maxHealingPotion));
            }
        }
    }
}
