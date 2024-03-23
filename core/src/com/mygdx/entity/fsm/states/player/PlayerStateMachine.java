package com.mygdx.entity.fsm.states.player;

import com.mygdx.entity.Character;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.StateMachine;
import com.mygdx.entity.fsm.states.player.PlayerAttackState;
import com.mygdx.entity.fsm.states.player.PlayerIdleState;
import com.mygdx.entity.fsm.states.player.PlayerRunState;

public class PlayerStateMachine extends StateMachine {
    protected Player character;
    protected PlayerIdleState idleState;
    protected PlayerRunState runState;
    protected PlayerAttackState attackState;

    public PlayerStateMachine(Player character) {
        idleState = new PlayerIdleState(character, this);
        runState = new PlayerRunState(character, this);
        attackState = new PlayerAttackState(character, this);
    }

    public void setIdleState() {
        changeState(idleState);
    }
    public void setRunState() {
        changeState(runState);
    }

    public void setAttackState() {
        changeState(attackState);
    }
}
