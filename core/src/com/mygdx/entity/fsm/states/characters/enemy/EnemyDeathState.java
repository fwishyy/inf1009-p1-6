package com.mygdx.entity.fsm.states.characters.enemy;

import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.events.EnemyDefeatedEvent;

public class EnemyDeathState extends CharacterState {

    public EnemyDeathState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        EnemyDefeatedEvent.addEvent(new EnemyDefeatedEvent());
        character.setAnimation("die", 1, true);
    }

    @Override
    public void update() {
        if (character.readyToDispose()) {
            character.dispose();
        }
    }
}
