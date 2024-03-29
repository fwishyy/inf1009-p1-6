package com.mygdx.entity.fsm.states.characters.enemy;

import com.mygdx.entity.Enemy;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.events.CharacterDeathEvent;

public class EnemyDieState extends CharacterState {

    public EnemyDieState(Enemy enemy, CharacterStateMachine stateMachine) {
        super(enemy, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("die", 1, true);
    }

    @Override
    public void update() {
        if (character.readyToDispose()) {
            CharacterDeathEvent.addEvent(new CharacterDeathEvent(character));
            character.dispose();
        }
    }
}
