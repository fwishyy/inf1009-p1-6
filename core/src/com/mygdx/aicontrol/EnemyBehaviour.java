package com.mygdx.aicontrol;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.engine.behaviour.Behaviour;
import com.mygdx.engine.entity.Entity;
import com.mygdx.entity.Enemy;
import com.mygdx.entity.Player;
import com.mygdx.entity.fsm.states.characters.CharacterStateEnum;

public class EnemyBehaviour implements Behaviour {
    private Player target;

    public EnemyBehaviour(Player target) {
        this.target = target;
    }

    public void update(Entity entity, float deltaTime) {
        Enemy enemy = (Enemy) entity;
        enemy.setTarget(target);

        Vector2 enemyPos = enemy.getVector2();
        Vector2 targetPos = target.getVector2();

        double distanceFromTarget = Math.sqrt(Math.pow((targetPos.x - enemyPos.x), 2) + Math.pow((targetPos.y - enemyPos.y), 2));
        boolean canStrike = distanceFromTarget <= enemy.getStrikingDistance();
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