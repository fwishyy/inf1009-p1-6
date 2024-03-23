package com.mygdx.entity.fsm.states.player;

import com.mygdx.entity.Character;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.State;

public abstract class PlayerState extends State {
    protected Player player;
    protected PlayerStateMachine stateMachine;

    public PlayerState(Player player, PlayerStateMachine stateMachine) {
        this.player = player;
        this.stateMachine = stateMachine;
    }
}
