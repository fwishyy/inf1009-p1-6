package com.mygdx.actions;

import com.mygdx.engine.actions.GameAction;
import com.mygdx.engine.entity.Entity;
import com.mygdx.engine.entity.EntityManager;
import com.mygdx.entity.Player;

public class Attack extends GameAction {

    public EntityManager em;
    public boolean isAttack;
    public Player newPlayer;

    @Override
    public boolean act(Entity entity) {
        // TODO Auto-generated method stub
        Entity old = entity;

        if (isAttack) {

            System.out.println("attack");
            em.replaceEntity(newPlayer, old);

        }

        return false;
    }

}
