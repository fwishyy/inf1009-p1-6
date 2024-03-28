package com.mygdx.entity.fsm.states.characters.player;

import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.characters.CharacterState;
import com.mygdx.entity.fsm.states.characters.CharacterStateMachine;
import com.mygdx.events.CharacterDeathEvent;

public class PlayerDieState extends CharacterState {
    public PlayerDieState(Player player, CharacterStateMachine stateMachine) {
        super(player, stateMachine);
    }

    @Override
    public void onStateEnter() {
        character.setAnimation("die", 1, true);
        CharacterDeathEvent.addEvent(new CharacterDeathEvent(character));
    }

    @Override
    public void update() {
        if (character.readyToDispose()) {
            character.dispose();
        }
    }
}
