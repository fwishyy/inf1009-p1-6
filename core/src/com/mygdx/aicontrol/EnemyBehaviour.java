package com.mygdx.aicontrol;

import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.CharacterStateEnum;

public class EnemyBehaviour implements Behaviour {
    private Player target;

    public EnemyBehaviour(Player target) {
        this.target = target;
    }

    public void update(Entity entity, float deltaTime) {
        Enemy enemy = (Enemy) entity;
        enemy.setTarget(target);
        float xDistanceFromTarget = Math.abs(enemy.getX() - target.getX());
        float yDistanceFromTarget = Math.abs(enemy.getY() - target.getY());
        boolean canStrike = xDistanceFromTarget <= enemy.getStrikingDistance() && yDistanceFromTarget <= enemy.getStrikingDistance();
        if (!enemy.isDead()) {
            // found enemy and within striking range, time to attack!
            if (canStrike) {
                if (enemy.getState() != CharacterStateEnum.ATTACK) {
                    enemy.attack();
                }
            } else {
                // cannot strike yet, need to find player to strike
                if (enemy.getState() != CharacterStateEnum.RUN) {
                    enemy.move();
                }
            }
        }
    }
}